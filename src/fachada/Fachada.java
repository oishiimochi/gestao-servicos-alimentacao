package fachada;

import exceptions.IDExistenteException;
import exceptions.IdNaoEncontradoException;
import modelo.*;
import servico.*;

import java.util.List;

public class Fachada {
    private CadastroUsuarios cadastroUsuarios;
    private CadastroFornecedor cadastroFornecedor;


    private static Fachada instace;

    public Fachada(){
        cadastroUsuarios = new CadastroUsuarios();
        cadastroFornecedor = new CadastroFornecedor();
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
}