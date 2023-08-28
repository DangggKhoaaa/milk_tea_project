package com.example.hudamilktea.api;

import com.example.hudamilktea.model.Customer;
import com.example.hudamilktea.service.CustomerService;
import com.example.hudamilktea.service.DTO.CustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
