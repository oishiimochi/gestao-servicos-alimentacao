package gui;

import fachada.Fachada;
import modelo.Produto;
import exceptions.IDExistenteException;
import exceptions.RepositorioCheioException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class ProdutoWindow extends JFrame {
    private Fachada fachada;
    private JList<String> productList;
    private DefaultListModel<String> listModel;

    public ProdutoWindow() {
        super("Gerenciamento de Produtos");
        fachada = Fachada.getInstace();

        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel para listar produtos
        listModel = new DefaultListModel<>();
        productList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(productList);
        add(scrollPane, BorderLayout.CENTER);

        // Painel para adicionar novo produto
        JPanel addPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Adicionar Novo Produto");
        addPanel.add(addButton);
        add(addPanel, BorderLayout.SOUTH);

        // Ação do botão de adicionar
        addButton.addActionListener(e -> openAddProductDialog());

        // Carregar a lista de produtos inicial
        refreshProductList();
    }

    private void refreshProductList() {
        listModel.clear();
        try {
            Produto[] produtos = fachada.getTodosOsProdutos();
            if (produtos.length == 0) {
                listModel.addElement("Nenhum produto cadastrado.");
            } else {
                for (Produto p : produtos) {
                    listModel.addElement(p.getNome() + " (ID: " + p.getId() + ")");
                }
            }
        } catch (Exception e) {
            listModel.addElement("Erro ao carregar produtos.");
        }
    }

    private void openAddProductDialog() {
        JTextField idField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField unidadeField = new JTextField();
        JTextField estoqueField = new JTextField();
        JTextField categoriaField = new JTextField();
        JTextField localField = new JTextField();
        JTextField precoField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Unidade (kg, L, un):"));
        panel.add(unidadeField);
        panel.add(new JLabel("Estoque Inicial:"));
        panel.add(estoqueField);
        panel.add(new JLabel("Categoria:"));
        panel.add(categoriaField);
        panel.add(new JLabel("Localização:"));
        panel.add(localField);
        panel.add(new JLabel("Preço (custo):"));
        panel.add(precoField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adicionar Novo Produto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String id = idField.getText();
                String nome = nomeField.getText();
                String unidade = unidadeField.getText();
                double estoque = Double.parseDouble(estoqueField.getText());
                String categoria = categoriaField.getText();
                String local = localField.getText();
                double preco = Double.parseDouble(precoField.getText());

                Produto novoProduto = new Produto(id, nome, unidade, estoque, categoria, local, null, LocalDateTime.now().plusYears(1), preco);
                fachada.adicionarProdutoAoEstoque(novoProduto);

                refreshProductList();
                JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erro de formato: Estoque e Preço devem ser números.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IDExistenteException ex) {
                JOptionPane.showMessageDialog(this, "ID de produto já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (RepositorioCheioException ex) {
                JOptionPane.showMessageDialog(this, "O estoque está cheio.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (exceptions.ValorNuloException | exceptions.ValorInvalidoException ex) {
                JOptionPane.showMessageDialog(this, "Erro de valor: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
