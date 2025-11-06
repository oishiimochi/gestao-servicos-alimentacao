package main;

import java.util.Scanner;
import exceptions.*;
import fachada.Fachada;
import modelo.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Fachada fachada = Fachada.getInstace();
        Administrador administrador = new Administrador(123, "Luiz", "Luiz91558", "Estrela", "Sol");
        Colaborador funcionario = new Colaborador(124, "Ana", "Ana1234", "Salvador", "Cristo");
        Gerente gerente = new Gerente(125, "Antonio", "Antonio5674", "Uvas", "videira");
        Fornecedor fornecedor = new Fornecedor(123, "Paulo", "123456789", "(81)985067123", "paulomorais@gmail.com");
        System.out.println("Iniciando o programa...\n");
        try {
            fachada.adicionarUsuario(administrador);
            fachada.adicionarUsuario(funcionario);
            fachada.adicionarUsuario(gerente);
            fachada.adicionarFornecedor(fornecedor);
            boolean sucesso = false;
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
                        int ID = sc.nextInt();
                        sc.nextLine();
                            try {
                                Usuario tentativa = fachada.buscarUsuario(ID);
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
                                    tentativarecuperarsenha = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
            if(usuario != null) {
                try {
                    if (usuario instanceof Administrador) {
                        System.out.println("1");
                        fachada.adicionarAdministrador(126,"Ana Costa", "ana.costa", "passWord!", "Cidade onde nasci");
                        fachada.adicionarGerente(127, "João", "joao.p", "Nina", "Nome do cachorro");
                    }
                    if (usuario instanceof Administrador || usuario instanceof Gerente) {
                        System.out.println("2");
                        fachada.adicionarColaborador(128, "Antonio", "Amor5674", "1234", "Facil");
                    }
                }catch (IDExistenteException e){
                    System.out.println(e.getMessage());
                }
                try {
                    fachada.removerUsuario(fachada.buscarUsuario(126));
                    if(fachada.buscarUsuario(126) != null){
                        System.out.println("O usuario não foi removido com sucesso");
                    }
                }catch (IdNaoEncontradoException e){
                    System.out.println(e.getMessage());
                }
                try {
                    if(fachada.buscarFornecedor(123) != null){
                        System.out.println("3");
                    }
                    fachada.removerFornecedor(fachada.buscarFornecedor(123));
                    System.out.println("Fornecedor removido com sucesso!");
                }catch (IdNaoEncontradoException e){
                    System.out.println(e.getMessage());
                }


            }
            sc.close();
    }
}