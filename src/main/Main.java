package main;

import modelo.*;
import servico.SistemaGestao;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SistemaGestao sistema = new SistemaGestao();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println("==========================================");
        System.out.println("  SISTEMA DE GESTÃO DE SERVIÇOS DE ALIMENTAÇÃO");
        System.out.println("==========================================");

        while (opcao != 0) {
            System.out.println("\n----- MENU PRINCIPAL -----");
            System.out.println("1. Cadastrar fornecedor");
            System.out.println("2. Cadastrar produto");
            System.out.println("3. Cadastrar ficha técnica");
            System.out.println("4. Registrar venda");
            System.out.println("5. Cadastrar usuário");
            System.out.println("6. Listar produtos");
            System.out.println("7. Listar fichas técnicas");
            System.out.println("8. Listar vendas");
            System.out.println("9. Listar usuários");
            System.out.println("10. Gerar resumo geral");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar quebra de linha

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Cadastro de Fornecedor ---");
                    System.out.print("ID: ");
                    int idF = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Nome: ");
                    String nomeF = scanner.nextLine();
                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String tel = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    Fornecedor fornecedor = new Fornecedor(idF, nomeF, cnpj, tel, email);
                    sistema.adicionarFornecedor(fornecedor);
                    System.out.println("Fornecedor cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- Cadastro de Produto ---");
                    System.out.print("ID: ");
                    int idP = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Nome: ");
                    String nomeP = scanner.nextLine();
                    System.out.print("Unidade de medida: ");
                    String unid = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    double qtd = scanner.nextDouble(); scanner.nextLine();
                    System.out.print("Categoria: ");
                    String cat = scanner.nextLine();
                    System.out.print("Lote: ");
                    String lote = scanner.nextLine();
                    System.out.print("Custo unitário: ");
                    double custo = scanner.nextDouble(); scanner.nextLine();

                    System.out.print("ID do Fornecedor: ");
                    int idFornec = scanner.nextInt(); scanner.nextLine();
                    Fornecedor forn = sistema.buscarFornecedorPorId(idFornec);

                    if (forn == null) {
                        System.out.println("Fornecedor não encontrado! Cadastre-o primeiro.");
                    } else {
                        Produto produto = new Produto(idP, nomeP, unid, qtd, cat, lote, forn,
                                LocalDate.now().plusMonths(6), custo);
                        sistema.adicionarProduto(produto);
                        System.out.println("Produto cadastrado com sucesso!");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Cadastro de Ficha Técnica ---");
                    System.out.print("ID: ");
                    int idFic = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Nome: ");
                    String nomeFic = scanner.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Modo de preparo: ");
                    String preparo = scanner.nextLine();
                    System.out.print("Rendimento (nº porções): ");
                    int rend = scanner.nextInt(); scanner.nextLine();

                    FichaTecnica ficha = new FichaTecnica(idFic, nomeFic, categoria, preparo, rend);
                    sistema.adicionarFicha(ficha);
                    System.out.println("Ficha técnica cadastrada com sucesso!");
                    break;

                case 4:
                    System.out.println("\n--- Registro de Venda ---");
                    System.out.print("ID da venda: ");
                    int idV = scanner.nextInt(); scanner.nextLine();
                    System.out.print("ID da Ficha Técnica (prato): ");
                    int idPrato = scanner.nextInt(); scanner.nextLine();

                    FichaTecnica prato = sistema.buscarFichaPorId(idPrato);
                    if (prato == null) {
                        System.out.println("Ficha técnica não encontrada!");
                        break;
                    }

                    System.out.print("Quantidade vendida: ");
                    int qtdVend = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble(); scanner.nextLine();

                    Venda venda = new Venda(idV, prato, qtdVend, preco, LocalDate.now());
                    sistema.registrarVenda(venda);
                    System.out.println("Venda registrada com sucesso!");
                    break;

                case 5:
                    System.out.println("\n--- Cadastro de Usuário ---");
                    System.out.print("ID: ");
                    int idU = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Nome: ");
                    String nomeU = scanner.nextLine();
                    System.out.print("Login: ");
                    String login = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Dica de senha: ");
                    String dica = scanner.nextLine();

                    System.out.println("Tipo de usuário (1-Admin, 2-Gerente, 3-Colaborador): ");
                    int tipo = scanner.nextInt(); scanner.nextLine();

                    Usuario user;
                    if (tipo == 1) {
                        user = new Administrador(idU, nomeU, login, senha, dica);
                    } else if (tipo == 2) {
                        user = new Gerente(idU, nomeU, login, senha, dica);
                    } else {
                        user = new Colaborador(idU, nomeU, login, senha, dica);
                    }

                    sistema.adicionarUsuario(user);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case 6:
                    sistema.listarProdutos();
                    break;

                case 7:
                    sistema.listarFichas();
                    break;

                case 8:
                    sistema.listarVendas();
                    break;

                case 9:
                    sistema.listarUsuarios();
                    break;

                case 10:
                    sistema.gerarResumoGeral();
                    break;

                case 0:
                    System.out.println("Saindo do sistema... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
