package com.example.hudamilktea.service.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
public class BillRequest {
    private String date;
    private CustomerRequest customer;
    private List<BillDetailRequest> billDetails;
}
