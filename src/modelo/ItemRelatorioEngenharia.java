package modelo;

// Representa um único item dentro do Relatório de Engenharia

public class ItemRelatorioEngenharia {
    private String nomePrato;
    private int totalVendido;
    private double lucroPorItem;
    private int idPrato;
    private String classificacao;

    //Construtor inicial
    public ItemRelatorioEngenharia(int idPrato, String nomePrato, int totalVendido, double lucroPorItem) {
        this.idPrato = idPrato;
        this.nomePrato = nomePrato;
        this.totalVendido = totalVendido;
        this.lucroPorItem = lucroPorItem;
    }

    //Getters
    public int getIdPrato() { return idPrato; }
    public String getNomePrato() {return nomePrato; }
    public int getTotalVendido() {return totalVendido; }
    public double getLucroPorItem() {return lucroPorItem; }
    public String getClassificacao() {return classificacao; }

    //Setter
    public void setTotalVendido(int totalVendido) {
        this.totalVendido = totalVendido;
    }
    public void setClassificacao(String classificacao){
        this.classificacao = classificacao;
    }
    // To String
    public String toString(){
        return "Prato: " + nomePrato +
                "| Vendido: " + totalVendido +
                "| Lucro: R$" + String.format("%.2f", lucroPorItem) +
                "| Classificação: " + classificacao;
    }
}

