package br.com.studentmanagement.enums;

public enum Shift {

    MATUTINO("Matutino"),
    NIGHT_SHIFT("No Shift");

    private String shift;

    private Shift(String shift) {
        this.shift = shift;
    }
}
