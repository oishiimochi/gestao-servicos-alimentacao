package servico;

import modelo.*;
import repository.EngenhariaCardapio;
import repository.ItemVendaRepository;
import repository.VendaRepository;
import exceptions.RepositorioCheioException;
import exceptions.ItemNaoEncontradoException;
import exceptions.ValorNuloException;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class SistemaGestao {

    //Listas e Repositórios
    private ArrayList<Produto> produtos;
    private ArrayList<Fornecedor> fornecedores;
    private ArrayList<FichaTecnica> fichas;
    private final VendaRepository historicoVendas;
    private final ItemVendaRepository catalogoItens;
    private ArrayList<Usuario> usuarios;
    private ArrayList<ItemRelatorioEngenharia> relatorioEngenharia;

    //Construtor
    public SistemaGestao() {
        produtos = new ArrayList<>();
        fornecedores = new ArrayList<>();
        fichas = new ArrayList<>();
        historicoVendas = new VendaRepository();
        catalogoItens = new ItemVendaRepository();
        usuarios = new ArrayList<>();
        relatorioEngenharia = new ArrayList<>();
    }

    // --------- MÉTODOS DE PRODUTO ---------
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void listarProdutos() {
        System.out.println("=== Lista de Produtos ===");
        for (Produto p : produtos) {
            System.out.println(p.getId() + " - " + p.getNome() + " | " + p.getQuantidade() + " " + p.getUnidadeMedida());
        }
    }

    public Produto buscarProdutoPorId(String id) {
        for (Produto p : produtos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void removerProduto(String id) {
        Produto encontrado = buscarProdutoPorId(id);
        if (encontrado != null) {
            produtos.remove(encontrado);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Produto p : produtos) {
            total += p.calcularValorTotal();
        }
        return total;
    }

    // --------- MÉTODOS DE FORNECEDOR ---------
    public void adicionarFornecedor(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
    }

    public void listarFornecedores() {
        System.out.println("=== Lista de Fornecedores ===");
        for (Fornecedor f : fornecedores) {
            System.out.println(f.getId() + " - " + f.getNome() + " | " + f.getCnpj());
        }
    }

    public Fornecedor buscarFornecedorPorId(int id) {
        for (Fornecedor f : fornecedores) {
            if (f.getId() == id) return f;
        }
        return null;
    }

    // --------- MÉTODOS DE FICHA TÉCNICA ---------
    public void adicionarFicha(FichaTecnica ficha) {
        fichas.add(ficha);
    }

    public void listarFichas() {
        System.out.println("=== Lista de Fichas Técnicas ===");
        for (FichaTecnica f : fichas) {
            System.out.println(f.getId() + " - " + f.getNome() + " | Rendimento: " + f.getRendimento());
        }
    }

    public FichaTecnica buscarFichaPorId(int id) {
        for (FichaTecnica f : fichas) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    // --------- MÉTODOS DE ITEM DE VENDA (CATÁLOGO) ---------
    public void adicionarItemVenda(FichaTecnica ficha) throws ValorNuloException, RepositorioCheioException {
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
    public void registrarVenda(Venda venda) throws RepositorioCheioException {
        historicoVendas.registrarVenda(venda);
    }

    public void listarVendas() {
        System.out.println("=== Lista de Vendas ===");
        for (Venda v : historicoVendas.buscarTodas()) {
            System.out.println("Venda ID: " + v.getId() + " | Prato: " + v.getPrato().getNome() +
                    " | Qtd: " + v.getQuantidadeVendida() + " | Receita: R$ " + v.calcularReceitaTotal());
        }
    }

    public double calcularLucroTotal() {
        double total = 0;
        for (Venda v : historicoVendas.buscarTodas()) {
            total += v.calcularLucroBruto();
        }
        return total;
    }

    // --------- MÉTODOS DE USUÁRIO ---------
    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void listarUsuarios() {
        System.out.println("=== Usuários Cadastrados ===");
        for (Usuario u : usuarios) {
            System.out.println(u.getId() + " - " + u.getNome() + " (" + u.getClass().getSimpleName() + ")");
        }
    }

    public Usuario buscarUsuarioPorLogin(String login) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    // --------- GETTERS DAS LISTAS ---------
    public ArrayList<Produto> getProdutos() { return produtos; }
    public ArrayList<Fornecedor> getFornecedores() { return fornecedores; }
    public ArrayList<FichaTecnica> getFichas() { return fichas; }
    public List<Venda> getVendas() { return historicoVendas.buscarTodas(); }
    public ArrayList<Usuario> getUsuarios() { return usuarios; }

    // --------- RELATÓRIO GERAL ---------
    public void gerarResumoGeral() {
        System.out.println("\n=== RESUMO GERAL DO SISTEMA ===");
        System.out.println("Produtos no estoque: " + produtos.size());
        System.out.println("Fornecedores cadastrados: " + fornecedores.size());
        System.out.println("Fichas técnicas: " + fichas.size());
        System.out.println("Vendas registradas: " + historicoVendas.buscarTodas().size());
        System.out.println("Usuários cadastrados: " + usuarios.size());
        System.out.println("Valor total do estoque: R$ " + calcularValorTotalEstoque());
        System.out.println("Lucro total estimado: R$ " + calcularLucroTotal());
    }

    // --------- RELATÓRIO DIÁRIO ---------
    public void gerarResumoDiario(LocalDate data) {
        System.out.println("\n=== RESUMO DIÁRIO (" + data + ") ===");
        double total = 0;
        for (Venda v : historicoVendas.buscarTodas()) {
            if (v.getDataVenda().equals(data)) {
                System.out.println(v.getPrato().getNome() + " - R$ " + v.calcularReceitaTotal());
                total += v.calcularReceitaTotal();
            }
        }
        System.out.println("Total vendido no dia: R$ " + total);
    }
    // --------- ENGENHARIA DE CARDÁPIO ---------

    public List<ItemRelatorioEngenharia> gerarEArmazenarRelatorioEngenharia() {

        EngenhariaCardapio motorAnalise = new EngenhariaCardapio();


        this.relatorioEngenharia = (ArrayList<ItemRelatorioEngenharia>) motorAnalise.gerarRelatorio(this.fichas, this.historicoVendas.buscarTodas());

        System.out.println("Relatório de Engenharia de Cardápio gerado e armazenado com sucesso!");

        return this.relatorioEngenharia;
    }

    public ArrayList<ItemRelatorioEngenharia> getRelatorioEngenharia() {
        return relatorioEngenharia;
    }
}