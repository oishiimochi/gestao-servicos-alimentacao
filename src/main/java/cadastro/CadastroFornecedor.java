package cadastro;

import exceptions.IDExistenteException;
import exceptions.IdNaoEncontradoException;
import modelo.Fornecedor;
import repository.RepositorioFornecedor;

public class CadastroFornecedor {
    private RepositorioFornecedor repositorio;

    public CadastroFornecedor() {
        this.repositorio = new RepositorioFornecedor();
    }
    public void adicionarFornecedor(Fornecedor fornecedor) throws IDExistenteException {
        if(repositorio.buscarFornecedor(fornecedor.getId()) == null) {
            repositorio.adicionarFornecedor(fornecedor);
        }
        else {
            throw new IDExistenteException(fornecedor.getId(), "Fornecedor");
        }
    }
    public void removerFornecedor(Fornecedor fornecedor) throws IdNaoEncontradoException {
        if(repositorio.buscarFornecedor(fornecedor.getId()) != null) {
            repositorio.removerFornecedor(fornecedor);
        }
        else {
            throw new IdNaoEncontradoException(fornecedor.getId(), "Fornecedor");
        }
    }
    public Fornecedor buscarFornecedor(String ID) throws IdNaoEncontradoException {
        Fornecedor fornecedor = repositorio.buscarFornecedor(ID);
        if(fornecedor == null) {
            throw new IdNaoEncontradoException(ID, "Fornecedor");
        }
        return fornecedor;
    }
}
