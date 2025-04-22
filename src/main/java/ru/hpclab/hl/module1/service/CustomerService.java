package ru.hpclab.hl.module1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.client.WebApiCustomerClient;
import ru.hpclab.hl.module1.model.Customer;

@Service
@AllArgsConstructor
public class CustomerService {
    private final WebApiCustomerClient webApiCustomerClient;

    public Customer getCustomerById(Long id) {
        return webApiCustomerClient.findCustomerById(id);
    }
}
