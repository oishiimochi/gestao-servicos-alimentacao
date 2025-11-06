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
        //verificação de entrada
        boolean sucesso = false;
        while (!sucesso) {
            System.out.println("Digite o login: ");
            String login = sc.nextLine();
            System.out.println("Digite o senha: ");
            String senha = sc.nextLine();
            Usuario usuario = fachada.validarEntrada(login, senha);
            if (usuario != null) {
                sucesso = true;
            } else {
                System.out.println("O login ou senha está incorreto. /n");
            }
            if(usuario instanceof Administrador){
                fachada.adicionarAdministrador(126, "Ana", "Dantes006", "60814004", "A de sempre");
            }
            if(usuario instanceof Administrador || usuario instanceof Colaborador){
                fachada.adicionarColaborador();
            }
            sc.close();
        }
    }
}