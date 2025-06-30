package br.com.studentmanagement.enums;

public enum Status {

    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String status;

    private Status(String status) {
        this.status = status;
    }
}
