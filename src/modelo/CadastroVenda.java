package modelo;

import exceptions.*;
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
    public void adicionarItemVenda(FichaTecnica ficha) throws ValorNuloException, RepositorioCheioException, IDExistenteException, ValorInvalidoException {
        ItemVenda novoItem = new ItemVenda(ficha);
        catalogoItens.adicionarItem(novoItem);
        System.out.println("Item de venda '" + ficha.getNome() + "' adicionado ao catálogo.");
    }

    public ItemVenda buscarItemVendaPorIdFichaTecnica(int idFichaTecnica) throws ItemNaoEncontradoException {
        return catalogoItens.buscarPorIdFichaTecnica(idFichaTecnica);
    }

    public void removerItemVenda(int idFichaTecnica) throws ItemNaoEncontradoException {
        catalogoItens.removerItem(idFichaTecnica);
        System.out.println("Item de venda removido do catálogo.");
    }

    public void listarItensVenda() {
        System.out.println("=== Catálogo de Itens para Venda ===");
        for (ItemVenda item : catalogoItens.buscarTodos()) {
            System.out.println(item);
        }
    }

    // --------- MÉTODOS DE VENDA ---------
    public void registrarVenda(Venda novaVenda) throws RepositorioCheioException, IDExistenteException {
        historicoVendas.registrarVenda(novaVenda);
    }

    public void listarVendas() {
        System.out.println("=== Histórico de Vendas ===");
        Venda[] todasAsVendas = historicoVendas.buscarTodas();
        if (todasAsVendas.length == 0) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }
        for (Venda venda : todasAsVendas) {
            System.out.println("---------------------------------");
            System.out.println(venda); // Usa o toString() da Venda
            System.out.println("Itens vendidos:");
            for (ItemVenda itemDaVenda : venda.getItens()) {
                System.out.println("  - " + itemDaVenda); // Usa o toString() do ItemVenda
            }
        }
        System.out.println("---------------------------------");
    }

    public double calcularLucroTotal() {
        double lucroTotal = 0;
        for (Venda venda : historicoVendas.buscarTodas()) {
            lucroTotal += venda.calcularLucroBruto();
        }
        return lucroTotal;
    }

    public Venda[] getHistoricoVendas() {
        return historicoVendas.buscarTodas();
    }

    // --- Engenharia de Cardápio ---
    // (Métodos futuros podem ser adicionados aqui)

}