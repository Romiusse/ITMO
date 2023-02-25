package ru.romiusse.box_store.src.Exceptions;

public class SizeCheckException extends RuntimeException {

    public SizeCheckException() {
        super();
    }
    
    public SizeCheckException(String s) {
        super(s);
    }

}
