package repository;

import exceptions.IDExistenteException;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.RepositorioCheioException;
import modelo.Produto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class ProdutoRepository {
    private static final int CAPACIDADE_MAXIMA = 1000;
    private static final int DIAS_ALERTA_VENCIMENTO = 7;
    private Produto[] itemEstoque;
    private int proximaPosicao;

    public ProdutoRepository() {
        this.itemEstoque = new Produto[CAPACIDADE_MAXIMA];
        this.proximaPosicao = 0;
    }

    //adicionar produto ao estoque
    public void adicionarProdutoAoEstoque(Produto produto) throws RepositorioCheioException, IDExistenteException {
        if (proximaPosicao >= CAPACIDADE_MAXIMA) {
            throw new RepositorioCheioException("O estoque de produtos está cheio.");
        }
        // Opcional: Verificar se já existe um produto com o mesmo ID
        for (int i = 0; i < proximaPosicao; i++) {
            if (itemEstoque[i].getId().equals(produto.getId())) {
                throw new IDExistenteException(produto.getId(), "Produto");
            }
        }
        itemEstoque[proximaPosicao] = produto;
        proximaPosicao++;
    }

    //editar item do estoque
    public boolean editarProdutoNoEstoque(String id, Produto novoProduto) {
        for (int i = 0; i < proximaPosicao; i++) {
            if (itemEstoque[i].getId().equals(id)) {
                itemEstoque[i] = novoProduto;
                return true;
            }
        }
        return false;
    }

    //remover produto do estoque
    public boolean removerProdutoDoEstoque(String id) throws ProdutoNaoEncontradoException {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximaPosicao; i++) {
            if (itemEstoque[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado para remoção.");
        }

        for (int i = indiceParaRemover; i < proximaPosicao - 1; i++) {
            itemEstoque[i] = itemEstoque[i + 1];
        }
        itemEstoque[proximaPosicao - 1] = null;
        proximaPosicao--;
        return true;
    }

    //verificar alertas de estoque
    public String[] getAlertasDeEstoque() {
        String[] alertas = new String[proximaPosicao * 2];
        int contadorAlertas = 0;

        for (int i = 0; i < proximaPosicao; i++) {
            Produto produto = itemEstoque[i];
            if (produto.getQuantidade() == 0) {
                alertas[contadorAlertas++] = "ALERTA: O produto '" + produto.getNome() + "' está com o estoque zerado!";
            }

            if (produto.getDataVencimento() != null) {
                long diasRestantes = ChronoUnit.DAYS.between(LocalDateTime.now(), produto.getDataVencimento());
                if (diasRestantes <= DIAS_ALERTA_VENCIMENTO && diasRestantes >= 0) {
                    alertas[contadorAlertas++] = "ALERTA: O produto '" + produto.getNome() + "' vence em " + diasRestantes + " dias!";
                }
            }
        }
        
        if (contadorAlertas == 0) {
            return new String[0];
        }

        return Arrays.copyOf(alertas, contadorAlertas);
    }

    public Produto buscarProdutoPorNome(String nome) throws ProdutoNaoEncontradoException {
        for (int i = 0; i < proximaPosicao; i++) {
            if (itemEstoque[i].getNome().equalsIgnoreCase(nome)) {
                return itemEstoque[i];
            }
        }
        throw new ProdutoNaoEncontradoException("Produto com nome '" + nome + "' não encontrado.");
    }

    //retorna uma cópia do array de produtos
    public Produto[] buscarTodos() {
        return Arrays.copyOf(itemEstoque, proximaPosicao);
    }
}
