package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getcustomers(){

        return customerService.getCustomers();

    }
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){
       Customer theCustomer= customerService.getCustomer(customerId);

       if (theCustomer == null){
           throw new CustomerNotFoundException("Customer id not found " +customerId);
       }

       return theCustomer;
    }
    // add mapping for POST / Customers - add new customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer){

        // force insert a new record
        theCustomer.setId(0);
        customerService.saveCustomer(theCustomer);
        return theCustomer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer){

        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }
    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){

        Customer theCustomer = customerService.getCustomer(customerId);
        if (theCustomer == null){
            throw new CustomerNotFoundException("Customer id not found " +customerId);
        }

        customerService.deleteCustomer(customerId);
        return " Deleted customer id - " + customerId;
    }
}
