package amazin.controller;

import amazin.model.Customer;
import amazin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private CustomerRepository customerRepository;

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
