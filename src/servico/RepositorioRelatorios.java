package servico;

import modelo.RelatorioEngenharia;
import modelo.ItemRelatorioEngenharia;
import java.util.ArrayList;
import java.util.List;

//Gerencia o armazenamento e o histórico dos relatórios

public class RepositorioRelatorios {

    private ArrayList<RelatorioEngenharia> relatoriosArmazenados;
    private int proximoId = 1;

    // Construtor

    public RepositorioRelatorios() {
        this.relatoriosArmazenados = new ArrayList<>();
    }

    // Adiciona Relatório

    public RelatorioEngenharia adicionarRelatorio(List<ItemRelatorioEngenharia> itensDoRelatorio) {

        RelatorioEngenharia novoRelatorio = new RelatorioEngenharia(proximoId, itensDoRelatorio);


        this.relatoriosArmazenados.add(novoRelatorio);

        this.proximoId++;

        return novoRelatorio;
    }

    //Busca Relatório Existente
    public RelatorioEngenharia buscarRelatorio(int id) {

        for (RelatorioEngenharia relatorio : relatoriosArmazenados) {
            if (relatorio.getId() == id) {
                return relatorio;
            }
        }
        return null;
    }

//Remove Relatório Existente
    public boolean removerRelatorio(int id) {
        RelatorioEngenharia relatorioParaRemover = buscarRelatorio(id);
        if (relatorioParaRemover != null) {
            relatoriosArmazenados.remove(relatorioParaRemover);
            return true;
        }
        return false;
    }

// Lista Todos os Relatórios
    public ArrayList<RelatorioEngenharia> listarTodosRelatorios() {
        return relatoriosArmazenados;
    }
}