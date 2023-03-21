package amazin.controller;

import amazin.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import amazin.model.Account;
import amazin.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class VendorController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/Vendor")
    public String Vendor(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "Vendor";
    }

    @GetMapping("/VendorCreate")
    public String VendorCreate() {
        return "VendorCreate";
    }

    @PostMapping(value="/VendorCreate", params = "AddBook")
    public String CreateBook(@RequestParam(name="isbn", required=false, defaultValue = "") String isbn,
                               @RequestParam(name="createNewVariant", required=false, defaultValue= "False") String createNewVariant,
                               @RequestParam(name="name", required=false, defaultValue = "") String name,
                               @RequestParam(name="description", required=false, defaultValue = "") String description,
                               @RequestParam(name="publisher", required=false, defaultValue = "") String publisher,
                               @RequestParam(name="stock", required=false, defaultValue = "") String stock,
                               @RequestParam(name="price", required=false, defaultValue = "") String price, Model model) {
        boolean conditionAnyNotSet = isbn.equals("") || name.equals("") ||
                description.equals("") || publisher.equals("") || stock.equals("") || price.equals("");

        if (conditionAnyNotSet) {
            model.addAttribute("createBookError", "Some Input is Not Set!");
            return "VendorCreate";
        }

        Iterable<Book> bookList = bookRepository.findBooksByIsbn(isbn);
        int numBooksWithSameISBN = 0;
        for( Book book : bookList){
            numBooksWithSameISBN++;
        }

        // we have a book with that isbn in the repo, and we do not want to create a new variant
        if (numBooksWithSameISBN > 0) {
            if (createNewVariant.equals("False")) {
                System.out.println(createNewVariant);
                model.addAttribute("createBookError", "Book With That ISBN Already Exist!\nPlease Click 'Create New Variant' To Create A New Variant");
                return "VendorCreate";
            }
        }


        try {
            double newPrice = Double.parseDouble(price);
            int newStock = Integer.parseInt(stock);
            if (newPrice >= 0 && newStock >= 0) {
                int version = numBooksWithSameISBN + 1;
                Book newBook = new Book(isbn, version, name, description, publisher, newStock, newPrice);
                bookRepository.save(newBook);
                return "redirect:/Vendor";
            } else {
                model.addAttribute("createBookError", "Enter Non-Negative numbers for version or stock or price!");
            }
        } catch (NumberFormatException nfe) {
            model.addAttribute("createBookError", "Inputs do not have correct number formating for version or stock or price!");
        }
        return "VendorCreate";
    }

    @GetMapping("/VendorEdit")
    public String VendorEdit() {
        return "VendorEdit";
    }
    @PostMapping(value="/VendorEdit", params="SearchBook")
    public String BookSearch(@RequestParam(name="name", required=false, defaultValue = "") String name,Model model) {
        Iterable<Book> foundBooks = bookRepository.findBooksByName(name);
        //Return all books by empty input
        if(name.equals("")){
            Iterable<Book> allBooks = bookRepository.findAll();
            model.addAttribute("books",allBooks);
        }
        //If there found books then add them to model
        else if(foundBooks.iterator().hasNext()){
            model.addAttribute("books",foundBooks);
        }
        else{
            model.addAttribute("BookSearchError","No Books Found by that name!");
        }
        return "VendorEdit";
    }

}