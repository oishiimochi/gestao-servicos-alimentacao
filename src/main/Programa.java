package main;

import exceptions.*;
import modelo.*;
import repository.ProdutoRepository;
import servico.CadastroFichaTecnica;
import servico.CadastroVenda;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Programa {

    public static void main(String[] args) {
        System.out.println("=== Iniciando Programa de Teste ===");

        try {
            CadastroFichaTecnica servicoFichaTecnica = new CadastroFichaTecnica();
            CadastroVenda servicoVenda = new CadastroVenda();
            ProdutoRepository estoque = new ProdutoRepository();

            System.out.println("\n=== Cadastrando Produtos no Estoque ===");
            Produto poCafe = new Produto("P01", "Pó de Café Especial", "kg", 1.0, "Mercearia", "L001", null, LocalDateTime.now().plusMonths(6), 80.00);
            Produto acucar = new Produto("P02", "Açúcar Refinado", "kg", 5.0, "Mercearia", "L002", null, LocalDateTime.now().plusYears(1), 12.00);
            Produto agua = new Produto("P03", "Água Mineral (Galão)", "L", 20.0, "Bebidas", "L003", null, LocalDateTime.now().plusYears(2), 3.00);
            Produto farinha = new Produto("P04", "Farinha de Trigo", "kg", 2.0, "Mercearia", "L004", null, LocalDateTime.now().plusMonths(8), 18.00);
            Produto ovo = new Produto("P05", "Ovo de Galinha", "un", 30.0, "Perecíveis", "L005", null, LocalDateTime.now().plusDays(20), 2.50);

            estoque.adicionarProdutoAoEstoque(poCafe);
            estoque.adicionarProdutoAoEstoque(acucar);
            estoque.adicionarProdutoAoEstoque(agua);
            estoque.adicionarProdutoAoEstoque(farinha);
            estoque.adicionarProdutoAoEstoque(ovo);
            System.out.println("Produtos cadastrados com sucesso!");

            System.out.println("\n--- Criando Fichas Técnicas (Receitas) ---");
            servicoFichaTecnica.cadastrarFichaTecnica(1, "Café Expresso", "Bebidas", "Extrair o café na máquina.", 1);
            FichaTecnica fichaCafe = servicoFichaTecnica.buscarFichaTecnicaPorId(1);
            // Quantidades em unidades mais legíveis (15 g, 50 ml)
            fichaCafe.adicionarRequisito(new RequisitoReceita(poCafe, 15, "g"));
            fichaCafe.adicionarRequisito(new RequisitoReceita(agua, 50, "ml"));

            servicoFichaTecnica.cadastrarFichaTecnica(2, "Fatia de Bolo de Fubá", "Confeitaria", "Misturar tudo e assar.", 1);
            FichaTecnica fichaBolo = servicoFichaTecnica.buscarFichaTecnicaPorId(2);
            // informar farinha e açúcar em gramas (120 g, 80 g); ovo em unidades (0.8 un)
            fichaBolo.adicionarRequisito(new RequisitoReceita(farinha, 120, "g"));
            fichaBolo.adicionarRequisito(new RequisitoReceita(acucar, 80, "g"));
            fichaBolo.adicionarRequisito(new RequisitoReceita(ovo, 0.8));

            System.out.println("\n=== Montando o Cardápio ===");
            servicoVenda.adicionarItemAoCatalogo(fichaCafe);
            servicoVenda.adicionarItemAoCatalogo(fichaBolo);
            System.out.println("Itens adicionados ao catálogo de vendas.");
            System.out.println("Cardápio/Catálogo:");
            for (String item : servicoVenda.getCatalogoParaExibicao()) {
                System.out.println(" - " + item);
            }

            System.out.println("\n=== Simulando uma Venda ===");
            Venda novaVenda = new Venda(LocalDate.now());
            novaVenda.adicionarItem(new ItemVenda(fichaCafe, 2));
            novaVenda.adicionarItem(new ItemVenda(fichaBolo, 1));
            servicoVenda.registrarVenda(novaVenda);
            System.out.println("Venda registrada com sucesso!");

            System.out.println("\n=== Relatório Detalhado da Venda ===");
            for (Venda venda : servicoVenda.getHistoricoVendas()) {
                System.out.println("---------------------------------");
                System.out.println("Venda ID: " + venda.getId() + " | Data: " + venda.getDataVenda());
                System.out.println("Itens vendidos:");
                for (ItemVenda item : venda.getItens()) {
                    double custoPorcao = item.getFichaTecnica().calcularCustoPorPorcao();
                    double precoUnitario = item.getPrecoVenda();
                    double lucroUnitario = precoUnitario - custoPorcao;
                    
                    System.out.println(String.format("  - Item: %s (Qtd: %d)", item.getFichaTecnica().getNome(), item.getQuantidade()));
                    System.out.println(String.format("      > Custo Unitário (sem lucro): R$%.2f", custoPorcao));
                    System.out.println(String.format("      > Preço de Venda Unitário: R$%.2f", precoUnitario));
                    System.out.println(String.format("      > Lucro Unitário: R$%.2f", lucroUnitario));
                    System.out.println(String.format("      > Subtotal (Qtd * Preço Venda): R$%.2f", item.getSubtotal()));
                    System.out.println(String.format("      > Lucro Total do Item (Qtd * Lucro Unit): R$%.2f", item.calcularLucroTotalItem()));
                }
                System.out.println("---------------------------------");
                System.out.println(String.format("Resumo da Venda -> Receita Total: R$%.2f | Lucro Bruto Total: R$%.2f", venda.calcularReceitaTotal(), venda.calcularLucroBruto()));
            }

        } catch (ValorNuloException | ValorInvalidoException | RepositorioCheioException | IDExistenteException e) {
            System.err.println("\n!!! OCORREU UM ERRO DURANTE A EXECUÇÃO !!!");
            System.err.println("Mensagem: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== Fim do Programa de Teste ===");
    }
}
