package com.example.psb_click.exceptions;

import com.example.psb_click.dto.response.JErrorRes;

public class CustomException extends RuntimeException{

    public CustomException(JErrorRes errorRes) {
        super(errorRes.getMessage());
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
