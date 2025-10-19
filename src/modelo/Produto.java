package model;

import java.time.LocalDate;

public class Produto {
    private String id;
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
}


