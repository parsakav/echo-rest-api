package com.parsakav.echorestapi.exceptions;

import java.io.IOException;

public class UserServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1311465036468710841L;

    public UserServiceException(String message, IOException e) {
        super(message,e);
        // TODO Auto-generated constructor stub
    }

    public UserServiceException(String message) {
        super(message);
    }


}
