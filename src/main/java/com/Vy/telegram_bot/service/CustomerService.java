package com.Vy.telegram_bot.service;

import com.Vy.telegram_bot.dto.Request.CustomerRequest;
import com.Vy.telegram_bot.dto.Response.CustomerResponse;
import com.Vy.telegram_bot.exception.CustomerNotFoundException;
import com.Vy.telegram_bot.model.Customer;
import com.Vy.telegram_bot.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public CustomerResponse save(CustomerRequest customerRequest) {


        Customer customer = new Customer();
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already exist! Try again");
        }
        BeanUtils.copyProperties(customerRequest, customer);
        customer.setActive(false);
        customer.setCreatedAt(LocalDateTime.now());
        customerRepository.save(customer);
        return new CustomerResponse(customer);
    }

    public Page<CustomerResponse> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable).map(CustomerResponse::new);
    }

    public void deleteById(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException(" Id not found! try again");
        }
        customerRepository.deleteById(id);
    }

    public CustomerResponse updateCustomer(UUID id, CustomerRequest request) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Id not found!"));
        BeanUtils.copyProperties(request, customer, "id", "createdAt", "active");
        customerRepository.save(customer);
        return new CustomerResponse(customer);

    }

    public CustomerResponse findById(UUID id) {
        return customerRepository.findById(id)
                .map(CustomerResponse::new)
                .orElseThrow(() -> new CustomerNotFoundException("Id not found"));

    }

    public CustomerResponse findByName(String name) {

        return customerRepository.findByName(name)
                .stream()
                .map(CustomerResponse::new).toList().getFirst();
    }
}
