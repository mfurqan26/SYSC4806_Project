package amazin.controller;

import amazin.model.Book;
import amazin.model.Customer;
import amazin.repository.BookRepository;
import amazin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/Shop")
    public String Customer(Model model) {
        Flux<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "Shop";
    }

    @PostMapping(value="/Shop", params = "AllBooks")
    public String SearchAllBook(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "Shop";
    }

    @PostMapping(value="/Shop", params = "SearchBook")
    public String SearchBook(@RequestParam(name="search", required=false, defaultValue = "") String search,
                             @RequestParam(name="filter", required=false, defaultValue = "") String filter,
                             Model model){
        if(!search.equals("")){
            Flux<Book> books;
            if(filter.equals("by-publisher")){
                books = bookRepository.findBooksByPublisher(search);
            }
            else if(filter.equals("by-name")){
                books = bookRepository.findBooksByName(search);
            }
            else {
                books = bookRepository.findAll();
            }

            //If any book is Found
            if(books.toIterable().iterator().hasNext()) {
                model.addAttribute("books", books);
            }
            else {
                model.addAttribute("searchError", "No Books Found With that Name.");
            }
        }
        else {
            Flux<Book> books = bookRepository.findAll();
            model.addAttribute("books",books);
        }
        return "Shop";
    }



    @GetMapping("/ShoppingCart")
    public String Customer(){
        return "ShoppingCart";
    }


    @Autowired
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/customer")
    public void createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public Customer getCustomer(@RequestParam("id") Integer id){
        return customerRepository.findCustomerById(id);
    }


}
