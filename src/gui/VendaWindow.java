package gui;

import fachada.Fachada;
import modelo.FichaTecnica;
import modelo.ItemVenda;
import modelo.Venda;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;


public class VendaWindow extends JFrame {

    private Fachada fachada;
    private JComboBox<String> itemComboBox;
    private DefaultListModel<String> cartListModel;
    private ItemVenda[] carrinho;
    private int carrinhoSize = 0;
    private static final int CARRINHO_MAX = 100;

    public VendaWindow() {
        super("Registrar Nova Venda");
        fachada = Fachada.getInstace();
        carrinho = new ItemVenda[CARRINHO_MAX];
        carrinhoSize = 0;

        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Painel Superior: Seleção de Itens ---
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder("Adicionar Item ao Carrinho"));

        itemComboBox = new JComboBox<>(fachada.getCatalogoParaExibicao());
        JTextField quantityField = new JTextField("1", 5);
        JButton addButton = new JButton("Adicionar");
        
        topPanel.add(new JLabel("Item do Cardápio:"));
        topPanel.add(itemComboBox);
        topPanel.add(new JLabel("Qtd:"));
        topPanel.add(quantityField);
        topPanel.add(addButton);

        // --- Painel Central: Carrinho de Compras ---
        cartListModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(cartListModel);
        JScrollPane scrollPane = new JScrollPane(cartList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Carrinho"));

        // --- Painel Inferior: Finalizar Venda ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton finalizeButton = new JButton("Finalizar Venda");
        bottomPanel.add(finalizeButton);

        // Adicionando painéis à janela
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- Ações dos Botões ---
        addButton.addActionListener(e -> {
            try {
                String selectedItemStr = (String) itemComboBox.getSelectedItem();
                if (selectedItemStr == null) {
                    JOptionPane.showMessageDialog(this, "Nenhum item selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int idFicha = Integer.parseInt(selectedItemStr.split(" - ")[0].replace("ID: ", ""));
                int quantidade = Integer.parseInt(quantityField.getText());
                FichaTecnica ficha = fachada.buscarFichaTecnicaPorId(idFicha);
                if (ficha != null) {
                    ItemVenda novoItem = new ItemVenda(ficha, quantidade);
                    if (carrinhoSize < CARRINHO_MAX) {
                        carrinho[carrinhoSize++] = novoItem;
                        updateCartList();
                    } else {
                        JOptionPane.showMessageDialog(this, "Carrinho cheio.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        finalizeButton.addActionListener(e -> {
            if (carrinhoSize == 0) {
                JOptionPane.showMessageDialog(this, "O carrinho está vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Venda novaVenda = new Venda(LocalDate.now());
                for(int i = 0; i < carrinhoSize; i++) {
                    novaVenda.adicionarItem(carrinho[i]);
                }
                fachada.registrarVenda(novaVenda);
                JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!");
                dispose(); // Fecha a janela após a venda
            } catch (exceptions.RepositorioCheioException | exceptions.IDExistenteException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao registrar venda: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (exceptions.ValorNuloException ex) {
                JOptionPane.showMessageDialog(this, "Erro de valor nulo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void updateCartList() {
        cartListModel.clear();
        double total = 0;
        for (int i = 0; i < carrinhoSize; i++) {
            ItemVenda item = carrinho[i];
            cartListModel.addElement(String.format("%dx %s - Subtotal: R$%.2f",
                    item.getQuantidade(), item.getFichaTecnica().getNome(), item.getSubtotal()));
            total += item.getSubtotal();
        }
        cartListModel.addElement("----------------------------------------------------");
        cartListModel.addElement(String.format("TOTAL DA VENDA: R$%.2f", total));
    }
}
