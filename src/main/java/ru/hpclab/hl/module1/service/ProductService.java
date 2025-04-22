package ru.hpclab.hl.module1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.client.WebApiProductClient;
import ru.hpclab.hl.module1.model.Product;

@Service
@AllArgsConstructor
public class ProductService {
    private final WebApiProductClient webApiProductClient;

    public Product getProductById(Long id) {
        return webApiProductClient.findProductById(id);
    }
}
