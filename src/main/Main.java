package main;

import java.util.Scanner;
import exceptions.*;
import fachada.Fachada;
import modelo.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Fachada fachada = Fachada.getInstace();
        Administrador administrador = new Administrador(123,"Luiz", "Luiz91558", "Estrela", "Sol");
        Colaborador funcionario = new Colaborador(124, "Ana", "Ana1234", "Salvador", "Cristo");
        Gerente gerente = new Gerente(125, "Antonio", "Antonio5674", "Uvas", "videira");
        System.out.println("Iniciando o programa...\n");
        try{
            fachada.adicionarUsuario(administrador);
            fachada.adicionarUsuario(funcionario);
            fachada.adicionarUsuario(gerente);
            boolean sucesso = false;
            while(!sucesso) {
                System.out.println("Digite o login: ");
                String login = sc.nextLine();
                System.out.println("Digite o senha: ");
                String senha = sc.nextLine();
                Usuario usuario = fachada.validarEntrada(login, senha);
                if(usuario != null) {
                    sucesso = true;
                }
                else{
                    System.out.println("O login ou senha está incorreto. /n");
                }
                if(usuario instanceof Administrador){
                    System.out.println("O Usuario é um administrador");
                }
            }
        }catch(IDExistenteException e){
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}