package modelo;

import java.time.LocalDateTime;

//classe Produto
public class Produto {
    private String id;
    private String nome;
    private String unidadeMedida;
    private double quantidade;
    private String categoria;
    private String lote;
    private Fornecedor fornecedor;
    private LocalDateTime dataVencimento;

    // Construtor inicial
    public Produto(String id, String nome, String unidadeMedida, double quantidade,
                   String categoria, String lote, Fornecedor fornecedor, LocalDateTime dataVencimento) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.lote = lote;
        this.fornecedor = fornecedor;
        this.dataVencimento = dataVencimento;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getLote() {
        return lote;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
