package exceptions;

public class IDExistenteException extends Exception {
    public IDExistenteException(String Id, String tipo) {
        super("Existe "+ tipo +" de ID " + Id + " no sistema!");
    }
}
