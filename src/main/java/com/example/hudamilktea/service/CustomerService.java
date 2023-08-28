package com.example.hudamilktea.service;

import com.example.hudamilktea.model.Customer;
import com.example.hudamilktea.model.LocationRegion;
import com.example.hudamilktea.model.enums.Role;
import com.example.hudamilktea.repository.CustomerRepository;
import com.example.hudamilktea.repository.LocationRegionRepository;
import com.example.hudamilktea.service.DTO.CustomerRequest;
import com.example.hudamilktea.util.AppUtils;
import lombok.AllArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    private final LocationRegionRepository locationRegionRepository;

    private final PasswordEncoder passwordEncoder;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public void create(CustomerRequest request) {
        Customer customer = AppUtils.mapper.map(request, Customer.class);

        customer.setRole(Role.ROLE_USER);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        LocationRegion locationRegion =request.getLocationRegion();
        locationRegionRepository.save(locationRegion);
        customer.setLocationRegion(locationRegion);

        customerRepository.save(customer);
    }

    public Customer updateProfile(CustomerRequest request, Long id) {
        Customer customer = AppUtils.mapper.map(request, Customer.class);


        return customerRepository.save(customer);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not Exist") );
        var role = new ArrayList<SimpleGrantedAuthority>();
        role.add(new SimpleGrantedAuthority(customer.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(), role);
    }
}
