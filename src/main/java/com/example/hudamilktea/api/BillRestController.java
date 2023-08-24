package com.example.hudamilktea.api;

import com.example.hudamilktea.service.BillService;
import com.example.hudamilktea.service.DTO.BillRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bills")
public class BillRestController {

    private final BillService billService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody BillRequest request) {
        billService.create(request);
        return ResponseEntity.ok(request);
    }
}
