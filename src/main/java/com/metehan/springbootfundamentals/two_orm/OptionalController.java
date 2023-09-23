package com.metehan.springbootfundamentals.two_orm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class OptionalController {
    private CustomerRepository customerRepository;

    public OptionalController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/optional/present/{id}")
    @ResponseBody
    public String findPresent( @PathVariable(name = "id") long customerId) {
        Optional<Customer> optional  = customerRepository.findById(customerId);
        Customer customer = null;
        if (optional.isPresent()) {
            customer = optional.get();
            return "Mevcutta alımcı: " +
                    customer.getCustomerName();
        } else {
            return "Mevcutta alımcı bulunamadı";
        }
    }

    @GetMapping("/optional/else/{id}")
    @ResponseBody
    public String findElse(
            @PathVariable(name = "id") long customerId) {
        return customerRepository
                .findById(customerId)
                .map((customer) ->
                        "Değilsede alımcı: " +
                                customer.getCustomerName())
                .orElse("Değilsede alımcı bulunamadı");
    }

    @GetMapping("/optional/get/{id}")
    @ResponseBody
    public String findGet(
            @PathVariable(name = "id") long customerId) {
        return customerRepository
                .findById(customerId)
                .map((customer) ->
                        "Edinmede alımcı: " +
                                customer.getCustomerName())
                .orElseGet(() -> {
                    String defultName = "Edinmede yoksama ad";
                    return "Edinmede alımcı: " + defultName;
                });
    }

    @GetMapping("/optional/throw/{id}")
    @ResponseBody
    public String findThrow(
            @PathVariable(name = "id") long customerId) {
        return customerRepository
                .findById(customerId)
                .map((customer) ->
                        "Fırlatmada alımcı: " +
                                customer.getCustomerName())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Fırlatmada alımcı bulunamadı"));
    }
}

