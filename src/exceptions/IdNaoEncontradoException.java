package exceptions;

public class IdNaoEncontradoException extends Exception {
    private int ID;
    public IdNaoEncontradoException(int ID){
        super("O ID: " + ID + " não foi encontrado no sistema.");
        this.ID = ID;
    }
}
