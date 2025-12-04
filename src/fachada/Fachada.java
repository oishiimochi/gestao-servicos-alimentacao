package fachada;

import exceptions.*;
import modelo.*;
import servico.*;

import java.util.List;

public class Fachada {
    private final CadastroUsuarios cadastroUsuarios;
    private final CadastroFornecedor cadastroFornecedor;
    private final CadastroProduto cadastroProduto;
    private final CadastroFichaTecnica cadastroFichaTecnica;
    private final CadastroVenda cadastroVenda;


    private static Fachada instace;

    public Fachada(){
        cadastroUsuarios = new CadastroUsuarios();
        cadastroFornecedor = new CadastroFornecedor();
        cadastroProduto = new CadastroProduto();
        cadastroFichaTecnica = new CadastroFichaTecnica();
        cadastroVenda = new CadastroVenda();
    }

    public static Fachada getInstace(){
        if (instace == null){
            instace = new Fachada();
        }
        return instace;
    }
    // -------------------Usuario------------------------------//
    public void adicionarUsuario(Usuario usuario) throws IDExistenteException{
        cadastroUsuarios.adicionarUsuario(usuario);
    }
    public void removerUsuario(Usuario usuario) throws IdNaoEncontradoException {
        cadastroUsuarios.removerUsuario(usuario);
    }
    public Usuario buscarUsuario(String ID) throws IdNaoEncontradoException {
        return cadastroUsuarios.buscarUsuario(ID);
    }
    public Usuario validarEntrada(String login, String senha) {
        return cadastroUsuarios.validarEntrada(login, senha);
    }
    public Usuario recuperarSenha(String dicaSenha, String ID){
        return cadastroUsuarios.recuperarSenha(dicaSenha, ID);
    }
    public void setSenha(Usuario usuario, String senha){
        usuario.setSenha(senha);
    }
    //------------------------Fornecedor-----------------------//
    public void adicionarFornecedor(Fornecedor fornecedor) throws IDExistenteException {
        cadastroFornecedor.adicionarFornecedor(fornecedor);
    }
    public void removerFornecedor(Fornecedor fornecedor) throws IdNaoEncontradoException {
        cadastroFornecedor.removerFornecedor(fornecedor);
    }
    public Fornecedor buscarFornecedor(String ID) throws IdNaoEncontradoException {
        return cadastroFornecedor.buscarFornecedor(ID);
    }

    public Fornecedor[] getTodosOsFornecedores() {
        return cadastroFornecedor.getTodosOsFornecedores();
    }
    //------------------------Produto (Estoque)-----------------------//
    public void adicionarProdutoAoEstoque(Produto produto) throws RepositorioCheioException, IDExistenteException, ValorNuloException, ValorInvalidoException {
        cadastroProduto.adicionarProduto(produto);
    }

    public boolean editarProduto(String id, Produto novoProduto) {
        return cadastroProduto.editarProduto(id, novoProduto);
    }

    public void removerProduto(String id) throws ProdutoNaoEncontradoException {
        cadastroProduto.removerProduto(id);
    }

    public Produto buscarProdutoPorNome(String nome) throws ProdutoNaoEncontradoException {
        return cadastroProduto.buscarProdutoPorNome(nome);
    }

    public Produto[] getTodosOsProdutos() {
        return cadastroProduto.listarTodosProdutos();
    }

    public String[] getAlertasDeEstoque() {
        return cadastroProduto.getAlertasDeEstoque();
    }

    //------------------------Ficha Técnica-----------------------//
    public void cadastrarFichaTecnica(int id, String nome, String categoria, String preparo, int rendimento) throws RepositorioCheioException, IDExistenteException {
        cadastroFichaTecnica.cadastrarFichaTecnica(id, nome, categoria, preparo, rendimento);
    }

    public FichaTecnica buscarFichaTecnicaPorId(int id) {
        return cadastroFichaTecnica.buscarFichaTecnicaPorId(id);
    }

    public boolean removerFichaTecnica(int id) {
        return cadastroFichaTecnica.removerFichaTecnica(id);
    }

    public String[] getFichasTecnicasParaExibicao() {
        return cadastroFichaTecnica.getFichasTecnicasParaExibicao();
    }

    public void adicionarRequisito(int idFicha, RequisitoReceita requisito) throws ValorInvalidoException, ValorNuloException {
        cadastroFichaTecnica.adicionarRequisito(idFicha, requisito);
    }
    //------------------------Vendas-----------------------//
    public void adicionarItemAoCatalogo(FichaTecnica ficha) throws ValorNuloException, RepositorioCheioException, IDExistenteException, ValorInvalidoException {
        cadastroVenda.adicionarItemAoCatalogo(ficha);
    }

    public ItemVenda buscarItemNoCatalogoPorIdFichaTecnica(int idFichaTecnica) throws ItemNaoEncontradoException {
        return cadastroVenda.buscarItemNoCatalogoPorIdFichaTecnica(idFichaTecnica);
    }

    public void removerItemDoCatalogo(int idFichaTecnica) throws ItemNaoEncontradoException {
        cadastroVenda.removerItemDoCatalogo(idFichaTecnica);
    }

    public String[] getCatalogoParaExibicao() {
        return cadastroVenda.getCatalogoParaExibicao();
    }

    public void registrarVenda(Venda venda) throws RepositorioCheioException, IDExistenteException {
        cadastroVenda.registrarVenda(venda);
    }

    public Venda[] getHistoricoVendas() {
        return cadastroVenda.getHistoricoVendas();
    }

    public String[] getVendasParaExibicao() {
        return cadastroVenda.getVendasParaExibicao();
    }

    public double calcularLucroTotal() {
        return cadastroVenda.calcularLucroTotal();
    }

    public double[] calcularIndicadoresGerais() throws ValorNuloException {
        return cadastroVenda.calcularIndicadoresGerais();
    }

    public String[] gerarLinhasMatrizBCG(double totalVendasGeral, double lucroMedioPonderado, double mixIdeal) {
        return cadastroVenda.gerarLinhasMatrizBCG(totalVendasGeral, lucroMedioPonderado, mixIdeal);
    }

    public void salvarRelatorio(String textoCompleto) throws RepositorioCheioException {
        cadastroVenda.salvarRelatorio(textoCompleto);
    }

    public String[] getHistoricoRelatoriosEngenharia() {
        return cadastroVenda.getHistoricoRelatoriosEngenharia();
    }
}
