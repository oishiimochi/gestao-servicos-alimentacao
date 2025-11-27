package cadastro;

import exceptions.IDExistenteException;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.RepositorioCheioException;
import modelo.Produto;
import repository.ProdutoRepository;

public class CadastroProduto {
    private final ProdutoRepository repositorio;

    public CadastroProduto() {
        this.repositorio = new ProdutoRepository();
    }

    public void adicionarProduto(Produto produto) throws RepositorioCheioException, IDExistenteException {
        repositorio.adicionarProduto(produto);
    }

    public boolean editarProduto(String id, Produto novoProduto) {
        return repositorio.editarProduto(id, novoProduto);
    }

    public void removerProduto(String id) throws ProdutoNaoEncontradoException {
        repositorio.removerProduto(id);
    }

    public Produto buscarProdutoPorNome(String nome) throws ProdutoNaoEncontradoException {
        return repositorio.buscarProduto(nome);
    }

    public Produto[] listarTodosProdutos() {
        return repositorio.buscarTodos();
    }
    // criar esse método no ProdutoRepository
    /*
    public String[] getAlertasDeEstoque() {
        return repositorio.getAlertasDeEstoque();
    }
     */
}
