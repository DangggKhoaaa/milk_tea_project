package com.example.hudamilktea.service.DTO;

import com.example.hudamilktea.service.product.request.SelectOptionRequest;
import lombok.Data;


@Data
public class BillDetailRequest {
    private SelectOptionRequest product;
    private String price;
    private String quantity;

    public BillDetailRequest() {
        product = new SelectOptionRequest();
    }
}
