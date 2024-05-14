package ru.maliutin.storage.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllProductExpectProductList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api-storage/product"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                [
                                    {
                                        "productId": 1,
                                        "title": "Накладка тормозная (МАЗ Супер) с заклепками (к-т)  В-160",
                                        "catalogueNumber": "5336-3501105р",
                                        "programNumber": 6526,
                                        "technics": [
                                            {
                                                "technicId": 2,
                                                "title": "КамАЗ"
                                            },
                                            {
                                                "technicId": 1,
                                                "title": "МАЗ"
                                            }
                                        ],
                                        "balance": 5,
                                        "price": 2450.00
                                    },
                                    {
                                        "productId": 2,
                                        "title": "Муфта выключения сцепления в сборе (МАЗ)",
                                        "catalogueNumber": "236-1601180-Б2",
                                        "programNumber": 6010,
                                        "technics": [
                                            {
                                                "technicId": 1,
                                                "title": "МАЗ"
                                            }
                                        ],
                                        "balance": 7,
                                        "price": 1400.00
                                    }
                                ]
                                """)
                );
    }

    @Test
    public void getProductExpectProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api-storage/product/1"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                    "productId": 1,
                                    "title": "Накладка тормозная (МАЗ Супер) с заклепками (к-т)  В-160",
                                    "catalogueNumber": "5336-3501105р",
                                    "programNumber": 6526,
                                    "technics": [
                                        {
                                            "technicId": 2,
                                            "title": "КамАЗ"
                                        },
                                        {
                                            "technicId": 1,
                                            "title": "МАЗ"
                                        }
                                    ],
                                    "balance": 5,
                                    "price": 2450.00
                                }
                                """)
                );
    }

    @Test
    public void getProductExpectProductNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api-storage/product/100000000"))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                     "message": "Товар с id 100000000 не найден!",
                                     "errors": null
                                 }
                                """)
                );
    }

    @Test
    public void createProductExceptProduct() throws Exception {
        String jsonBody = """
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
                """;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api-storage/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpectAll(status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                    "productId": 3,
                                    "title": "Элемент фильтрующий топливный тонкой очистки (МТЗ)",
                                    "catalogueNumber": "009-1102054",
                                    "programNumber": 1785,
                                    "technics": [
                                        {
                                            "technicId": 3,
                                            "title": "МТЗ"
                                        }
                                    ],
                                    "balance": 3,
                                    "price": 900.00
                                }
                                """)
                );
    }

    @Test
    public void createProductExceptBadRequest() throws Exception {
        String jsonBody = """
                {
                     "title": null,
                     "catalogueNumber": "5320-3501105р",
                     "programNumber": 1226,
                     "technics": [
                         {
                             "title": null
                         }
                     ],
                     "balance": -5,
                     "price": -5
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api-storage/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpectAll(status().isBadRequest(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                    "message": "Validation failed",
                                    "errors": {
                                        "balance": "Остаток товара должен быть больше или равен нулю!",
                                        "price": "Цена товара должна быть больше нуля!",
                                        "title": "Название не может быть пустым"
                                    }
                                }
                                """)
                );
    }

    @Test
    public void deleteProductExceptStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api-storage/product/3"))
                .andExpectAll(status().isOk());
    }
}
