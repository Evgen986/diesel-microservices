package ru.maliutin.webclient.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.maliutin.webclient.client.StorageClientApi;
import ru.maliutin.webclient.domain.Product;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private StorageClientApi storageClientApi;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void getProductsExpectProducts(){
        List<Product> expectProducts = List.of(new Product());
        when(storageClientApi.getProducts()).thenReturn(expectProducts);
        List<Product> actualProducts = productService.getProducts();
        Assertions.assertEquals(expectProducts, actualProducts);
        verify(storageClientApi).getProducts();
    }
}
