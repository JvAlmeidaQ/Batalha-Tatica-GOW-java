package br.com.BatalhaTatica.exceptions;

public class AtaqueInvalidoException extends IllegalStateException {
    public AtaqueInvalidoException(String message) {
        super(message);
    }
}
