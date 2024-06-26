package ru.maliutin.storage.web.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.maliutin.storage.domain.exception.ExceptionBody;
import ru.maliutin.storage.domain.exception.ProductNotFoundException;
import ru.maliutin.storage.domain.exception.ResourceMappingException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс перехватывающий исключения и возвращающий их на фронт в удобном виде.
 */
@RestControllerAdvice
public class ControllerAdvice {

    /**
     * Исключение при ненахождении данных в БД.
     *
     * @param e исключение ProductNotFoundException
     * @return объект ExceptionBody
     */
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleProductNotFound(
            final ProductNotFoundException e) {
        return new ExceptionBody(e.getMessage());
    }

    /**
     * Исключение при преобразовании объектов.
     *
     * @param e исключение ResourceNotFoundException
     * @return объект ExceptionBody
     */
    @ExceptionHandler(ResourceMappingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleResourceMapping(
            final ResourceMappingException e) {
        return new ExceptionBody(e.getMessage());
    }

    /**
     * Исключение генерируемое при валидации данных.
     *
     * @param e исключение MethodArgumentNotValidException.
     * @return объект ExceptionBody.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleMethodArgumentNotValid(
            final MethodArgumentNotValidException e) {
        ExceptionBody exceptionBody = new ExceptionBody("Validation failed");
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        exceptionBody.setErrors(errors.stream()
                .collect(Collectors
                        .toMap(FieldError::getField,
                                FieldError::getDefaultMessage)));
        return exceptionBody;
    }

    /**
     * Исключение генерируемое при валидации данных.
     *
     * @param e исключение ConstraintViolationException
     * @return объект ExceptionBody
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleConstraintViolation(
            final ConstraintViolationException e) {
        ExceptionBody exceptionBody = new ExceptionBody("Validation failed");
        exceptionBody.setErrors(e.getConstraintViolations()
                .stream().collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        violation -> violation.getMessage()
                )));
        return exceptionBody;
    }
}
