package gui;

import fachada.Fachada;
import exceptions.IDExistenteException;
import exceptions.RepositorioCheioException;
import exceptions.ValorInvalidoException;
import exceptions.ValorNuloException;
import modelo.Produto;
import modelo.RequisitoReceita;

import javax.swing.*;
import java.awt.*;

public class FichaTecnicaWindow extends JFrame {
    private Fachada fachada;
    private JList<String> fichaList;
    private DefaultListModel<String> listModel;
    private JButton addIngredientButton;

    public FichaTecnicaWindow() {
        super("Gerenciamento de Fichas Técnicas (Receitas)");
        fachada = Fachada.getInstace();

        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel para listar as fichas
        listModel = new DefaultListModel<>();
        fichaList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(fichaList);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Adicionar Nova Ficha");
        addIngredientButton = new JButton("Adicionar Ingrediente");
        buttonPanel.add(addButton);
        buttonPanel.add(addIngredientButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ações dos botões
        addButton.addActionListener(e -> openAddFichaDialog());
        addIngredientButton.addActionListener(e -> openAddIngredientDialog());

        // Listener para seleção na lista
        fichaList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                addIngredientButton.setEnabled(fichaList.getSelectedIndex() != -1);
            }
        });

        // Estado inicial
        addIngredientButton.setEnabled(false);
        refreshFichaList();
    }

    private void refreshFichaList() {
        int selectedIndex = fichaList.getSelectedIndex();
        listModel.clear();
        try {
            String[] fichas = fachada.getFichasTecnicasParaExibicao();
            if (fichas.length == 0 || (fichas.length == 1 && fichas[0].contains("Nenhuma ficha"))) {
                listModel.addElement("Nenhuma ficha técnica cadastrada.");
            } else {
                for (String f : fichas) {
                    listModel.addElement(f);
                }
            }
        } catch (Exception e) {
            listModel.addElement("Erro ao carregar fichas técnicas.");
        }
        if (selectedIndex != -1 && selectedIndex < listModel.getSize()) {
            fichaList.setSelectedIndex(selectedIndex);
        }
    }

    private void openAddFichaDialog() {
        JTextField idField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField categoriaField = new JTextField();
        JTextArea preparoArea = new JTextArea(5, 20);
        JTextField rendimentoField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("ID (número):"));
        panel.add(idField);
        panel.add(new JLabel("Nome da Receita:"));
        panel.add(nomeField);
        panel.add(new JLabel("Categoria:"));
        panel.add(categoriaField);
        panel.add(new JLabel("Modo de Preparo:"));
        panel.add(new JScrollPane(preparoArea));
        panel.add(new JLabel("Rendimento (porções):"));
        panel.add(rendimentoField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adicionar Nova Ficha Técnica",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String nome = nomeField.getText();
                String categoria = categoriaField.getText();
                String preparo = preparoArea.getText();
                int rendimento = Integer.parseInt(rendimentoField.getText());

                fachada.cadastrarFichaTecnica(id, nome, categoria, preparo, rendimento);

                refreshFichaList();
                JOptionPane.showMessageDialog(this, "Ficha técnica adicionada com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erro de formato: ID e Rendimento devem ser números.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IDExistenteException ex) {
                JOptionPane.showMessageDialog(this, "ID de ficha técnica já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (RepositorioCheioException ex) {
                JOptionPane.showMessageDialog(this, "O repositório de fichas está cheio.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openAddIngredientDialog() {
        String selectedValue = fichaList.getSelectedValue();
        if (selectedValue == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma ficha técnica da lista primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int fichaId = Integer.parseInt(selectedValue.split(" ")[1]);
        Produto[] produtos = fachada.getTodosOsProdutos();
        if (produtos.length == 0) {
            JOptionPane.showMessageDialog(this, "Não há produtos no estoque para adicionar como ingrediente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JComboBox<Produto> produtoComboBox = new JComboBox<>(produtos);
        JTextField quantidadeField = new JTextField();
        JTextField unidadeField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Selecione o Produto:"));
        panel.add(produtoComboBox);
        panel.add(new JLabel("Quantidade:"));
        panel.add(quantidadeField);
        panel.add(new JLabel("Unidade (g, ml, un):"));
        panel.add(unidadeField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adicionar Ingrediente",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                Produto produtoSelecionado = (Produto) produtoComboBox.getSelectedItem();
                double quantidade = Double.parseDouble(quantidadeField.getText());
                String unidade = unidadeField.getText();

                if (produtoSelecionado == null || unidade.trim().isEmpty()) {
                    throw new ValorNuloException("Produto e unidade não podem ser nulos.");
                }

                RequisitoReceita novoRequisito = new RequisitoReceita(produtoSelecionado, quantidade, unidade);
                fachada.adicionarRequisito(fichaId, novoRequisito);

                refreshFichaList();
                JOptionPane.showMessageDialog(this, "Ingrediente adicionado com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erro de formato: Quantidade deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (ValorNuloException | ValorInvalidoException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar ingrediente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
