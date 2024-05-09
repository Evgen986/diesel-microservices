package ru.maliutin.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maliutin.storage.domain.Product;
import ru.maliutin.storage.domain.exception.ProductNotFoundException;
import ru.maliutin.storage.repository.ProductRepository;
import ru.maliutin.storage.service.ProductService;
import ru.maliutin.storage.service.TechnicService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final TechnicService technicService;
    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Товар с id %d не найден!".formatted(id)));
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        product.setTechnics(technicService.checkNewTechnic(product.getTechnics()));
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product updateProduct) {
        Product currentProduct = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Товар с id %d не найден!".formatted(id)));
        currentProduct.setTitle(updateProduct.getTitle());
        currentProduct.setCatalogueNumber(updateProduct.getCatalogueNumber());
        currentProduct.setProgramNumber(updateProduct.getProgramNumber());
        currentProduct.setBalance(updateProduct.getBalance());
        currentProduct.setPrice(updateProduct.getPrice());
        currentProduct.setTechnics(technicService.checkNewTechnic(updateProduct.getTechnics()));
        return productRepository.save(currentProduct);
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
