package modelo;

// Classe ItemRelatorioEngenharia
public class ItemRelatorioEngenharia {
    private String nomePrato;
    private int totalVendido;
    private double lucroPorItem;
    private String classificacao;

    //Construtor inicial
    public ItemRelatorioEngenharia(String nomePrato, int totalVendido, double lucroPorItem) {
        this.nomePrato = nomePrato;
        this.totalVendido = totalVendido;
        this.lucroPorItem = lucroPorItem;
    }

    //Getters
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

    public String toString(){
        return "Prato: " + nomePrato +
                "| Vendido: " + totalVendido +
                "| Lucro: R$" + String.format("%.2f", lucroPorItem) +
                "| Classificação: " + classificacao;
    }

}



