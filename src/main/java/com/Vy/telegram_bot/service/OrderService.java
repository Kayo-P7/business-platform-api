package com.Vy.telegram_bot.service;

import com.Vy.telegram_bot.dto.OrderItemRequest;
import com.Vy.telegram_bot.dto.OrderRequest;
import com.Vy.telegram_bot.dto.OrderResponse;
import com.Vy.telegram_bot.model.Customer;
import com.Vy.telegram_bot.model.Order;
import com.Vy.telegram_bot.model.OrderItem;
import com.Vy.telegram_bot.model.Product;
import com.Vy.telegram_bot.model.pk.PkOrderAndProduct;
import com.Vy.telegram_bot.repository.CustomerRepository;
import com.Vy.telegram_bot.repository.OrderRepository;
import com.Vy.telegram_bot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public OrderResponse save(OrderRequest orderRequest) {

        Order order = new Order();
        Customer customer = customerRepository
                .findById(orderRequest.customerId())
                .orElseThrow(() -> new RuntimeException("Id not found"));
        order.setCustomer(customer);
        order = orderRepository.save(order);
        for (OrderItemRequest item : orderRequest.items()) {
            OrderItem orderItem = new OrderItem();


            Product product = productRepository
                    .findById(item.productId())
                    .orElseThrow(() -> new RuntimeException("Id not found"));
            PkOrderAndProduct pkOrderAndProduct = new PkOrderAndProduct(order.getId(), product.getId());
            orderItem.setPkOrderAndProduct(pkOrderAndProduct);
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(item.quantity());
            orderItem.setPrice(product.getPrice());
            order.getItems().add(orderItem);


        }
        order = orderRepository.save(order);
        return new OrderResponse(order.getTotal());
    }

}
