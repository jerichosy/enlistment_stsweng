package com.orangeandbronze.enlistment;

public class ExceededUnitLimitException extends RuntimeException{
    public ExceededUnitLimitException(String msg) {
        super(msg);
    }
}
