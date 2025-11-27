package modelo;

import exceptions.ValorInvalidoException;
import exceptions.ValorNuloException;

import java.time.LocalDateTime;

//classe Produto
public class Produto {
    private final String id;
    private final String nome;
    private final String unidadeMedida;
    private double quantidade;
    private String categoria;
    private String lote;
    private Fornecedor fornecedor;
    private LocalDateTime dataVencimento;
    private double custoUnitario;

    // Construtor
    public Produto(String id, String nome, String unidadeMedida, double quantidade,
                   String categoria, String lote, Fornecedor fornecedor, LocalDateTime dataVencimento, double custoUnitario) throws ValorNuloException, ValorInvalidoException {
        if (id == null || id.trim().isEmpty()) {
            throw new ValorNuloException("O ID do produto não pode ser nulo ou vazio.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValorNuloException("O nome do produto não pode ser nulo ou vazio.");
        }
        if (unidadeMedida == null || unidadeMedida.trim().isEmpty()) {
            throw new ValorNuloException("A unidade de medida do produto não pode ser nula ou vazia.");
        }
        if (quantidade < 0) {
            throw new ValorInvalidoException("A quantidade do produto não pode ser negativa.");
        }
        if (custoUnitario < 0) {
            throw new ValorInvalidoException("O custo unitário do produto não pode ser negativo.");
        }

        this.id = id;
        this.nome = nome;
        // Normaliza a unidade de medida para minúsculas (ex: "KG" -> "kg") para conversões consistentes
        this.unidadeMedida = unidadeMedida.trim().toLowerCase();
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.lote = lote;
        this.fornecedor = fornecedor;
        this.dataVencimento = dataVencimento;
        this.custoUnitario = custoUnitario;
    }

    // Getters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public double getQuantidade() { return quantidade; }
    public String getCategoria() { return categoria; }
    public String getLote() { return lote; }
    public Fornecedor getFornecedor() { return fornecedor; }
    public LocalDateTime getDataVencimento() { return dataVencimento; }
    public double getCustoUnitario() { return custoUnitario; }

    // Setters para campos que podem mudar
    public void setQuantidade(double quantidade) throws ValorInvalidoException {
        if (quantidade < 0) {
            throw new ValorInvalidoException("A quantidade do produto não pode ser negativa.");
        }
        this.quantidade = quantidade;
    }

    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setLote(String lote) { this.lote = lote; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
    public void setDataVencimento(LocalDateTime dataVencimento) { this.dataVencimento = dataVencimento; }
    public void setCustoUnitario(double custoUnitario) throws ValorInvalidoException {
        if (custoUnitario < 0) {
            throw new ValorInvalidoException("O custo unitário do produto não pode ser negativo.");
        }
        this.custoUnitario = custoUnitario;
    }

    public double calcularValorTotalEmEstoque() {
        return this.quantidade * this.custoUnitario;
    }
}
