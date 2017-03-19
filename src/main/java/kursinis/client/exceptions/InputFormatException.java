package kursinis.client.exceptions;

public class InputFormatException extends Exception {
    private int lineNumber = -1;

    public InputFormatException(String message) {
        super(message);
    }

    public InputFormatException(String message, int lineNr) {
        super(message);
        lineNumber = lineNr;
    }

    public void setLineNumber(int lineNr) {
        lineNumber = lineNr;
    }

    public int getLineNr() {
        return lineNumber;
    }
}
