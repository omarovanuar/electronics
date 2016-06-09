package com.epam.electronics.exception;

public class InvalidNewItemException extends Exception {
    public String getMessage() {
        return "[InvalidNewItemException]: Electronic was not added";
    }
}
