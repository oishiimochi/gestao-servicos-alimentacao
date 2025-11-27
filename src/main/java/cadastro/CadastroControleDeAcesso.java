package cadastro;

import enums.TipoUsuario;
import exceptions.AcessoNegadoException;
import exceptions.MetodoNaoEncontradoException;
import exceptions.RepositorioCheioException;
import modelo.Usuario;
import repository.RepositorioControleDeAcesso;

public class CadastroControleDeAcesso {
    private RepositorioControleDeAcesso repositorioControleDeAcesso;

    public CadastroControleDeAcesso() {
        this.repositorioControleDeAcesso = RepositorioControleDeAcesso.getInstance();
    }
    public void verificarPermissao(Usuario usuario, String metodo) throws AcessoNegadoException {
        TipoUsuario acesso = usuario.getTipo();
        if(acesso == TipoUsuario.Gerente){
            repositorioControleDeAcesso.verificarPermissaoGerente(metodo);
        }
        else if(acesso == TipoUsuario.Colaborador){
            repositorioControleDeAcesso.verificarPermissaoColaborador(metodo);
        }
    }
    public void adicionarAcessoGerente(String metodo) throws RepositorioCheioException {
        repositorioControleDeAcesso.adicionarAcessoGerente(metodo);
    }
    public void adicionarAcessoColaborador(String metodo) throws RepositorioCheioException {
       repositorioControleDeAcesso.adicionarAcessoColaborador(metodo);
    }
    public String buscarAcessoGerente(String metodo) throws MetodoNaoEncontradoException {
        return repositorioControleDeAcesso.buscarAcessoGerente(metodo);
    }
    public String buscarAcessoColaborador(String metodo) throws MetodoNaoEncontradoException {
        return repositorioControleDeAcesso.buscarAcessoColaborador(metodo);
    }
    public void removerAcessoGerente(String metodo) throws MetodoNaoEncontradoException {
        this.buscarAcessoGerente(metodo);
        repositorioControleDeAcesso.removerAcessoGerente(metodo);
    }
    public void removerAcessoColaborador(String metodo) throws MetodoNaoEncontradoException {
        this.buscarAcessoColaborador(metodo);
        repositorioControleDeAcesso.removerAcessoColaborador(metodo);
    }
}
