package repository;

import exceptions.IdNaoEncontradoException;
import modelo.Usuario;

public class RepositorioUsuario {

    private Usuario[] usuarios;
    private int posicaolivre;
    private static RepositorioUsuario instance;

    public RepositorioUsuario() {
        usuarios = new Usuario[50];
    }
    public static RepositorioUsuario getInstance() {
        if (instance == null) {
            instance = new RepositorioUsuario();
        }
        return instance;
    }
    public Usuario[] getUsuarios() {
        return usuarios;
    }
    public Usuario buscarUsuario(String ID){
        for(int i = 0; i < posicaolivre; i++){
            if(usuarios[i].getId().equals(ID)){
                return usuarios[i];
            }
        }
        return null;
    }
    public void adicionarUsuario(Usuario usuario) {
         usuarios[posicaolivre] = usuario;
         posicaolivre++;
    }
    private int buscarPosicaoUsuario(Usuario usuario) throws IdNaoEncontradoException {
        for(int i = 0; i < posicaolivre; i++){
            if(usuarios[i].getId().equals(usuario.getId())){
                return i;
            }
        }
        throw new IdNaoEncontradoException(usuario.getId(), "usuário");
    }
    public void removerUsuario(Usuario usuario) throws IdNaoEncontradoException {
        int posicao = buscarPosicaoUsuario(usuario);
        usuarios[posicao] = usuarios[posicaolivre-1];
        posicaolivre--;
    }
    public Usuario[] getRepositorio(){
        return usuarios;
    }
}
