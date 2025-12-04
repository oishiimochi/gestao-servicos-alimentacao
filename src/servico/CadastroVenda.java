package servico;

import exceptions.*;
import modelo.FichaTecnica;
import modelo.ItemVenda;
import modelo.Venda;
import repository.ItemVendaRepository;
import repository.VendaRepository;
import repository.RepositorioRelatorio;


public class CadastroVenda {
    private VendaRepository historicoVendas;
    private ItemVendaRepository catalogoItens;
    private RepositorioRelatorio repositorioRelatorios;

    public CadastroVenda() {
        this.historicoVendas = new VendaRepository();
        this.catalogoItens = new ItemVendaRepository();
        this.repositorioRelatorios = new RepositorioRelatorio();
    }

    // --------- MÉTODOS DE ITEM DE VENDA (CATÁLOGO) ---------
    public void adicionarItemAoCatalogo(FichaTecnica ficha) throws ValorNuloException, RepositorioCheioException, IDExistenteException, ValorInvalidoException {
        // Ao adicionar um item ao catálogo, a quantidade padrão é 1.
        ItemVenda novoItem = new ItemVenda(ficha, 1);
        catalogoItens.adicionarItem(novoItem);
    }

    public ItemVenda buscarItemNoCatalogoPorIdFichaTecnica(int idFichaTecnica) throws ItemNaoEncontradoException {
        return catalogoItens.buscarPorIdFichaTecnica(idFichaTecnica);
    }

    public void removerItemDoCatalogo(int idFichaTecnica) throws ItemNaoEncontradoException {
        catalogoItens.removerItem(idFichaTecnica);
    }

    public String[] getCatalogoParaExibicao() {
        ItemVenda[] itens = catalogoItens.buscarTodos();
        String[] catalogoExibicao = new String[itens.length];
        for (int i = 0; i < itens.length; i++) {
            catalogoExibicao[i] = itens[i].toString();
        }
        return catalogoExibicao;
    }

    // --------- MÉTODOS DE VENDA ---------
    public void registrarVenda(Venda novaVenda) throws RepositorioCheioException, IDExistenteException {
        historicoVendas.registrarVenda(novaVenda);
    }

    public String[] getVendasParaExibicao() {
        Venda[] todasAsVendas = historicoVendas.buscarTodas();
        if (todasAsVendas.length == 0) {
            return new String[]{"Nenhuma venda registrada."};
        }

        int totalLinhas = 0;
        for (int i = 0; i < todasAsVendas.length; i++) {
            totalLinhas += 3 + todasAsVendas[i].getItens().length; // Venda + "Itens vendidos:" + número de itens + linha em branco
        }

        String[] vendasExibicao = new String[totalLinhas];
        int linhaAtual = 0;
        for (int i = 0; i < todasAsVendas.length; i++) {
            Venda venda = todasAsVendas[i];
            vendasExibicao[linhaAtual++] = "---------------------------------";
            vendasExibicao[linhaAtual++] = venda.toString();
            vendasExibicao[linhaAtual++] = "Itens vendidos:";
            ItemVenda[] itensDaVenda = venda.getItens();
            for (int j = 0; j < itensDaVenda.length; j++) {
                vendasExibicao[linhaAtual++] = "  - " + itensDaVenda[j].toString();
            }
        }
        return vendasExibicao;
    }

    public double calcularLucroTotal() {
        double lucroTotal = 0;
        Venda[] todasAsVendas = historicoVendas.buscarTodas();
        for (int i = 0; i < todasAsVendas.length; i++) {
            lucroTotal += todasAsVendas[i].calcularLucroBruto();
        }
        return lucroTotal;
    }

    public Venda[] getHistoricoVendas() {
        return historicoVendas.buscarTodas();
    }

    // --- Engenharia de Cardápio ---

