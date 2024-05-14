package ru.maliutin.webclient.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maliutin.webclient.service.ProductService;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "catalog";
    }
}
