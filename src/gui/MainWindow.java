package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fachada.Fachada;

public class MainWindow extends JFrame {
    private Fachada fachada;

    public MainWindow() {
        super("Gestão de Serviços de Alimentação");
        fachada = Fachada.getInstace(); // fixed method name
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Menu Bar ---
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // -- Menu Cadastros --
        JMenu menuCadastros = new JMenu("Cadastros");
        menuBar.add(menuCadastros);

        JMenuItem itemProdutos = new JMenuItem("Produtos (Estoque)");
        itemProdutos.addActionListener(e -> {
            ProdutoWindow produtoWindow = new ProdutoWindow();
            produtoWindow.setVisible(true);
        });
        menuCadastros.add(itemProdutos);

        JMenuItem itemFichas = new JMenuItem("Fichas Técnicas (Receitas)");
        itemFichas.addActionListener(e -> {
            FichaTecnicaWindow fichaWindow = new FichaTecnicaWindow();
            fichaWindow.setVisible(true);
        });
        menuCadastros.add(itemFichas);
        
        JMenuItem itemFornecedores = new JMenuItem("Fornecedores");
        itemFornecedores.addActionListener(e -> {
            FornecedorWindow fornecedorWindow = new FornecedorWindow();
            fornecedorWindow.setVisible(true);
        });
        menuCadastros.add(itemFornecedores);

        // -- Menu Vendas --
        JMenu menuVendas = new JMenu("Vendas");
        menuBar.add(menuVendas);

        JMenuItem itemRegistrarVenda = new JMenuItem("Registrar Nova Venda");
        itemRegistrarVenda.addActionListener(e -> {
            VendaWindow vendaWindow = new VendaWindow();
            vendaWindow.setVisible(true);
        });
        menuVendas.add(itemRegistrarVenda);

        JMenuItem itemRelatorios = new JMenuItem("Relatórios de Vendas");
        itemRelatorios.addActionListener(e -> {
            RelatorioVendasWindow relatorioWindow = new RelatorioVendasWindow();
            relatorioWindow.setVisible(true);
        });
        menuVendas.add(itemRelatorios);


        JLabel welcomeLabel = new JLabel("Bem-vindo ao sistema! Use o menu para navegar.", SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}
