package fachada;

import exceptions.IDExistenteException;
import exceptions.IdNaoEncontradoException;
import modelo.*;
import servico.*;
import repository.*;

import java.util.List;

public class Fachada {
    private CadUsuarios cadUsuarios;
    private CadFornecedor cadFornecedor;
    private Estoque estoque;
    private EngenhariaCardapio engenhariaCardapio;

    private static Fachada instace;

    public Fachada(){
        cadUsuarios = new CadUsuarios();
        cadFornecedor = new CadFornecedor();
        estoque = new Estoque();
        engenhariaCardapio = new EngenhariaCardapio();
    }

    public static Fachada getInstace(){
        if (instace == null){
            instace = new Fachada();
        }
        return instace;
    }
    // -------------------Usuario------------------------------//
    public void adicionarUsuario(Usuario usuario) throws IDExistenteException{
        cadUsuarios.adicionarUsuario(usuario);
    }
    public void adicionarColaborador(int id, String nome, String login, String senha, String dicaSenha) throws IDExistenteException {
        cadUsuarios.adicionarColaborador(id, nome, login, senha, dicaSenha);
    }
    public void adicionarGerente(int id, String nome, String login, String senha, String dicaSenha) throws IDExistenteException {
        cadUsuarios.adicionarGerente(id, nome, login, senha, dicaSenha);
    }
    public void adicionarAdministrador(int id, String nome, String login, String senha, String dicaSenha) throws IDExistenteException {
        cadUsuarios.adicionarAdministrador(id, nome, login, senha, dicaSenha);
    }
    public void removerUsuario(Usuario usuario) throws IdNaoEncontradoException {
        cadUsuarios.removerUsuario(usuario);
    }
    public Usuario buscarUsuario(int id) throws IdNaoEncontradoException {
        return cadUsuarios.buscarUsuario(id);
    }
    public Usuario validarEntrada(String login, String senha) {
        return cadUsuarios.validarEntrada(login, senha);
    }
    //------------------------Fornecedor-----------------------//
    public void adicionarFornecedor(Fornecedor fornecedor) throws IDExistenteException {
        cadUsuarios.adicionarUsuario(fornecedor);
    }
    public void removerFornecedor(Fornecedor fornecedor) throws IdNaoEncontradoException {
        cadUsuarios.removerUsuario(fornecedor);
    }
    public Fornecedor buscarFornecedor(int id) throws IdNaoEncontradoException {
        return cadFornecedor.buscarFornecedor(id);
    }
    //-------------------------Estoque-------------------------//
    public void adicionarProduto(Produto produto){
        estoque.adicionarProduto(produto);
    }
    public boolean editarProduto(String id, Produto novoProduto){
       return estoque.editarProduto(id, novoProduto);
    }
    public void removerProduto(String id){
        estoque.removerProduto(id);
    }
    public void listarProdutos(){
        estoque.listarProdutos();
    }
    public Produto buscarProduto(String id){
        return estoque.buscarProduto(id);
    }
    //-------------------------EngenhariaCardapio---------------//
    public List<ItemRelatorioEngenharia> gerarRelatorio(List<FichaTecnica> fichas, List<Venda> vendas){
        return engenhariaCardapio.gerarRelatorio(fichas, vendas);
    }
}