package servico;

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
        repositorio.adicionarProdutoAoEstoque(produto);
    }

    public boolean editarProduto(String id, Produto novoProduto) {
        return repositorio.editarProdutoNoEstoque(id, novoProduto);
    }

    public void removerProduto(String id) throws ProdutoNaoEncontradoException {
        repositorio.removerProdutoDoEstoque(id);
    }

    public Produto buscarProdutoPorNome(String nome) throws ProdutoNaoEncontradoException {
        return repositorio.buscarProdutoPorNome(nome);
    }

    public Produto[] listarTodosProdutos() {
        return repositorio.buscarTodos();
    }
    
    public String[] getAlertasDeEstoque() {
        return repositorio.getAlertasDeEstoque();
    }
}
