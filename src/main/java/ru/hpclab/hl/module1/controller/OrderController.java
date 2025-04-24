package ru.hpclab.hl.module1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hpclab.hl.module1.model.order.OrderCustomerPrice;
import ru.hpclab.hl.module1.service.OrderService;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final ObservabilityService observabilityService;

    @GetMapping("/total-prices")
    public List<OrderCustomerPrice> calculateTotalOrdersPrices() {
        observabilityService.start(getClass().getSimpleName() + ":calculateTotalOrdersPrices");

        List<OrderCustomerPrice> result = service.calculateTotalOrdersPrices();

        observabilityService.stop(getClass().getSimpleName() + ":calculateTotalOrdersPrices");

        return result;
    }
}
