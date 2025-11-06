package modelo;

import exceptions.ValorInvalidoException;
import exceptions.ValorNuloException;

import java.time.LocalDate;

public class Venda {
    private static int proximoId = 1;
    private int id;
    private ItemVenda itemVendido;
    private int quantidadeVendida;
    private LocalDate dataVenda;

    public Venda(ItemVenda itemVendido, int quantidadeVendida, LocalDate dataVenda) throws ValorNuloException, ValorInvalidoException {
        if (itemVendido == null) {
            throw new ValorNuloException("O item vendido não pode ser nulo.");
        }
        if (dataVenda == null) {
            throw new ValorNuloException("A data da venda não pode ser nula.");
        }
        if (quantidadeVendida <= 0) {
            throw new ValorInvalidoException("A quantidade vendida deve ser maior que zero.");
        }
        this.id = proximoId++;
        this.itemVendido = itemVendido;
        this.quantidadeVendida = quantidadeVendida;
        this.dataVenda = dataVenda;
    }

    // Getters para compatibilidade com Engenharia de Cardápio
    public int getId() { return id; }
    public FichaTecnica getPrato() { return itemVendido.getPrato(); }
    public int getQuantidadeVendida() { return quantidadeVendida; }
    public double getPrecoUnitario() { return itemVendido.getPrecoVenda(); } // Preço vem do ItemVenda
    public LocalDate getDataVenda() { return dataVenda; }


    // Métodos de cálculo
    public double calcularReceitaTotal() {
        return quantidadeVendida * getPrecoUnitario();
    }

    public double calcularLucroBruto() {
        double custoTotal = getPrato().calcularCustoPorPorcao() * quantidadeVendida;
        return calcularReceitaTotal() - custoTotal;
    }

    @Override
    public String toString() {
        return String.format("Venda ID: %d, Data: %s, Item: %s, Qtd: %d, Receita: R$%.2f, Lucro: R$%.2f",
                id, dataVenda, getPrato().getNome(), quantidadeVendida, calcularReceitaTotal(), calcularLucroBruto());
    }
}