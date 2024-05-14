package ru.maliutin.webclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.maliutin.webclient.domain.Product;

import java.util.List;


@FeignClient(name = "storage")
public interface StorageClientApi {

    @GetMapping("/api-storage/product")
    List<Product> getProducts();

}
