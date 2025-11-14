package repository;

import enums.TipoUsuario;
import modelo.Usuario;
import exceptions.AcessoNegadoException;
import exceptions.RepositorioCheioException;
import exceptions.MetodoNaoEncontradoException;

public class RepositorioControleDeAcesso {
    private String[] acessoGerente;
    private String[] acessoColaborador;
    private int posicaoLivreGerente;
    private int posicaoLivreColaborador;
    private static RepositorioControleDeAcesso instance;

    public RepositorioControleDeAcesso() {
        this.acessoColaborador = new String[10];
        this.acessoGerente = new String[30];
    }
    public static RepositorioControleDeAcesso getInstance() {
        if (instance == null) {
            instance = new RepositorioControleDeAcesso();
        }
        return instance;
    }
    //getters
    public String[] getAcessoColaborador() {
        return acessoColaborador;
    }

    public String[] getAcessoGerente() {
        return acessoGerente;
    }
    //setters
    public void setAcessoColaborador(String[] acessoColaborador) {
        this.acessoColaborador = acessoColaborador;
    }

    public void setAcessoGerente(String[] acessoGerente) {
        this.acessoGerente = acessoGerente;
    }
    //Métodos
    public void adicionarAcessoGerente(String metodo) throws RepositorioCheioException {
        if(posicaoLivreGerente != acessoColaborador.length){
            acessoGerente[posicaoLivreGerente] = metodo;
            posicaoLivreGerente++;
        }
        else{
            throw new RepositorioCheioException("Erro ao adicionar acesso de gerente: Repositorio Cheio.");
        }
    }
    public void adicionarAcessoColaborador(String metodo) throws RepositorioCheioException {
        if(posicaoLivreColaborador != acessoGerente.length){
            acessoColaborador[posicaoLivreColaborador] = metodo;
            posicaoLivreColaborador++;
        }
        else{
            throw new RepositorioCheioException("Erro ao adicionar acesso de Colaborador: Repositorio Cheio.");
        }
    }
    public int buscarAcessoGerente(String metodo) throws MetodoNaoEncontradoException {
        for(int i = 0; i < acessoGerente.length; i++){
            if(acessoGerente[i].equals(metodo)){
                return i;
            }
        }
        throw new MetodoNaoEncontradoException("O nível de acesso gerente não possui acesso ao método: " + metodo);
    }
    public int buscarAcessoColaborador(String metodo) throws MetodoNaoEncontradoException {
        for(int i = 0; i < acessoColaborador.length; i++){
            if(acessoColaborador[i].equals(metodo)){
                return i;
            }
        }
        throw new MetodoNaoEncontradoException("O nível de acesso colaborador não possui acesso ao método: " + metodo);
    }
    public void removerAcessoGerente(String metodo) throws RepositorioCheioException {
        int posicao = buscarAcessoGerente(metodo);
        acessoGerente[posicao] = acessoGerente[posicaoLivreGerente];
        posicaoLivreGerente--;
    }
    public void removerAcessoColaborador(String metodo) throws RepositorioCheioException {
        int posicao = buscarAcessoColaborador(metodo);
        acessoColaborador[posicao] = acessoColaborador[posicaoLivreColaborador];
        posicaoLivreColaborador--;
    }
    public void verificarPermissaoGerente(String metodo) throws AcessoNegadoException {
        for(int i = 0; i < acessoGerente.length; i++) {
            if(acessoGerente[i].equals(metodo)) {
                return;
            }
        }
        throw new AcessoNegadoException("Acesso negado: Nível de acesso administrador necessário.");
    }
    public void verificarPermissaoColaborador(String metodo) throws AcessoNegadoException {
        for(int i = 0; i < acessoColaborador.length; i++) {
            if(acessoColaborador[i].equals(metodo)) {
                return;
            }
        }
        throw new AcessoNegadoException("Acesso negado.");
    }
}
