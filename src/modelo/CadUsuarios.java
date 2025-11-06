package modelo;
import repository.RepositorioUsuario;
import exceptions.*;

public class CadUsuarios {
    private RepositorioUsuario repositorio;
    public CadUsuarios(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }
    public CadUsuarios() {
        this.repositorio = new RepositorioUsuario();
    }
    public void adicionarUsuario(Usuario usuario) throws IDExistenteException {
        if(repositorio.buscarUsuario(usuario.getId()) == null) {
            repositorio.adicionarUsuario(usuario);
        }
        else {
            throw new IDExistenteException(usuario.getId());
        }
    }
    public void adicionarColaborador(int id, String nome, String login, String senha, String dicaSenha) throws IDExistenteException {
        Usuario novo = new Colaborador(id, nome, login, senha, dicaSenha);
        adicionarUsuario(novo);
    }
    public void adicionarAdministrador(int id, String nome, String login, String senha, String dicaSenha) throws IDExistenteException {
        Usuario novo = new Administrador(id, nome, login, senha, dicaSenha);
        adicionarUsuario(novo);
    }
    public void adicionarGerente(int id, String nome, String login, String senha, String dicaSenha) throws IDExistenteException {
        Usuario novo = new Gerente(id, nome, login, senha, dicaSenha);
        adicionarUsuario(novo);
    }
    public void removerUsuario(Usuario usuario) throws IdNaoEncontradoException{
        if(repositorio.buscarUsuario(usuario.getId()) == null) {
            throw new IdNaoEncontradoException(usuario.getId());
        }
        repositorio.removerUsuario(usuario);
    }
    public Usuario buscarUsuario(int ID) throws IdNaoEncontradoException {
        Usuario usuario = repositorio.buscarUsuario(ID);
        if(usuario == null) {
            throw new IdNaoEncontradoException(ID);
        }
        return usuario;
    }
    public Usuario validarEntrada(String login, String senha){
        for(Usuario u: repositorio.getUsuarios()){
            if(u.getLogin().equals(login) && u.getSenha().equals(senha)){
                return u;
            }
        }
        return null;
    }
    public Usuario recuperarSenha(String dicaSenha, int id){
        for(Usuario u: repositorio.getUsuarios()){
            if(u.getDicaSenha().equals(dicaSenha) && u.getId() == id){
                return u;
            }
        }
        return null;
    }
}
