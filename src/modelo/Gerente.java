package modelo;

import enums.TipoUsuario;

public class Gerente extends Usuario {
    public Gerente(String id, String nome, String login, String senha, String dicaSenha) {
        super(id, nome, login, senha, dicaSenha, TipoUsuario.Gerente);
    }
}
