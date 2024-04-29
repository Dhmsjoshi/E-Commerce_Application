package dev.dharamesh.ecomproductservice.exceptions;

public class NoProductPresentException extends RuntimeException{
    public NoProductPresentException(String message) {
        super(message);
    }
}
