package ru.hpclab.hl.module1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.dto.OrderDTO;
import ru.hpclab.hl.module1.model.Product;
import ru.hpclab.hl.module1.model.order.OrderCustomerPrice;
import ru.hpclab.hl.module1.repository.webapi.WebApiOrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final WebApiOrderRepository webApiOrderRepository;

    private final ProductService productService;

    private final CustomerService customerService;

    public List<OrderCustomerPrice> calculateTotalOrdersPrices() {
        List<OrderDTO> orders = webApiOrderRepository.findAll();

        return orders.stream()
                .map(order -> {
                    BigDecimal total = order.getOrderItems().stream()
                            .map(item -> {
                                Product product = productService.getProductById(item.getProductId());
                                return product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                            })
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    return OrderCustomerPrice.builder()
                            .orderId(order.getId())
                            .CustomerId(customerService.getCustomerById(order.getCustomerId()).getId())
                            .totalPrice(total)
                            .build();
                })
                .collect(Collectors.toList());
    }
}