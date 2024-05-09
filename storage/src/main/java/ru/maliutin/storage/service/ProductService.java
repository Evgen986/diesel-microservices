package ru.maliutin.storage.service;

import ru.maliutin.storage.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);

    void deleteProductById(Long id);

    Product updateProduct(Long id, Product updateProduct);
}
