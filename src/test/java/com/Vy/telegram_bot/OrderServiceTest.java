package com.Vy.telegram_bot;

import com.Vy.telegram_bot.dto.Request.OrderItemRequest;
import com.Vy.telegram_bot.dto.Request.OrderRequest;
import com.Vy.telegram_bot.dto.Response.OrderResponse;
import com.Vy.telegram_bot.exception.CustomerInactiveException;
import com.Vy.telegram_bot.exception.OrderNotFoundException;
import com.Vy.telegram_bot.exception.ProductInactiveException;
import com.Vy.telegram_bot.model.Customer;
import com.Vy.telegram_bot.model.Order;
import com.Vy.telegram_bot.model.OrderItem;
import com.Vy.telegram_bot.model.Product;
import com.Vy.telegram_bot.repository.CustomerRepository;
import com.Vy.telegram_bot.repository.OrderRepository;
import com.Vy.telegram_bot.repository.ProductRepository;
import com.Vy.telegram_bot.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    //testes unitários
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;


    UUID id = UUID.randomUUID();

    @Test
    void shouldThrowExceptionWhenOrderDoesNotExist() {
        Customer customer = new Customer();
        customer.setId(id);
        Order order = new Order();
        order.setId(id);
        order.setCustomer(customer);
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        OrderResponse orderResponse = orderService.findById(id);
        assertEquals(order.getId(), orderResponse.id());
    }

    @Test
    void shouldFindOrderById() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());

        Order order = new Order();
        order.setId(id);
        order.setCustomer(customer);

        when(orderRepository.findById(id))
                .thenReturn(Optional.of(order));

        OrderResponse response = orderService.findById(id);

        assertEquals(order.getId(), response.id());
        assertEquals(order.getOrderStatus(), response.status());
        assertEquals(order.getTotal(), response.total());

        verify(orderRepository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenCustomerDoesNotExist() {

        UUID customerUUID = UUID.randomUUID();
        OrderRequest orderRequest = new OrderRequest(customerUUID, new ArrayList<>());


        when(customerRepository.findById(customerUUID))
                .thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.save(orderRequest));
        verify(customerRepository).findById(customerUUID);
    }

    @Test
    void shouldThrowExceptionWhenCustomerIsInactive() {

        UUID customerUUID = UUID.randomUUID();
        OrderRequest orderRequest = new OrderRequest(customerUUID, new ArrayList<>());

        Customer customer = new Customer();
        customer.setActive(false);

        when(customerRepository.findById(customerUUID))
                .thenReturn(Optional.of(customer));

        assertThrows(CustomerInactiveException.class, () -> orderService.save(orderRequest));
        verify(customerRepository).findById(customerUUID);
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {

        UUID customerUUID = UUID.randomUUID();
        UUID productUUID = UUID.randomUUID();

        Customer customer = new Customer();
        customer.setId(customerUUID);
        customer.setActive(true);

        Order order = new Order();
        order.setCustomer(customer);
        OrderItemRequest orderItemRequest = new OrderItemRequest(productUUID, 7);
        List<OrderItemRequest> item = new ArrayList<>();
        item.add(orderItemRequest);

        OrderRequest orderRequest = new OrderRequest(customerUUID, item);

        when(customerRepository.findById(customerUUID))
                .thenReturn(Optional.of(customer));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        when(productRepository.findById(productUUID))
                .thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.save(orderRequest));

        verify(customerRepository).findById(customerUUID);
        verify(productRepository).findById(productUUID);

    }

    @Test
    void shouldThrowExceptionWhenProductIsInactive(){
        UUID customerUUID = UUID.randomUUID();
        UUID productUUID = UUID.randomUUID();

//        Product product = productRepository.findById(productUUID).orElseThrow(() -> new RuntimeException("Not found"));
        Product product = new Product();
        product.setActive(false);
        Customer customer = new Customer();
        customer.setId(customerUUID);
        customer.setActive(true);

        Order order = new Order();
        order.setCustomer(customer);
        OrderItemRequest orderItemRequest = new OrderItemRequest(productUUID, 7);
        List<OrderItemRequest> item = new ArrayList<>();
        item.add(orderItemRequest);

        OrderRequest orderRequest = new OrderRequest(customerUUID, item);

        when(customerRepository.findById(customerUUID))
                .thenReturn(Optional.of(customer));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        when(productRepository.findById(productUUID))
                .thenReturn(Optional.of(product));

        assertThrows(ProductInactiveException.class, () -> orderService.save(orderRequest));

        verify(customerRepository).findById(customerUUID);
        verify(productRepository).findById(productUUID);
    }
}
