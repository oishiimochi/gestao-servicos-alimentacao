package modelo;

import enums.TipoUsuario;

public class Administrador extends Usuario {
    public Administrador(String id, String nome, String login, String senha, String dicaSenha) {
        super(id, nome, login, senha, dicaSenha, TipoUsuario.Administrador);
    }
}
