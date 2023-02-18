package com.mscourse.creditevaluator.exception;

public class ClientStatusNotFoundException extends Exception {
    
    public ClientStatusNotFoundException() {
        super("Client not found!");
    }
}
