package br.com.studentmanagement.Enums;

public enum Status {

    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String status;

    private Status(String status) {
        this.status = status;
    }
}
