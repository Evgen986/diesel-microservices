package ru.maliutin.storage.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.maliutin.storage.domain.Product;
import ru.maliutin.storage.service.ProductService;
import ru.maliutin.storage.web.dto.ProductDto;
import ru.maliutin.storage.web.dto.validation.OnCreate;
import ru.maliutin.storage.web.dto.validation.OnUpdate;
import ru.maliutin.storage.web.mappers.ProductMapper;

import java.util.List;

@RestController
@RequestMapping("api-storage/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(productMapper.toDto(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                productMapper.toDto(productService.getProductById(id)));
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@Validated(OnCreate.class)
                                                  @RequestBody ProductDto productDto){
        Product product = productMapper.toEntity(productDto);
        return ResponseEntity.ok(
                productMapper.toDto(
                        productService.createProduct(product)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id,
                                                    @Validated(OnUpdate.class) @RequestBody ProductDto productDto){
        Product updateProduct = productMapper.toEntity(productDto);
        return ResponseEntity.ok(productMapper.toDto(productService.updateProduct(id, updateProduct)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProductById(id);
        return ResponseEntity.ok(null);
    }
}
