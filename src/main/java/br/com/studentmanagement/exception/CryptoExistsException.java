package br.com.studentmanagement.exception;

import java.io.Serial;

public class CryptoExistsException extends Exception {

    public CryptoExistsException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 1L;

}
