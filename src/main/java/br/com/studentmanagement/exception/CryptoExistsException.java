package br.com.studentmanagement.exception;

public class CryptoExistsException extends Exception {

    public CryptoExistsException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;

}
