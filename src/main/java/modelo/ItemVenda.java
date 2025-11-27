package modelo;

import exceptions.ValorInvalidoException;
import exceptions.ValorNuloException;

public class ItemVenda {
    private FichaTecnica prato;
    private double precoVenda;
    private int quantidade;

    public ItemVenda(FichaTecnica prato) throws ValorNuloException, ValorInvalidoException {
        this(prato, 1); // Assume quantidade 1 por padrão para o catálogo
    }

    public ItemVenda(FichaTecnica prato, int quantidade) throws ValorNuloException, ValorInvalidoException {
        if (prato == null) {
            throw new ValorNuloException("A ficha técnica não pode ser nula para criar um item de venda.");
        }
        if (quantidade <= 0) {
            throw new ValorInvalidoException("A quantidade do item deve ser maior que zero.");
        }
        this.prato = prato;
        this.quantidade = quantidade;
        this.precoVenda = calcularPrecoVenda();
    }

    private double calcularPrecoVenda() {
        double custo = prato.calcularCustoPorPorcao();
        String categoria = prato.getCategoria();

        if (categoria != null && (categoria.equalsIgnoreCase("Bebida") || categoria.equalsIgnoreCase("Bebidas"))) {
            return custo * 1.45; // Custo + 45%
        } else {
            return custo * 1.35; // Custo + 35% para Alimentos e outros
        }
    }

    // Getters
    public FichaTecnica getPrato() {
        return prato;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return precoVenda * quantidade;
    }

    @Override
    public String toString() {
        // Ajustado para não mostrar quantidade se for 1 (padrão do catálogo)
        if (this.quantidade == 1) {
            return String.format("Item: %s, Preço de Venda: R$%.2f",
                    prato.getNome(), precoVenda);
        }
        return String.format("Item: %s, Qtd: %d, Preço Unit: R$%.2f, Subtotal: R$%.2f",
                prato.getNome(), quantidade, precoVenda, getSubtotal());
    }
}