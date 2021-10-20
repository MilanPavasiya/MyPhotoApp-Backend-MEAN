package com.myphotoapp.exception;

public class InvalidIdToken extends Exception {

    @Override
    public String getMessage() {
        return "Invalid IdToken";
    }
}
