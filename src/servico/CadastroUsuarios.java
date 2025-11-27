package servico;
import modelo.Usuario;
import repository.RepositorioUsuario;
import exceptions.*;

public class CadastroUsuarios {
    private RepositorioUsuario repositorio;

    // Adicionar Singleton
    public CadastroUsuarios() {
        this.repositorio = RepositorioUsuario.getInstance();
    }
    public Usuario buscarUsuario(String ID) throws IdNaoEncontradoException {
        Usuario usuario = repositorio.buscarUsuario(ID);
        if(usuario == null) {
            throw new IdNaoEncontradoException(ID, "usuário");
        }
        return usuario;
    }
    public void adicionarUsuario(Usuario usuario) throws IDExistenteException {

        if(repositorio.buscarUsuario(usuario.getId()) == null) {
            repositorio.adicionarUsuario(usuario);
        }
        else {
            throw new IDExistenteException(usuario.getId(), "usuário");
        }
    }
    public void removerUsuario(Usuario usuario) throws IdNaoEncontradoException{
        if(repositorio.buscarUsuario(usuario.getId()) == null) {
            throw new IdNaoEncontradoException(usuario.getId(), "usuário");
        }
        repositorio.removerUsuario(usuario);
    }
    public Usuario validarEntrada(String login, String senha){
        for(Usuario u: repositorio.getUsuarios()){
            if(u.getLogin().equals(login) && u.getSenha().equals(senha)){
                return u;
            }
        }
        return null;
    }
    public Usuario recuperarSenha(String dicaSenha, String ID){
        for(Usuario usuario: repositorio.getUsuarios()){
            if(usuario.getDicaSenha().equals(dicaSenha) && usuario.getId().equals(ID)){
                return usuario;
            }
        }
        return null;
    }
}
