package com.metehan.springbootfundamentals.two_orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/persist")
    @ResponseBody
    public String persistCustomer(@RequestParam(name = "name") String customerName,
                                  @RequestParam(name = "credit") double totalCredit) {
        Customer customer = new Customer(0, customerName, totalCredit);
        customerRepository.save(customer);
        return "Varlık direldi : "
                + customer.getCustomerId();
    }

    @GetMapping("/customer/find/{id}")
    @ResponseBody
    public String findCustomer(@PathVariable(name = "id") long customerId) {
        Optional<Customer> optional
                = customerRepository.findById(customerId);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            return "Varlık ad : "
                    + customer.getCustomerName() + " "
                    + " borç: " + customer.getTotalCredit();
        } else {
            return "Varlık bulunamadı: " + customerId;
        }
    }

    @GetMapping("/customer/merge/{id}")
    @ResponseBody
    public String mergeCustomer(@PathVariable(name = "id") long customerId,
                                @RequestParam(name = "name") String customerName,
                                @RequestParam(name = "credit") double totalCredit) {
        Optional<Customer> optional = customerRepository.findById(customerId);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            customer.setCustomerName(customerName);
            customer.setTotalCredit(totalCredit);
            customerRepository.save(customer);
            return "Varlık kaynaştırıldı : "
                    + customer.getCustomerId();
        } else {
            return "Varlık bulunamadı: " + customerId;
        }
    }

    @GetMapping("/customer/remove/{id}")
    @ResponseBody
    public String removeCustomer(@PathVariable(name = "id") long customerId) {
        customerRepository.deleteById(customerId);
        return "Varlık silindi : " + customerId;
    }

    @GetMapping("/customer/findAll")
    @ResponseBody
    public String query() {

        return customerRepository.findAll().toString();
    }

    @GetMapping("/customer/findAll/{id}")
    @ResponseBody
    public String query(@PathVariable(name = "id") long customerId) {

        return customerRepository.findCustomerById(customerId).toString();
    }
}

