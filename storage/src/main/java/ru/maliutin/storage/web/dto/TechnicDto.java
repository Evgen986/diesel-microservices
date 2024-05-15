package ru.maliutin.storage.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.maliutin.storage.web.dto.validation.OnCreate;
import ru.maliutin.storage.web.dto.validation.OnUpdate;

/**
 * Объект передачи данных о Технике.
 */
@Data
@Schema(description = "TechnicDto")
public class TechnicDto {

    @Schema(description = "Идентификатор техники", example = "3")
    @NotNull(message = "", groups = OnUpdate.class)
    private Long technicId;

    @Schema(description = "Наименование техники", example = "МТЗ")
    @NotNull(message = "Название не может быть пустым",
            groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 3, max = 50, message = "Длинна названия техники, должна быть в диапазоне от 3 до 50 символов!",
            groups = {OnCreate.class, OnUpdate.class})
    private String title;
}
