package exceptions;

public class MetodoNaoEncontradoException extends RuntimeException {
    public MetodoNaoEncontradoException(String message) {
        super(message);
    }
}
