package ru.maliutin.storage.domain.exception;

/**
 * Собственное исключение при отсутствии товара.
 */
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
