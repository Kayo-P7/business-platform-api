package com.Vy.telegram_bot.controller;

import com.Vy.telegram_bot.dto.OrderRequest;
import com.Vy.telegram_bot.dto.OrderResponse;
import com.Vy.telegram_bot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;


    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest request) {
        return ResponseEntity.ok().body(orderService.save(request));
    }
}
