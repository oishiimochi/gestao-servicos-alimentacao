package modelo;

import exceptions.ValorNuloException;
import java.time.LocalDate;
import java.util.Arrays;

public class Venda {
    private static int proximoId = 1;
    private int id;
    private LocalDate dataVenda;
    private ItemVenda[] itens;
    private int numeroDeItens;
    private static int CAPACIDADE_INICIAL = 10;

    public Venda(LocalDate dataVenda) throws ValorNuloException {
        if (dataVenda == null) {
            throw new ValorNuloException("A data da venda não pode ser nula.");
        }
        this.id = proximoId++;
        this.dataVenda = dataVenda;
        this.itens = new ItemVenda[CAPACIDADE_INICIAL];
        this.numeroDeItens = 0;
    }

    public void adicionarItem(ItemVenda item) throws ValorNuloException {
        if (item == null) {
            throw new ValorNuloException("O item a ser adicionado não pode ser nulo.");
        }
        if (numeroDeItens == itens.length) {
            // Redimensiona o array se estiver cheio
            this.itens = Arrays.copyOf(itens, itens.length * 2);
        }
        this.itens[numeroDeItens] = item;
        this.numeroDeItens++;
    }

    // Getters
    public int getId() {
        return id;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public ItemVenda[] getItens() {
        return Arrays.copyOf(itens, numeroDeItens);
    }

    // Métodos de cálculo para ESTA venda
    public double calcularReceitaTotal() {
        double total = 0;
        for (int i = 0; i < numeroDeItens; i++) {
            total += itens[i].getSubtotal();
        }
        return total;
    }

    public double calcularLucroBruto() {
        double receitaTotal = calcularReceitaTotal();
        double custoTotal = 0;
        for (int i = 0; i < numeroDeItens; i++) {
            custoTotal += itens[i].getFichaTecnica().calcularCustoPorPorcao() * itens[i].getQuantidade();
        }
        return receitaTotal - custoTotal;
    }

    @Override
    public String toString() {
        return String.format("Venda ID: %d, Data: %s, Itens: %d, Receita Total: R$%.2f, Lucro Total: R$%.2f",
                id, dataVenda, numeroDeItens, calcularReceitaTotal(), calcularLucroBruto());
    }
}