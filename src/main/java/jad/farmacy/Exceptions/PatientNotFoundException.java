package jad.farmacy.Exceptions;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(String message) {
        super(message);
    }
}
