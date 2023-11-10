package com.parsakav.echorestapi.response;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field.Please check documentation for required fields.")
    ,RECORD_ALREADY_EXISTS("Record is already exists");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
