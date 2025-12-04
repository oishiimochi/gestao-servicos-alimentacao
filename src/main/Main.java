package main;

import gui.MainWindow;

public class Main {
    public static void main(String[] args) {
        MainWindow.main(args);
        // If you want to keep the console version, comment out the above line and uncomment the code below.
        /*
        Scanner sc = new Scanner(System.in);
        Fachada fachada = Fachada.getInstace();
        Fornecedor fornecedor = new Fornecedor("123", "Paulo", "123456789", "(81)985067123", "paulomorais@gmail.com");
        System.out.println("Iniciando o programa...\n");
        try {
            fachada.adicionarUsuario(new Usuario("123", "Luiz", "Luiz91558", "Estrela", "Sol", TipoUsuario.Administrador));
            fachada.adicionarUsuario(new Usuario("124", "Antonio", "Antonio5674", "Uvas", "videira", TipoUsuario.Gerente));
            fachada.adicionarUsuario(new Usuario("125", "Ana", "Ana1234", "Salvador", "Cristo", TipoUsuario.Colaborador));
            fachada.adicionarFornecedor(fornecedor);
        } catch (IDExistenteException e) {
            System.out.println(e.getMessage());
        }
        Usuario usuario = null;
        //verificação de entrada
        boolean sucesso = false;
        while (!sucesso) {
            System.out.println("Digite o login: ");
            String login = sc.nextLine();
            System.out.println("Digite a senha: ");
            String senha = sc.nextLine();
            usuario = fachada.validarEntrada(login, senha);
            if (usuario != null) {
                sucesso = true;
            } else {
                System.out.println("O login ou senha está incorreto. ");
                System.out.println("Deseja tentar novamente? (S/N)");
                String resposta = sc.nextLine();
                if (resposta.equalsIgnoreCase("N")) {
                    sucesso = true;
                } else {
                    System.out.println("Deseja recuperar senha? (S/N)");
                    resposta = sc.nextLine();
                    // Codigo de recuperação de senha
                    if (resposta.equalsIgnoreCase("S")) {
                        boolean tentativarecuperarsenha = false;
                        while (!tentativarecuperarsenha) {
                            System.out.println("Digite seu ID: ");
                            String ID = sc.nextLine();
                            try {
                                fachada.buscarUsuario(ID);
                                System.out.println("Digite sua dica senha:");
                                String dicasenha = sc.nextLine();
                                if (fachada.recuperarSenha(dicasenha, ID) != null) {
                                    usuario = fachada.recuperarSenha(dicasenha, ID);
                                    System.out.println("Dica Senha correta!\nDigite sua nova senha:");
                                    String novaSenha = sc.nextLine();
                                    fachada.setSenha(usuario, novaSenha);
                                    tentativarecuperarsenha = true;
                                    sucesso = true;
                                } else {
                                    System.out.println("Dica senha incorreta!\nDeseja tentar novamente? (S/N)");
                                    resposta = sc.nextLine();
                                    if (resposta.equalsIgnoreCase("N")) {
                                        tentativarecuperarsenha = true;
                                        sucesso = true;
                                    }
                                }
                            } catch (IdNaoEncontradoException e) {
                                System.out.println(e.getMessage());
                                System.out.println("Deseja tentar novamente? (S/N)");
                                resposta = sc.nextLine();
                                if (resposta.equalsIgnoreCase("N")) {
                                    sucesso = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        */
    }
}