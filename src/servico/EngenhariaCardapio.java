package servico;

import modelo.*;
import java.util.ArrayList;
import java.util.List;

// Recebe vendas e fichas e gera relatório
public class EngenhariaCardapio {

    //Executa a análise de engenharia
    public List<ItemRelatorioEngenharia> gerarRelatorio(List<FichaTecnica> fichas, List<Venda> vendas) {


        List<ItemRelatorioEngenharia> relatorio = new ArrayList<>();

        // --- 1. Calcular Lucro e Vendas de cada item

        for (Venda v : vendas) {
            FichaTecnica prato = v.getPrato();
            double lucroItem = v.getPrecoUnitario() - prato.calcularCustoPorPorcao();

            ItemRelatorioEngenharia itemExistente = this.buscarItemPorId(relatorio, prato.getId());

            if (itemExistente == null) {
                ItemRelatorioEngenharia novoItem = new ItemRelatorioEngenharia(prato.getId(), prato.getNome(), v.getQuantidadeVendida(), lucroItem);
                relatorio.add(novoItem);
            } else {

                int novaQuantidade = itemExistente.getTotalVendido() + v.getQuantidadeVendida();
                itemExistente.setTotalVendido(novaQuantidade);
            }
        }

        if (relatorio.isEmpty()) {
            return relatorio;
        }

        // --- 2. Calcular Médias (Popularidade e Lucro) ---

        double lucroTotalAcumulado = 0;
        int vendasTotalAcumulado = 0;
        for (ItemRelatorioEngenharia item : relatorio) {
            lucroTotalAcumulado += (item.getLucroPorItem() * item.getTotalVendido());
            vendasTotalAcumulado += item.getTotalVendido();
        }

        double lucroMedio = lucroTotalAcumulado / vendasTotalAcumulado;
        double popularidadeMedia = (vendasTotalAcumulado / (double) relatorio.size()) * 0.7;

        // --- 3. Classificar cada Prato (com if/else) ---

        for (ItemRelatorioEngenharia item : relatorio) {

            boolean altoLucro = item.getLucroPorItem() > lucroMedio;
            boolean altaPopularidade = item.getTotalVendido() > popularidadeMedia;

            if (altaPopularidade && altoLucro) {
                item.setClassificacao("ESTRELA");

            } else if (altaPopularidade && !altoLucro) {
                item.setClassificacao("BOI DE CARGA");

            } else if (!altaPopularidade && altoLucro) {
                item.setClassificacao("QUEBRA-CABECA");

            } else {
                item.setClassificacao("CACHORRO");
            }
        }
        return relatorio; // Retorna a lista preenchida
    }


    // Busca item na lista por ID

    private ItemRelatorioEngenharia buscarItemPorId(List<ItemRelatorioEngenharia> relatorio, int idPrato) {
        for (ItemRelatorioEngenharia item : relatorio) {
            if (item.getIdPrato() == idPrato) {
                return item;
            }
        }
        return null;
    }
}