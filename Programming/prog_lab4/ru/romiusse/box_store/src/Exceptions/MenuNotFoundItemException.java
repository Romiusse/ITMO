package ru.romiusse.box_store.src.Exceptions;

public class MenuNotFoundItemException extends Exception {

    public MenuNotFoundItemException() {
        super();
    }

    public MenuNotFoundItemException(String message) {
        super(message);
    }

    public MenuNotFoundItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public MenuNotFoundItemException(Throwable cause) {
        super(cause);
    }

}
