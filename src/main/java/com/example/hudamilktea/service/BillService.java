package com.example.hudamilktea.service;

import com.example.hudamilktea.model.Bill;
import com.example.hudamilktea.model.Customer;
import com.example.hudamilktea.model.Product;
import com.example.hudamilktea.repository.BillRepository;
import com.example.hudamilktea.repository.CustomerRepository;
import com.example.hudamilktea.repository.ProductRepository;
import com.example.hudamilktea.service.DTO.BillRequest;
import com.example.hudamilktea.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Transactional
public class BillService {

    private final BillRepository billRepository;

    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    public void create(BillRequest request) {
        Bill bill = AppUtils.mapper.map(request, Bill.class);
        bill.setCustomerName(request.getCustomer().getFullName());
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (var item : bill.getBillDetails()) {
            Product product = productRepository.findById(item.getProduct().getId()).orElseThrow();
            item.setPrice(product.getPrice());
            item.setProductName(product.getName());
            item.setBill(bill);
            totalAmount = totalAmount.add(product.getPrice()).multiply(new BigDecimal(item.getQuantity().toString()));
        }
        bill.setTotal(totalAmount);

        Customer customer = customerRepository.findCustomerByUsername(request.getCustomer().getUsername()).orElseThrow(null);
        if (customer != null) {
            bill.getCustomer().setId(customer.getId());
        }
        customer = bill.getCustomer();
        bill.setCustomer(customerRepository.save(customer));
        billRepository.save(bill);
    }
}
