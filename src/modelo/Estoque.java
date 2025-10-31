package modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> itemEstoque;

    public Estoque() {
        this.itemEstoque = new ArrayList<>();
    }

    //adicionar produto ao estoque
    public void adicionarProduto(Produto produto) {
        itemEstoque.add(produto);
    }

    //editar item do estoque
    public boolean editarProduto(String id, Produto novoProduto) {
        for (int i = 0; i < itemEstoque.size(); i++) {
            if (itemEstoque.get(i).getId().equals(id)) {
                itemEstoque.set(i, novoProduto);
                return true;
            }
        }
        return false;
    }

    //remover produto do estoque
    public boolean removerProduto(String id) {
        for (int i = 0; i < itemEstoque.size(); i++) {
            if (itemEstoque.get(i).getId().equals(id)) {
                itemEstoque.remove(i);
                return true;
            }
        }
        return false;
    }

    //listar produtos do estoque
    public void listarProdutos() {
        if (itemEstoque.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            for (Produto p : itemEstoque) {
                System.out.println("ID: " + p.getId() +
                        " | Nome: " + p.getNome() +
                        " | Quantidade: " + p.getQuantidade() +
                        " | Categoria: " + p.getCategoria() +
                        " | Vencimento: " + p.getDataVencimento());

                //alerta estoque zerado
                if (p.getQuantidade() == 0) {
                    System.out.println("ALERTA: O produto '" + p.getNome() + "' está com o estoque zerado!");
                }

                //alerta vencimento
                if (p.getDataVencimento() != null) {
                    long diasRestantes = ChronoUnit.DAYS.between(LocalDateTime.now(), p.getDataVencimento());
                    if (diasRestantes <= 30 && diasRestantes >= 0) {
                        System.out.println("ALERTA: O produto '" + p.getNome() + "' vence em " + diasRestantes + " dias!");
                    }
                }
            }
        }
    }

    //buscar produto
    public Produto buscarProduto(String nome) {
        for (Produto p : itemEstoque) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
}
