package com.Vy.telegram_bot.service;

import com.Vy.telegram_bot.dto.Request.CustomerRequest;
import com.Vy.telegram_bot.dto.Request.RegisterRequest;
import com.Vy.telegram_bot.dto.Response.CustomerResponse;
import com.Vy.telegram_bot.enums.RoleStatus;
import com.Vy.telegram_bot.exception.CustomerNotFoundException;
import com.Vy.telegram_bot.model.Customer;
import com.Vy.telegram_bot.model.User;
import com.Vy.telegram_bot.repository.CustomerRepository;
import com.Vy.telegram_bot.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerResponse save(RegisterRequest registerRequest) {
        User user = new User();
        Customer customer = new Customer();

        BeanUtils.copyProperties(registerRequest, customer);
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already exist! Try again");
        }
        customer.setActive(true);
        customer.setCreatedAt(LocalDateTime.now());

        BeanUtils.copyProperties(
                customer, user,
                "id",
                "createdAt",
                "active",
                "order"
        );


        user.setRole(RoleStatus.CUSTOMER);
        user.setCustomer(customer);
        user.setPassword(passwordEncoder.encode(registerRequest.password()));

        customerRepository.save(customer);
        userRepository.save(user);
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
