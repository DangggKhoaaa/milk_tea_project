package com.example.hudamilktea.api;

import com.example.hudamilktea.model.Customer;
import com.example.hudamilktea.service.CustomerService;
import com.example.hudamilktea.service.DTO.CustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;


@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> findAll (){
        return customerService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerRequest customerRequest) {
        customerService.create(customerRequest);
        return ResponseEntity.ok(customerRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById( @PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }
    @PatchMapping("{id}")
    public ResponseEntity<?> update(@RequestBody CustomerRequest request, @PathVariable Long id) {
        Customer customer = customerService.updateProfile(request, id);
        return ResponseEntity.ok(customer);
    }
}
