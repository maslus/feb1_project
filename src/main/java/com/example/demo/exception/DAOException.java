package com.example.demo.exception;

public class DAOException extends RuntimeException {
    private String messageDetail;

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, String messageDetail) {
        super(message);
        this.messageDetail = messageDetail;
    }

    public DAOException(Exception e) {
        super(e);
    }

    public String getMessageDetail() {
        return messageDetail;
    }
}
