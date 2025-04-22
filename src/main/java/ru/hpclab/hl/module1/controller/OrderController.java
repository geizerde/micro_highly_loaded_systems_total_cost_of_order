package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hpclab.hl.module1.model.order.OrderCustomerPrice;
import ru.hpclab.hl.module1.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/total-prices")
    public List<OrderCustomerPrice> calculateTotalOrdersPrices() {
        return service.calculateTotalOrdersPrices();
    }
}
