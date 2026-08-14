package com.Vy.telegram_bot.controller;

import com.Vy.telegram_bot.dto.Request.OrderRequest;
import com.Vy.telegram_bot.dto.Response.OrderResponse;
import com.Vy.telegram_bot.dto.Request.OrderStatusUpdateRequest;
import com.Vy.telegram_bot.service.OrderService;
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
        name = "Orders",
        description = "Operations related to order management"
)
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @Operation(
            summary = "send of order",
            description = "send of products orders"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Order created successfully"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Order save with successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "data send wrong!"
            )
    })

    @PostMapping
    public ResponseEntity<OrderResponse> save(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(request));
    }

    @Operation(
            summary = "Find order",
            description = "find order by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "order is find!"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found"
            )
    })

    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("id") UUID id) {

        return ResponseEntity.ok().body(orderService.findById(id));
    }

    @Operation(
            summary = "List all orders",
            description = "Find orders send of clients"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "order exist!"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "order not found"
            )
    })
    @GetMapping
    public ResponseEntity<Page<OrderResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(orderService.findAll(pageable));
    }

    @Operation(
            summary = "Upadate status order",
            description = "Upadate status order on client"
    )
    @ApiResponses({
            @ApiResponse(
                   responseCode = "200",
                    description = "update successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "date invalid"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "resource not exists"
            )
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable("id") UUID id, @Valid @RequestBody OrderStatusUpdateRequest request) {

        return ResponseEntity.ok().body(orderService.updateOrderStatus(id, request));

    }


}
