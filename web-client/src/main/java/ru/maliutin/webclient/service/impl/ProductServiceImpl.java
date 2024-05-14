package ru.maliutin.webclient.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maliutin.webclient.client.StorageClientApi;
import ru.maliutin.webclient.domain.Product;
import ru.maliutin.webclient.service.ProductService;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final StorageClientApi storageClientApi;

    @Override
    public List<Product> getProducts() {
        return storageClientApi.getProducts();
    }
}