    public double[] calcularIndicadoresGerais() throws ValorNuloException {
        Venda[] vendas = historicoVendas.buscarTodas();
        if (vendas == null || vendas.length == 0) {
            throw new ValorNuloException("Não há vendas registradas.");
        }

        // Agregação simplificada apenas para totais
        int totalVendasGeral = 0;
        double lucroGeralAcumulado = 0;

        // Contagem de pratos distintos para o Mix Ideal
        int[] idsVistos = new int[100];
        int contagemPratos = 0;

        for (Venda v : vendas) {
            for (ItemVenda item : v.getItens()) {
                if (item == null) continue;
                totalVendasGeral += item.getQuantidade();
                lucroGeralAcumulado += item.calcularLucroTotalItem();

                // Contagem de distintos
                boolean novo = true;
                for(int i=0; i<contagemPratos; i++) {
                    if(idsVistos[i] == item.getFichaTecnica().getId()) {
                        novo = false;
                        break;
                    }
                }
                if(novo && contagemPratos < 100) {
                    idsVistos[contagemPratos++] = item.getFichaTecnica().getId();
                }
            }
        }

        if (totalVendasGeral == 0) return new double[]{0, 0, 0};

        double lucroMedio = lucroGeralAcumulado / totalVendasGeral;
        double mixIdeal = (1.0 / (contagemPratos == 0 ? 1 : contagemPratos)) * 0.7;

        return new double[] { (double) totalVendasGeral, lucroMedio, mixIdeal };
    }

    /**
     * Gera as linhas da tabela (os pratos classificados).
     * Recebe os indicadores já calculados para garantir consistência.
     */
    public String[] gerarLinhasMatrizBCG(double totalVendasGeral, double lucroMedioPonderado, double mixIdeal) {
        Venda[] vendas = historicoVendas.buscarTodas();

        // 1. Estruturas Auxiliares
        int capacidade = 100;
        int[] idsPratos = new int[capacidade];
        String[] nomesPratos = new String[capacidade];
        int[] qtdTotalVendida = new int[capacidade];
        double[] lucroTotalPrato = new double[capacidade];
        double[] custoTotalPrato = new double[capacidade];
        int contadorPratosDistintos = 0;

        // 2. Agregação
        for (Venda v : vendas) {
            for (ItemVenda item : v.getItens()) {
                if (item == null) continue;
                int id = item.getFichaTecnica().getId();
                int idx = -1;

                for (int k = 0; k < contadorPratosDistintos; k++) {
                    if (idsPratos[k] == id) { idx = k; break; }
                }

                if (idx == -1) {
                    if (contadorPratosDistintos < capacidade) {
                        idx = contadorPratosDistintos++;
                        idsPratos[idx] = id;
                        nomesPratos[idx] = item.getFichaTecnica().getNome();
                    }
                }

                if (idx != -1) {
                    qtdTotalVendida[idx] += item.getQuantidade();
                    lucroTotalPrato[idx] += item.calcularLucroTotalItem();
                    custoTotalPrato[idx] += item.calcularCustoTotalItem();
                }
            }
        }

        // 3. Classificação e Geração das Linhas
        String[] linhas = new String[contadorPratosDistintos];

        for (int i = 0; i < contadorPratosDistintos; i++) {
            double lucroUnitario = lucroTotalPrato[i] / qtdTotalVendida[i];
            double mixPrato = (double) qtdTotalVendida[i] / totalVendasGeral;

            boolean altaPop = mixPrato >= mixIdeal;
            boolean altoLucro = lucroUnitario >= lucroMedioPonderado;

            String classif;
            if (altaPop && altoLucro) classif = "ESTRELA";
            else if (altaPop && !altoLucro) classif = "BURRO";
            else if (!altaPop && altoLucro) classif = "ABACAXI";
            else classif = "CÃO";

            linhas[i] = String.format("%-20s | %-5d | R$ %-8.2f | R$ %-10.2f | %s",
                    nomesPratos[i], qtdTotalVendida[i], custoTotalPrato[i], lucroUnitario, classif);
        }
        return linhas;
    }


    public void salvarRelatorio(String textoCompleto) throws RepositorioCheioException {
        repositorioRelatorios.adicionarRelatorio(textoCompleto);
    }

    public String[] getHistoricoRelatoriosEngenharia() {
        return repositorioRelatorios.buscarTodos();
    }
}



