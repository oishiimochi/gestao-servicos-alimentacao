package modelo;

import exceptions.IDExistenteException;
import exceptions.IdNaoEncontradoException;
import repository.RepositorioFornecedor;

import java.util.ArrayList;

public class CadFornecedor {
    private RepositorioFornecedor repositorio;

    public CadFornecedor() {
        this.repositorio = new RepositorioFornecedor();
    }
    public ArrayList<Fornecedor> getRepositorio() {
        return repositorio.getRepositorio();
    }
    public void AdicionarFornecedor(Fornecedor fornecedor) throws IDExistenteException {
        if(repositorio.buscarFornecedor(fornecedor.getId()) == null) {
            repositorio.adicionarFornecedor(fornecedor);
        }
        else {
            throw new IDExistenteException(fornecedor.getId());
        }
    }
    public void RemoverFornecedor(Fornecedor fornecedor) {
        if(repositorio.buscarFornecedor(fornecedor.getId()) != null) {
            repositorio.removerFornecedor(fornecedor);
        }
        else {
            throw new IdNaoEncontradoException(fornecedor.getId());
        }
    }
    public Fornecedor buscarFornecedor(int ID) {
        return repositorio.buscarFornecedor(ID);
    }
}
