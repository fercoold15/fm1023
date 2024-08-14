package jad.farmacy.Exceptions;

public class ProductNotFoundException  extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
