package Exeptions;

public class IDExistenteException extends RuntimeException {
    private int ID;
    public IDExistenteException(int ID) {
        this.ID = ID;
        super("O ID: " + ID + "já existe no sistema.");
    }
}
