package exceptions;

public class IdNaoEncontradoException extends Exception {
    private int ID;
    public IdNaoEncontradoException(int ID){
        super("O ID: " + ID + " não foi encontrado.");
        this.ID = ID;
    }
}
