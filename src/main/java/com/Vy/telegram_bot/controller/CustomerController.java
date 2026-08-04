package com.Vy.telegram_bot.controller;

import com.Vy.telegram_bot.dto.CustomerRequest;
import com.Vy.telegram_bot.dto.CustomerResponse;
import com.Vy.telegram_bot.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(
        name = "Customer",
        description = "Operations related to customer management"
)
@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;


    @Operation(
            summary = "Create customer",
            description = "Creates a new customer record"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    @PostMapping
    public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerRequest request) {

        CustomerResponse customerResponse = customerService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @Operation(
            summary = "Delete customer",
            description = "Deletes a customer by their UUID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Customer deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer not found"
            )
    }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) {

        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Update customer",
            description = "Updates customer information by their UUID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @PutMapping("{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable("id") UUID id, @Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }
    @Operation(
            summary = "Find customer by ID",
            description = "Retrieves customer details by their UUID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(customerService.findById(id));
    }
    @Operation(
            summary = "Find customer by name",
            description = "Retrieves customer details by their name"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("{name}")
    public ResponseEntity<CustomerResponse> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(customerService.findByName(name));
    }
    @Operation(
            summary = "List all customers",
            description = "Retrieves a paginated list of customers"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(customerService.findAll(pageable));
    }

}


