package modelo;


import java.time.LocalDate;
import java.util.List;

//Representa um único relatório de engenharia
public class RelatorioEngenharia {

    private int id;
    private LocalDate dataGeracao;
    private List<ItemRelatorioEngenharia> itensDoRelatorio; // A lista de pratos

    public RelatorioEngenharia(int id, List<ItemRelatorioEngenharia> itensDoRelatorio) {
        this.id = id;
        this.dataGeracao = LocalDate.now();
        this.itensDoRelatorio = itensDoRelatorio;
    }

    // Getters
    public int getId() {
        return id;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public List<ItemRelatorioEngenharia> getItensDoRelatorio() {
        return itensDoRelatorio;
    }


    public String toString() {
        return "Relatório ID: " + id + " (Gerado em: " + dataGeracao + ") - " + itensDoRelatorio.size() + " itens.";
    }
}