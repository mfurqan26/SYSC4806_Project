package amazin.controller;

import amazin.model.Book;
import amazin.model.Customer;
import amazin.repository.BookRepository;
import amazin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/Shop")
    public String Customer(Model model){
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
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
