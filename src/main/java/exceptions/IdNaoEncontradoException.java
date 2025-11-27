package exceptions;

public class IdNaoEncontradoException extends Exception {
    public IdNaoEncontradoException(String ID, String tipo) {
        super("Não existe "+ tipo +" de ID " + ID + " no sistema!");
    }
}
