package modelo;

import exceptions.ValorInvalidoException;
import exceptions.ValorNuloException;

public class ItemVenda {
    private FichaTecnica fichaTecnica;
    private double precoVenda;
    private int quantidade;

    private static final double FATOR_MAJORACAO_BEBIDA = 1.45; // 45%
    private static final double FATOR_MAJORACAO_ALIMENTO = 1.35; // 35%

    public ItemVenda(FichaTecnica fichaTecnica, int quantidade) throws ValorNuloException, ValorInvalidoException {
        if (fichaTecnica == null) {
            throw new ValorNuloException("A ficha técnica não pode ser nula para criar um item de venda.");
        }
        if (quantidade <= 0) {
            throw new ValorInvalidoException("A quantidade do item deve ser maior que zero.");
        }
        this.fichaTecnica = fichaTecnica;
        this.quantidade = quantidade;
        this.precoVenda = calcularPrecoVenda();
    }

    private double calcularPrecoVenda() {
        double custo = fichaTecnica.calcularCustoPorPorcao();
        String categoria = fichaTecnica.getCategoria();

        if (categoria != null && (categoria.equalsIgnoreCase("Bebida") || categoria.equalsIgnoreCase("Bebidas"))) {
            return custo * FATOR_MAJORACAO_BEBIDA;
        } else {
            return custo * FATOR_MAJORACAO_ALIMENTO;
        }
    }

    /**
     * Calcula o lucro total para este item de venda (lucro unitário * quantidade).
     * @return O lucro total do item.
     */
    public double calcularLucroTotalItem() {
        double custoTotal = fichaTecnica.calcularCustoPorPorcao() * quantidade;
        return getSubtotal() - custoTotal;
    }

    // Getters
    public FichaTecnica getFichaTecnica() { return fichaTecnica; }
    public double getPrecoVenda() { return precoVenda; }
    public int getQuantidade() { return quantidade; }
    public double getSubtotal() { return precoVenda * quantidade; }

    @Override
    public String toString() {
        return String.format("Item: %s, Qtd: %d, Preço Unit: R$%.2f, Subtotal: R$%.2f",
                fichaTecnica.getNome(), quantidade, precoVenda, getSubtotal());
    }
}
