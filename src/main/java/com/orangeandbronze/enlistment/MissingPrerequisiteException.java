package com.orangeandbronze.enlistment;

public class MissingPrerequisiteException extends RuntimeException{
    public MissingPrerequisiteException(String msg) {
        super(msg);
    }
}
