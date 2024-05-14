package ru.maliutin.storage.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.maliutin.storage.domain.Product;
import ru.maliutin.storage.domain.Technic;
import ru.maliutin.storage.domain.exception.ProductNotFoundException;
import ru.maliutin.storage.repository.ProductRepository;
import ru.maliutin.storage.service.KafkaProducer;
import ru.maliutin.storage.service.TechnicService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private TechnicService technicService;
    @Mock
    private KafkaProducer kafkaProducer;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void getAllProductExtendsProducts() {
        List<Product> expectProducts = Arrays.asList(new Product());
        when(productRepository.findAll()).thenReturn(expectProducts);
        List<Product> actualProducts = productService.getAllProducts();
        assertEquals(expectProducts, actualProducts);
        verify(productRepository).findAll();
    }

    @Test
    public void getProductByIdExpectProduct() {
        Long productId = 1L;
        Product expectProduct = new Product();
        expectProduct.setProductId(productId);
        when(productRepository.findById(productId))
                .thenReturn(Optional.of(expectProduct));
        Product actualProduct = productService.getProductById(productId);
        assertEquals(expectProduct, actualProduct);
        verify(productRepository).findById(productId);
    }

    @Test
    public void getProductByIdExceptProductNotFoundException() {
        Long productId = 1L;
        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ProductNotFoundException.class,
                () -> productService.getProductById(productId));
        verify(productRepository).findById(productId);
    }

    @Test
    public void createProductExpectProduct() {
        Product expectProduct = new Product();
        Set<Technic> expectTechnics = Set.of(new Technic());
        expectProduct.setTechnics(expectTechnics);
        when(technicService.checkNewTechnic(expectProduct.getTechnics()))
                .thenReturn(expectTechnics);
        when(productRepository.save(expectProduct)).thenReturn(expectProduct);
        doNothing().when(kafkaProducer).sendMessage(List.of(expectProduct));
        Product actualProduct = productService.createProduct(expectProduct);
        assertEquals(expectProduct, actualProduct);
        verify(technicService).checkNewTechnic(expectTechnics);
        verify(productRepository).save(expectProduct);
        verify(kafkaProducer).sendMessage(List.of(expectProduct));
    }

    @Test
    public void updateProductExceptProduct() {
        Long productId = 1L;
        Product updateProduct = new Product();
        updateProduct.setTitle("update title");
        updateProduct.setCatalogueNumber("update catalogue");
        updateProduct.setBalance(1);
        updateProduct.setPrice(new BigDecimal(1));
        Set<Technic> technics = Set.of(new Technic());
        updateProduct.setTechnics(technics);
        Product expectProduct = new Product();
        when(productRepository.findById(productId))
                .thenReturn(Optional.of(expectProduct));
        when(technicService.checkNewTechnic(technics))
                .thenReturn(technics);
        when(productRepository.save(expectProduct)).thenReturn(expectProduct);

        Product actualProduct = productService.updateProduct(productId, updateProduct);
        assertEquals(expectProduct, actualProduct);
        verify(productRepository).findById(productId);
        verify(technicService).checkNewTechnic(technics);
        verify(productRepository).save(expectProduct);
    }

    @Test
    public void updateProductExceptProductNotFoundException() {
        Long productId = 1L;
        Product updateProduct = new Product();
        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class,
                () -> productService.updateProduct(productId, updateProduct));
        verify(productRepository).findById(productId);
        verify(technicService, never()).checkNewTechnic(any());
        verify(productRepository, never()).save(any());
    }

    @Test
    public void deleteProductByIdExpectCorrect() {
        Long productId = 1L;
        doNothing().when(productRepository).deleteById(productId);
        productService.deleteProductById(productId);
        verify(productRepository).deleteById(productId);
    }
}
