package repository;
import modelo.*;
import java.util.ArrayList;

public class RepositorioFornecedor {
    private ArrayList<Fornecedor> repositorio;

    public RepositorioFornecedor() {
        this.repositorio = new ArrayList<>();
    }
    public ArrayList<Fornecedor> getRepositorio() {
        return repositorio;
    }
    public void adicionarFornecedor(Fornecedor fornecedor) {
        this.repositorio.add(fornecedor);
    }
    public void removerFornecedor(Fornecedor fornecedor) {
        this.repositorio.remove(fornecedor);
    }
    public Fornecedor buscarFornecedor(String ID) {
        for (Fornecedor fornecedor : repositorio) {
            if (fornecedor.getId().equals(ID)) {
                return fornecedor;
            }
        }
        return null;
    }
}
