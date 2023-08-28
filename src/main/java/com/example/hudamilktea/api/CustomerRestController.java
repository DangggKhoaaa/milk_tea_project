package com.example.hudamilktea.api;

import com.example.hudamilktea.service.CustomerService;
import com.example.hudamilktea.service.DTO.CustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerRequest customerRequest) {
        customerService.create(customerRequest);
        return ResponseEntity.ok(customerRequest);
    }
}
