package modelo;

import java.util.ArrayList;

public class FichaTecnica {
    private int id;
    private String nome;
    private String categoria;
    private String modoPreparo;
    private int rendimento; // quantas porções
    private ArrayList<Produto> ingredientes;

    // Construtor
    public FichaTecnica(int id, String nome, String categoria, String modoPreparo, int rendimento) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.modoPreparo = modoPreparo;
        this.rendimento = rendimento;
        this.ingredientes = new ArrayList<>();
    }
    //Getters

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public String getModoPreparo() { return modoPreparo; }
    public int getRendimento() { return rendimento; }
    public ArrayList<Produto> getIngredientes() { return ingredientes; }

    //Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setModoPreparo(String modoPreparo) { this.modoPreparo = modoPreparo; }
    public void setRendimento(int rendimento) { this.rendimento = rendimento; }

    //Adicionar ingrediente
    public void adicionarIngrediente(Produto produto) {
        ingredientes.add(produto);
    }

    public double calcularCustoTotal() {
        double total = 0;
        for (Produto p : ingredientes) {
            total += p.getCustoUnitario();
        }
        return total;
    }

    //Calcular custo por porção
    public double calcularCustoPorPorcao() {
        return calcularCustoTotal() / rendimento;
    }
}
