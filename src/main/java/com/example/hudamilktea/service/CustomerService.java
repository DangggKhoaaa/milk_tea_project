package com.example.hudamilktea.service;

import com.example.hudamilktea.model.Customer;
import com.example.hudamilktea.model.LocationRegion;
import com.example.hudamilktea.repository.CustomerRepository;
import com.example.hudamilktea.repository.LocationRegionRepository;
import com.example.hudamilktea.service.DTO.CustomerRequest;
import com.example.hudamilktea.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final LocationRegionRepository locationRegionRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public void create(CustomerRequest request) {
        Customer customer = AppUtils.mapper.map(request, Customer.class);

        LocationRegion locationRegion =request.getLocationRegion();
        locationRegionRepository.save(locationRegion);
        customer.setLocationRegion(locationRegion);

        customerRepository.save(customer);
    }
}
