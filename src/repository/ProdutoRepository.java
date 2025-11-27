package repository;

import exceptions.IDExistenteException;
import exceptions.RepositorioCheioException;
import modelo.Produto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class ProdutoRepository {
    private static int CAPACIDADE_MAXIMA = 1000;
    private Produto[] itemEstoque;
    private int proximaPosicao;

    public ProdutoRepository() {
        this.itemEstoque = new Produto[CAPACIDADE_MAXIMA];
        this.proximaPosicao = 0;
    }

    //adicionar produto ao estoque
    public void adicionarProduto(Produto produto) throws RepositorioCheioException, IDExistenteException {
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
    public boolean editarProduto(String id, Produto novoProduto) {
        for (int i = 0; i < proximaPosicao; i++) {
            if (itemEstoque[i].getId().equals(id)) {
                itemEstoque[i] = novoProduto;
                return true;
            }
        }
        return false;
    }

    //remover produto do estoque
    public boolean removerProduto(String id) {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximaPosicao; i++) {
            if (itemEstoque[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            return false; // Produto não encontrado
        }

        // Desloca os elementos para a esquerda
        for (int i = indiceParaRemover; i < proximaPosicao - 1; i++) {
            itemEstoque[i] = itemEstoque[i + 1];
        }
        itemEstoque[proximaPosicao - 1] = null; // Limpa a última posição
        proximaPosicao--;
        return true;
    }

    //listar produtos do estoque
    public void listarProdutos() {
        if (proximaPosicao == 0) {
            System.out.println("O estoque está vazio.");
        } else {
            for (int i = 0; i < proximaPosicao; i++) {
                Produto produto = itemEstoque[i];
                System.out.println("ID: " + produto.getId() +
                        " | Nome: " + produto.getNome() +
                        " | Quantidade: " + produto.getQuantidade() +
                        " | Categoria: " + produto.getCategoria() +
                        " | Vencimento: " + produto.getDataVencimento());

                //alerta estoque zerado
                if (produto.getQuantidade() == 0) {
                    System.out.println("ALERTA: O produto '" + produto.getNome() + "' está com o estoque zerado!");
                }

                //alerta vencimento
                if (produto.getDataVencimento() != null) {
                    long diasRestantes = ChronoUnit.DAYS.between(LocalDateTime.now(), produto.getDataVencimento());
                    if (diasRestantes <= 30 && diasRestantes >= 0) {
                        System.out.println("ALERTA: O produto '" + produto.getNome() + "' vence em " + diasRestantes + " dias!");
                    }
                }
            }
        }
    }

    //buscar produto por nome
    public Produto buscarProduto(String nome) {
        for (int i = 0; i < proximaPosicao; i++) {
            if (itemEstoque[i].getNome().equalsIgnoreCase(nome)) {
                return itemEstoque[i];
            }
        }
        return null;
    }

    //retorna uma cópia do array de produtos
    public Produto[] buscarTodos() {
        return Arrays.copyOf(itemEstoque, proximaPosicao);
    }
}