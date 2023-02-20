package com.example.psb_click.exceptions;

public class SqlException extends RuntimeException{

    public SqlException(String message) {
        super(message);
    }

    public SqlException(String message, Throwable cause) {
        super(message, cause);
    }
}
