package servico;

import exceptions.*;
import modelo.FichaTecnica;
import modelo.ItemVenda;
import modelo.Venda;
import repository.ItemVendaRepository;
import repository.VendaRepository;

public class CadastroVenda {
    private VendaRepository historicoVendas;
    private ItemVendaRepository catalogoItens;

    public CadastroVenda() {
        this.historicoVendas = new VendaRepository();
        this.catalogoItens = new ItemVendaRepository();
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
    // (Métodos futuros podem ser adicionados aqui)

}
