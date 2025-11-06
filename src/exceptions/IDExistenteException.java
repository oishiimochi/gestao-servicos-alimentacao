package exceptions;

public class IDExistenteException extends Exception {
    private int ID;
    public IDExistenteException(int ID) {
        super("O ID: " + ID + "já existe no sistema.");
        this.ID = ID;
    }
}
