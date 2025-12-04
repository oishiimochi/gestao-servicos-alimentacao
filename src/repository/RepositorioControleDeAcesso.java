package repository;

import enums.TipoUsuario;
import modelo.Usuario;
import exceptions.AcessoNegadoException;
import exceptions.RepositorioCheioException;
import exceptions.MetodoNaoEncontradoException;

public class RepositorioControleDeAcesso {
    private static final int CAPACIDADE_MAXIMA = 200;
    private String[] acessoGerente;
    private String[] acessoColaborador;
    private int posicaoLivreGerente;
    private int posicaoLivreColaborador;
    private static RepositorioControleDeAcesso instance;

    public RepositorioControleDeAcesso() {
        this.acessoColaborador = new String[CAPACIDADE_MAXIMA];
        this.acessoGerente = new String[CAPACIDADE_MAXIMA];
        this.posicaoLivreGerente = 0;
        this.posicaoLivreColaborador = 0;
    }
    public static RepositorioControleDeAcesso getInstance() {
        if (instance == null) {
            instance = new RepositorioControleDeAcesso();
        }
        return instance;
    }
    //getters
    public String[] getAcessoColaborador() {
        return java.util.Arrays.copyOf(acessoColaborador, posicaoLivreColaborador);
    }
    public String[] getAcessoGerente() {
        return java.util.Arrays.copyOf(acessoGerente, posicaoLivreGerente);
    }
    //setters
    public void setAcessoColaborador(String[] acessoColaborador) {
        this.acessoColaborador = acessoColaborador;
        this.posicaoLivreColaborador = acessoColaborador.length;
    }
    public void setAcessoGerente(String[] acessoGerente) {
        this.acessoGerente = acessoGerente;
        this.posicaoLivreGerente = acessoGerente.length;
    }
    //Métodos
    public void adicionarAcessoGerente(String metodo) throws RepositorioCheioException {
        if (posicaoLivreGerente >= CAPACIDADE_MAXIMA) throw new RepositorioCheioException("Acesso gerente cheio");
        acessoGerente[posicaoLivreGerente++] = metodo;
    }
    public void adicionarAcessoColaborador(String metodo) throws RepositorioCheioException {
        if (posicaoLivreColaborador >= CAPACIDADE_MAXIMA) throw new RepositorioCheioException("Acesso colaborador cheio");
        acessoColaborador[posicaoLivreColaborador++] = metodo;
    }
    public String buscarAcessoGerente(String metodo) throws MetodoNaoEncontradoException {
        for (int i = 0; i < posicaoLivreGerente; i++) {
            if (acessoGerente[i].equals(metodo)) return metodo;
        }
        throw new MetodoNaoEncontradoException("O nível de acesso gerente não possui acesso ao método: " + metodo);
    }
    public String buscarAcessoColaborador(String metodo) throws MetodoNaoEncontradoException {
        for (int i = 0; i < posicaoLivreColaborador; i++) {
            if (acessoColaborador[i].equals(metodo)) return metodo;
        }
        throw new MetodoNaoEncontradoException("O nível de acesso colaborador não possui acesso ao método: " + metodo);
    }
    public void removerAcessoGerente(String metodo) throws MetodoNaoEncontradoException {
        int idx = -1;
        for (int i = 0; i < posicaoLivreGerente; i++) {
            if (acessoGerente[i].equals(metodo)) { idx = i; break; }
        }
        if (idx == -1) throw new MetodoNaoEncontradoException("Método não encontrado para gerente: " + metodo);
        for (int i = idx; i < posicaoLivreGerente - 1; i++) {
            acessoGerente[i] = acessoGerente[i + 1];
        }
        acessoGerente[--posicaoLivreGerente] = null;
    }
    public void removerAcessoColaborador(String metodo) throws MetodoNaoEncontradoException {
        int idx = -1;
        for (int i = 0; i < posicaoLivreColaborador; i++) {
            if (acessoColaborador[i].equals(metodo)) { idx = i; break; }
        }
        if (idx == -1) throw new MetodoNaoEncontradoException("Método não encontrado para colaborador: " + metodo);
        for (int i = idx; i < posicaoLivreColaborador - 1; i++) {
            acessoColaborador[i] = acessoColaborador[i + 1];
        }
        acessoColaborador[--posicaoLivreColaborador] = null;
    }
    public void verificarPermissaoGerente(String metodo) throws AcessoNegadoException {
        for (int i = 0; i < posicaoLivreGerente; i++) {
            if (acessoGerente[i].equals(metodo)) return;
        }
        throw new AcessoNegadoException("Acesso negado: Nível de acesso administrador necessário.");
    }
    public void verificarPermissaoColaborador(String metodo) throws AcessoNegadoException {
        for (int i = 0; i < posicaoLivreColaborador; i++) {
            if (acessoColaborador[i].equals(metodo)) return;
        }
        throw new AcessoNegadoException("Acesso negado.");
    }
}
