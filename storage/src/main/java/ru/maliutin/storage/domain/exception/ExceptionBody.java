package ru.maliutin.storage.domain.exception;

import lombok.Data;

import java.util.Map;

/**
 * Типовое тело ответа при возникновении исключения.
 */
@Data
public class ExceptionBody {

    // Сообщение о сгенерированном исключении
    private String message;
    // Коллекция полей классов и ошибок в этих полях при валидации.
    private Map<String, String> errors;

    public ExceptionBody(final String message) {
        this.message = message;
    }

}
