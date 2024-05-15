package ru.maliutin.storage.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.maliutin.storage.web.dto.validation.OnCreate;
import ru.maliutin.storage.web.dto.validation.OnUpdate;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Объект передачи данных о Товаре.
 */
@Data
@Schema(description = "ProductDto")
public class ProductDto {

    @Schema(description = "Идентификатор товара", example = "3")
    @NotNull(message = "Id не может быть пустым",
            groups = OnUpdate.class)
    private long productId;

    @Schema(description = "Название товара", example = "Элемент фильтрующий топливный тонкой очистки (МТЗ)")
    @NotNull(message = "Название не может быть пустым",
            groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 3, max = 200,
            message = "Длинна названия товара, должна быть в диапазоне от 3 до 200 символов!",
            groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Schema(description = "Каталожный номер товара", example = "009-1102054")
    @Size(max = 50,
            message = "Каталожный номер не может быть более 50 символов",
            groups = {OnCreate.class, OnUpdate.class})
    private String catalogueNumber;

    @Schema(description = "Внутренний номер товара", example = "1785")
    @NotNull(message = "Внутренний номер не может быть пустым",
            groups = {OnCreate.class, OnUpdate.class})
    @Min(value = 1, message = "Внутренний номер должен быть больше нуля!",
            groups = {OnCreate.class, OnUpdate.class})
    private int programNumber;

    @Schema(description = "Техника применения",
            example = """
                    [
                                            {
                                                "title": "МТЗ"
                                            }
                                        ]
                    """)
    private Set<TechnicDto> technics;

    @Schema(description = "Остаток товара", example = "3")
    @NotNull(message = "Товар должен иметь остаток",
            groups = {OnCreate.class, OnUpdate.class})
    @Min(value = 0, message = "Остаток товара должен быть больше или равен нулю!",
            groups = {OnCreate.class, OnUpdate.class})
    private int balance;

    @Schema(description = "Стоимость товара", example = "900.00")
    @NotNull(message = "Товар должен иметь цену!",
            groups = {OnCreate.class, OnUpdate.class})
    @Min(value = 1, message = "Цена товара должна быть больше нуля!",
            groups = {OnCreate.class, OnUpdate.class})
    private BigDecimal price;

}
