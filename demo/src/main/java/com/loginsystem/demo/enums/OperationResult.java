package com.loginsystem.demo.enums;

public enum OperationResult {
    ACCOUNT_CREATE_SUCCESS("register success"),
    ACCOUNT_CREATE_FAILURE("register failure"),
    LOGIN_SUCCESS("login success"),
    LOGIN_FAILURE("login failure"),
    ACCOUNT_FOUND("query success"),
    ACCOUNT_UPDATE_SUCCESS("update success"),
    ACCOUNT_DELETE_SUCCESS("delete success");

    private final String message;

    OperationResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
