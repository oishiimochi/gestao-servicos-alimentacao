package modelo;

import exceptions.ValorNuloException;

public class ItemVenda {
    private final FichaTecnica prato;
    private final double precoVenda;

    public ItemVenda(FichaTecnica prato) throws ValorNuloException {
        if (prato == null) {
            throw new ValorNuloException("A ficha técnica não pode ser nula para criar um item de venda.");
        }
        this.prato = prato;
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

    public FichaTecnica getPrato() {
        return prato;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    @Override
    public String toString() {
        return String.format("Item: %s, Preço de Venda: R$%.2f",
                prato.getNome(), precoVenda);
    }
}