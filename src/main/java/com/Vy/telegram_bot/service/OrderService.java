package com.Vy.telegram_bot.service;

import com.Vy.telegram_bot.dto.Request.OrderItemRequest;
import com.Vy.telegram_bot.dto.Request.OrderRequest;
import com.Vy.telegram_bot.dto.Request.OrderStatusUpdateRequest;
import com.Vy.telegram_bot.dto.Response.OrderItemResponse;
import com.Vy.telegram_bot.dto.Response.OrderResponse;
import com.Vy.telegram_bot.dto.summary.CustomerSummary;
import com.Vy.telegram_bot.enums.OrderStatus;
import com.Vy.telegram_bot.exception.CustomerInactiveException;
import com.Vy.telegram_bot.exception.InsufficientStockException;
import com.Vy.telegram_bot.exception.OrderNotFoundException;
import com.Vy.telegram_bot.exception.ProductInactiveException;
import com.Vy.telegram_bot.model.Customer;
import com.Vy.telegram_bot.model.Order;
import com.Vy.telegram_bot.model.OrderItem;
import com.Vy.telegram_bot.model.Product;
import com.Vy.telegram_bot.model.pk.PkOrderAndProduct;
import com.Vy.telegram_bot.repository.CustomerRepository;
import com.Vy.telegram_bot.repository.OrderRepository;
import com.Vy.telegram_bot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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
        //Order
        Order order = new Order();
        //Customer
        Customer customer = customerRepository
                .findById(orderRequest.customerId())
                .orElseThrow(() -> new OrderNotFoundException("Id not found"));
        order.setCustomer(customer);
        if (!customer.getActive()) {
            throw new CustomerInactiveException("Customer this not active");
        }
        //save
        order = orderRepository.save(order);
        order.setOrderStatus(OrderStatus.PENDING);

        //FOR
        for (OrderItemRequest item : orderRequest.items()) {
            //OrderItem
            OrderItem orderItem = new OrderItem();

            //Product
            Product product = productRepository
                    .findById(item.productId())
                    .orElseThrow(() -> new OrderNotFoundException("Id not found"));
            PkOrderAndProduct pkOrderAndProduct = new PkOrderAndProduct(order.getId(), product.getId());
            orderItem.setPkOrderAndProduct(pkOrderAndProduct);


            if (!product.getActive()) {
                throw new ProductInactiveException("Product is inactive");//ProductNotActiveException
            }
            if (item.quantity() > product.getStock()) {
                throw new InsufficientStockException("Quantity product insufficient");
            }


            product.setStock(product.getStock() - item.quantity());

            orderItem.setQuantity(item.quantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            //save
            //esse save funciona como se fosse um carrinho. Se você fizer 3 pedidos de 3 produtos dentro do mesmo JSON, ele vai colocar os 3 pedidos com o mesmo id -> id_order: 123, id_order: 123 e id_order: 123
            productRepository.save(product);//ele não está em cascade então você tem que salvar
            order.getItems().add(orderItem); // isso faz com que o json seja retornado no get do postman
            // Assim, os itens ficam disponíveis para montar o OrderResponse.

        }
        List<OrderItemResponse> listOrderItemResponse = order.getItems().stream().map(OrderItemResponse::new).toList();
        //save
        order = orderRepository.save(order);

        CustomerSummary customerSummary = new CustomerSummary(order.getCustomer());

        return new OrderResponse(order.getId(), order.getOrderStatus(), order.getTotal(), customerSummary, listOrderItemResponse);
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Id not found!"));

        return new OrderResponse(order);

    }

    @Transactional(readOnly = true)
    public Page<OrderResponse> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).map(OrderResponse::new);
    }

    @Transactional
    public OrderResponse updateOrderStatus(UUID id, OrderStatusUpdateRequest request) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Id not found!"));

        order.setOrderStatus(request.status());
        Order orderUpdate = orderRepository.save(order);
        return new OrderResponse(orderUpdate.getId(), orderUpdate.getOrderStatus(), orderUpdate.getTotal() != null ? orderUpdate.getTotal() : BigDecimal.ZERO, new CustomerSummary(orderUpdate.getCustomer()), orderUpdate.getItems().stream().map(OrderItemResponse::new).toList());
    }

}
