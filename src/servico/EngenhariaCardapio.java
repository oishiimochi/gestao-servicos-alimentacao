package servico;


import modelo.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngenhariaCardapio {

    public List<ItemRelatorioEngenharia> gerarRelatorio(List<FichaTecnica> fichas, List<Venda> vendas) {

        List<ItemRelatorioEngenharia> relatorio = new ArrayList<>();

        Map<Integer, ItemRelatorioEngenharia> mapaRelatorio = new HashMap<>();

        // Calcular Lucro e Vendas de cada item ---

        for (Venda v : vendas) {
            FichaTecnica prato = v.getPrato();
            int idPrato = prato.getId();


            double lucroItem = v.getPrecoUnitario() - prato.calcularCustoPorPorcao();


            ItemRelatorioEngenharia item = mapaRelatorio.get(idPrato);

            if (item == null) {

                item = new ItemRelatorioEngenharia(prato.getNome(), v.getQuantidadeVendida(), lucroItem);
            } else {

                int novaQuantidade = item.getTotalVendido() + v.getQuantidadeVendida();
                item.setTotalVendido(novaQuantidade);

            }
            mapaRelatorio.put(idPrato, item);
        }


        relatorio.addAll(mapaRelatorio.values());

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

        // Média de lucro ponderada
        double lucroMedio = lucroTotalAcumulado / vendasTotalAcumulado;

        // Média de popularidade (usando a regra de 70% da média de vendas por item)
        double popularidadeMedia = (vendasTotalAcumulado / (double) relatorio.size()) * 0.7;

        // --- 3. Classificar cada Prato ---
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

        return relatorio;
    }
}