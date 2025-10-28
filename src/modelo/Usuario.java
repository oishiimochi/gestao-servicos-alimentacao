package modelo;

public abstract class Usuario {
    private int id;
    private String nome;
    private String login;
    private String senha;
    private String dicaSenha;


    public Usuario(int id, String nome, String login, String senha, String dicaSenha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.dicaSenha = dicaSenha;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getDicaSenha() {
        return dicaSenha;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDicaSenha(String dicaSenha) {
        this.dicaSenha = dicaSenha;
    }

    //Autenticar senha
    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    //Recuperar senha
    public String recuperarSenha(String dicaInformada) {
        if (this.dicaSenha.equalsIgnoreCase(dicaInformada)) {
            return senha;
        } else {
            return "Dica incorreta!";
        }
    }
}
