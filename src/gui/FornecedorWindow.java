package gui;

import fachada.Fachada;
import modelo.Fornecedor;
import exceptions.IDExistenteException;

import javax.swing.*;
import java.awt.*;

public class FornecedorWindow extends JFrame {
    private final Fachada fachada;
    private final DefaultListModel<String> listModel;

    public FornecedorWindow() {
        super("Gerenciamento de Fornecedores");
        fachada = Fachada.getInstace();
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        JScrollPane scrollPane = new JScrollPane(new JList<>(listModel));
        add(scrollPane, BorderLayout.CENTER);

        JPanel addPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Adicionar Fornecedor");
        addPanel.add(addButton);
        add(addPanel, BorderLayout.SOUTH);

        addButton.addActionListener(_unused -> openAddFornecedorDialog());
        refreshFornecedorList();
    }

    private void refreshFornecedorList() {
        listModel.clear();
        try {
            Fornecedor[] fornecedores = fachada.getTodosOsFornecedores();
            if (fornecedores.length == 0) {
                listModel.addElement("Nenhum fornecedor cadastrado.");
            } else {
                for (Fornecedor f : fornecedores) {
                    listModel.addElement(f.getNome() + " (ID: " + f.getId() + ")");
                }
            }
        } catch (Exception e) {
            listModel.addElement("Erro ao carregar fornecedores.");
        }
    }

    private void openAddFornecedorDialog() {
        JTextField idField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField cnpjField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField emailField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("CNPJ:"));
        panel.add(cnpjField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adicionar Fornecedor",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String id = idField.getText();
                String nome = nomeField.getText();
                String cnpj = cnpjField.getText();
                String telefone = telefoneField.getText();
                String email = emailField.getText();

                Fornecedor novoFornecedor = new Fornecedor(id, nome, cnpj, telefone, email);
                fachada.adicionarFornecedor(novoFornecedor);

                refreshFornecedorList();
                JOptionPane.showMessageDialog(this, "Fornecedor adicionado com sucesso!");
            } catch (IDExistenteException ex) {
                JOptionPane.showMessageDialog(this, "ID de fornecedor já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
