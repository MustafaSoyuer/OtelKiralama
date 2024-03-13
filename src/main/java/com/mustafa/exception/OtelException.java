package com.mustafa.exception;

import lombok.Getter;

@Getter
public class OtelException extends RuntimeException{

    private final ErrorType errorType;
    public OtelException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public OtelException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
