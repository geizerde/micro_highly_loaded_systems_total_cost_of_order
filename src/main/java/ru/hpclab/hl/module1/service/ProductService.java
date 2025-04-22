package ru.hpclab.hl.module1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Product;
import ru.hpclab.hl.module1.repository.webapi.WebApiProductRepository;

@Service
@AllArgsConstructor
public class ProductService {
    private final WebApiProductRepository webApiProductRepository;

    public Product getProductById(Long id) {
        return webApiProductRepository.findProductById(id);
    }
}
