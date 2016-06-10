package com.epam.electronics.exception;

public class NoElectronicsException extends Exception {
    public String getMessage() {
        return "[NoElectronicsException]: There is no electronic in your flat";
    }
}
