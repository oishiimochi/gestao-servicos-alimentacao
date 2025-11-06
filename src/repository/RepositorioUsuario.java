package repository;

import modelo.*;
import java.util.ArrayList;

public class RepositorioUsuario {

    private ArrayList<Usuario> usuarios;

    public RepositorioUsuario() {
        usuarios = new ArrayList<>();
    }
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public Usuario buscarUsuario(int ID){
        for (Usuario usuario : usuarios) {
            if(usuario.getId() == ID){
                return usuario;
            }
        }
        return null;
    }
    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    public Usuario removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        return usuario;
    }
    public ArrayList<Usuario> getRepositorio(){
        return usuarios;
    }
}
