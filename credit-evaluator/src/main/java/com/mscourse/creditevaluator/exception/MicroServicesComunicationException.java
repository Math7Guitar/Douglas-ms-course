package com.mscourse.creditevaluator.exception;

public class MicroServicesComunicationException extends Exception {
    
    private Integer status;

    public MicroServicesComunicationException(int status, String msg) {
        super(msg);
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }
}
