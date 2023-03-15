package amazin.controller;

import amazin.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import amazin.model.Account;
import amazin.model.Book;
import amazin.model.Book.BookId;
import amazin.repository.BookRepository;
import amazin.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

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
                               @RequestParam(name="version", required=false, defaultValue = "") String version,
                               @RequestParam(name="name", required=false, defaultValue = "") String name,
                               @RequestParam(name="description", required=false, defaultValue = "") String description,
                               @RequestParam(name="publisher", required=false, defaultValue = "") String publisher,
                               @RequestParam(name="stock", required=false, defaultValue = "") String stock,
                               @RequestParam(name="price", required=false, defaultValue = "") String price, Model model) {
        Boolean conditionAnyNotSet = isbn.equals("") || version.equals("") || name.equals("") ||
                description.equals("") || publisher.equals("") || stock.equals("") || price.equals("");
        if(conditionAnyNotSet){
            model.addAttribute("createBookError", "Some Input is Not Set!");
        }

        else {
            try {
                int newVersion = Integer.parseInt(version);
                double newPrice = Double.parseDouble(price);
                int newStock = Integer.parseInt(stock);
                if(newPrice >= 0 && newStock >= 0 && newVersion >= 1) {
                    Book newBook = new Book(isbn, newVersion, name, description, publisher, newStock, newPrice);
                    bookRepository.save(newBook);
                    return "redirect:/Vendor";
                }
                else{
                    model.addAttribute("createBookError", "Enter Non-Negative numbers for version or stock or price!");
                }
            } catch (NumberFormatException nfe) {
                model.addAttribute("createBookError", "Inputs do not have correct number formating for version or stock or price!");
            }
        }

        return "VendorCreate";
    }

    @GetMapping("/VendorEdit")
    public String VendorEdit(Model model) {
        return "VendorEdit";
    }

}