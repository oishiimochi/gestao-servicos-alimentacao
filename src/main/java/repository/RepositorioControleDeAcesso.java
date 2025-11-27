package repository;

import exceptions.AcessoNegadoException;
import exceptions.MetodoNaoEncontradoException;

import java.util.ArrayList;

public class RepositorioControleDeAcesso {
    private ArrayList<String> acessoGerente;
    private ArrayList<String> acessoColaborador;
    private int posicaoLivreGerente;
    private int posicaoLivreColaborador;
    private static RepositorioControleDeAcesso instance;

    public RepositorioControleDeAcesso() {
        this.acessoColaborador = new ArrayList<>();
        this.acessoGerente = new ArrayList<>();
    }
    public static RepositorioControleDeAcesso getInstance() {
        if (instance == null) {
            instance = new RepositorioControleDeAcesso();
        }
        return instance;
    }
    //getters
    public ArrayList<String> getAcessoColaborador() {
        return acessoColaborador;
    }

    public ArrayList<String> getAcessoGerente() {
        return acessoGerente;
    }
    //setters
    public void setAcessoColaborador(ArrayList<String> acessoColaborador) {
        this.acessoColaborador = acessoColaborador;
    }

    public void setAcessoGerente(ArrayList<String> acessoGerente) {
        this.acessoGerente = acessoGerente;
    }
    //Métodos
    public void adicionarAcessoGerente(String metodo) {
        this.acessoGerente.add(metodo);
    }
    public void adicionarAcessoColaborador(String metodo){
        this.acessoColaborador.add(metodo);
    }
    public String buscarAcessoGerente(String metodo) throws MetodoNaoEncontradoException {
        if(this.acessoGerente.contains(metodo)){
            return metodo;
        }
        throw new MetodoNaoEncontradoException("O nível de acesso gerente não possui acesso ao método: " + metodo);
    }
    public String buscarAcessoColaborador(String metodo) throws MetodoNaoEncontradoException {
        if(this.acessoColaborador.contains(metodo)){
            return metodo;
        }
        throw new MetodoNaoEncontradoException("O nível de acesso colaborador não possui acesso ao método: " + metodo);
    }
    public void removerAcessoGerente(String metodo) throws MetodoNaoEncontradoException {
        this.acessoGerente.remove(metodo);
    }
    public void removerAcessoColaborador(String metodo) throws MetodoNaoEncontradoException {
        this.acessoColaborador.remove(metodo);
    }
    public void verificarPermissaoGerente(String metodo) throws AcessoNegadoException {
        if(this.acessoGerente.contains(metodo)){
            return;
        }
        throw new AcessoNegadoException("Acesso negado: Nível de acesso administrador necessário.");
    }
    public void verificarPermissaoColaborador(String metodo) throws AcessoNegadoException {
        if(this.acessoColaborador.contains(metodo)){
            return;
        }
        throw new AcessoNegadoException("Acesso negado.");
    }
}
