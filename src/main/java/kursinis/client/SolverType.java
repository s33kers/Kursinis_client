package kursinis.client;

public enum SolverType {

    BACKWARD_CHAIN("Atbulinio išvedimo algoritmas"),
    FORWARD_CHAIN("Tiesioginio išvedimo algoritmas");

    private String value;

    SolverType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
