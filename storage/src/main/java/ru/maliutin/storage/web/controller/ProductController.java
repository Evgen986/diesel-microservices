package ru.maliutin.storage.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Api складских остатков",
        description = "Позволяет просматривать все товары, товар по id, добавлять, изменять и удалять товары.")
@RequestMapping("api-storage/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    @Operation(summary = "Вывод всех товаров")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(productMapper.toDto(products));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Вывод товара по id")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                productMapper.toDto(productService.getProductById(id)));
    }

    @PostMapping()
    @Operation(summary = "Добавление нового товара",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class),
                    examples = @ExampleObject(name = "Example 1",
                            value = """
                                    {
                                        "title": "Элемент фильтрующий топливный тонкой очистки (МТЗ)",
                                        "catalogueNumber": "009-1102054",
                                        "programNumber": 1785,
                                        "technics": [
                                            {
                                                "title": "МТЗ"
                                            }
                                        ],
                                        "balance": 3,
                                        "price": 900.00
                                    }
                                    """)
            )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "OK")
    )
    public ResponseEntity<ProductDto> createProduct(@Validated(OnCreate.class)
                                                  @RequestBody ProductDto productDto){
        Product product = productMapper.toEntity(productDto);
        return ResponseEntity.ok(
                productMapper.toDto(
                        productService.createProduct(product)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение существующего товара",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class),
                    examples = @ExampleObject(name = "Example 1",
                            value = """
                                    {
                                        "title": "Измененное наименование",
                                        "catalogueNumber": "009-1102054",
                                        "programNumber": 1785,
                                        "technics": [
                                            {
                                                "title": "МТЗ"
                                            }
                                        ],
                                        "balance": 3,
                                        "price": 900.00
                                    }
                                    """)
            )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "OK")
    )
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id,
                                                    @Validated(OnUpdate.class) @RequestBody ProductDto productDto){
        Product updateProduct = productMapper.toEntity(productDto);
        return ResponseEntity.ok(productMapper.toDto(productService.updateProduct(id, updateProduct)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление товара по id")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProductById(id);
        return ResponseEntity.ok(null);
    }
}
