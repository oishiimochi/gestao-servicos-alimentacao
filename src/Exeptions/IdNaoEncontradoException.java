package Exeptions;

public class IdNaoEncontradoException extends RuntimeException {
    private int ID;
    public IdNaoEncontradoException(int ID){
        this.ID = ID;
        super("O ID: " + ID + " não foi encontrado.");
    }
}
