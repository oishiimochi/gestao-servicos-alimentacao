package modelo;

import java.time.LocalDate;

public class Venda {
    private int id;
    private FichaTecnica prato;
    private int quantidadeVendida;
    private double precoUnitario;
    private LocalDate dataVenda;

    public Venda(int id, FichaTecnica prato, int quantidadeVendida, double precoUnitario, LocalDate dataVenda) {
        this.id = id;
        this.prato = prato;
        this.quantidadeVendida = quantidadeVendida;
        this.precoUnitario = precoUnitario;
        this.dataVenda = dataVenda;
    }

    //Getters
    public int getId() { return id; }
    public FichaTecnica getPrato() { return prato; }
    public int getQuantidadeVendida() { return quantidadeVendida; }
    public double getPrecoUnitario() { return precoUnitario; }
    public LocalDate getDataVenda() { return dataVenda; }

    //Setters
    public void setQuantidadeVendida(int quantidadeVendida) { this.quantidadeVendida = quantidadeVendida; }
    public void setPrecoUnitario(double precoUnitario) { this.precoUnitario = precoUnitario; }

    //Calcular receita total
    public double calcularReceitaTotal() {
        return quantidadeVendida * precoUnitario;
    }

    //Calcular lucro bruto
    public double calcularLucroBruto() {
        double custo = prato.calcularCustoPorPorcao() * quantidadeVendida;
        return calcularReceitaTotal() - custo;
    }
}
