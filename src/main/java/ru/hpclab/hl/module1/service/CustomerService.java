package ru.hpclab.hl.module1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Customer;
import ru.hpclab.hl.module1.repository.webapi.WebApiCustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {
    private final WebApiCustomerRepository webApiCustomerRepository;

    public Customer getCustomerById(Long id) {
        return webApiCustomerRepository.findCustomerById(id);
    }
}
