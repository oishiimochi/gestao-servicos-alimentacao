package modelo;

import java.time.LocalDate;

public class Produto {
    private int id;
    private String nome;
    private String unidadeMedida;
    private double quantidade;
    private String categoria;
    private String lote;
    private Fornecedor fornecedor;
    private LocalDate dataVencimento;
    private double custoUnitario;

    public Produto(int id, String nome, String unidadeMedida, double quantidade, String categoria,
                   String lote, Fornecedor fornecedor, LocalDate dataVencimento, double custoUnitario) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.lote = lote;
        this.fornecedor = fornecedor;
        this.dataVencimento = dataVencimento;
        this.custoUnitario = custoUnitario;
    }

    //Getters
    public int getId(){
        return id;}

    public String getNome(){
        return nome;}

    public String getUnidadeMedida(){
        return unidadeMedida;}

    public double getQuantidade(){
        return quantidade;}

    public String getCategoria(){
        return categoria;}

    public String getLote(){
        return lote;}

    public Fornecedor getFornecedor() {
        return fornecedor;}

    public LocalDate getDataVencimento(){
        return dataVencimento;}

    public double getCustoUnitario(){
        return custoUnitario;}

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Setters
    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setCustoUnitario(double custoUnitario) {
        this.custoUnitario = custoUnitario;
    }

    //Calcular valor total
    public double calcularValorTotal() {
        return quantidade * custoUnitario;
    }

    public String toString() {
        return nome + " (" + quantidade + " " + unidadeMedida + ")";
    }
}
