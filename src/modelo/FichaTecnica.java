package modelo;

import exceptions.ValorNuloException;
import java.util.Arrays;

public class FichaTecnica {
    private int id;
    private String nome;
    private String categoria;
    private String modoPreparo;
    private int rendimento; // quantas porções
    private RequisitoReceita[] requisitos;
    private int numeroDeRequisitos;
    private static final int CAPACIDADE_INICIAL = 10;

    // Construtor (sem custos operacionais)
    public FichaTecnica(int id, String nome, String categoria, String modoPreparo, int rendimento) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.modoPreparo = modoPreparo;
        this.rendimento = rendimento;
        this.requisitos = new RequisitoReceita[CAPACIDADE_INICIAL];
        this.numeroDeRequisitos = 0;
    }

    //Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public String getModoPreparo() { return modoPreparo; }
    public int getRendimento() { return rendimento; }
    public RequisitoReceita[] getRequisitos() {
        return Arrays.copyOf(requisitos, numeroDeRequisitos);
    }

    //Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setModoPreparo(String modoPreparo) { this.modoPreparo = modoPreparo; }
    public void setRendimento(int rendimento) { this.rendimento = rendimento; }

    //Adicionar requisito
    public void adicionarRequisito(RequisitoReceita requisito) throws ValorNuloException {
        if (requisito == null) {
            throw new ValorNuloException("O requisito a ser adicionado não pode ser nulo.");
        }
        if (numeroDeRequisitos == requisitos.length) {
            requisitos = Arrays.copyOf(requisitos, requisitos.length * 2);
        }
        this.requisitos[numeroDeRequisitos] = requisito;
        this.numeroDeRequisitos++;
    }

    private double calcularCustoTotalIngredientes() {
        double total = 0;
        for (int i = 0; i < numeroDeRequisitos; i++) {
            total += requisitos[i].calcularCustoRequisito();
        }
        return total;
    }

    /**
     * Calcula o custo de uma porção, baseado apenas no custo dos ingredientes.
     * @return O custo da porção.
     */
    public double calcularCustoPorPorcao() {
        if (rendimento <= 0) {
            return 0; // Evita divisão por zero
        }
        return calcularCustoTotalIngredientes() / rendimento;
    }
}
