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
}
