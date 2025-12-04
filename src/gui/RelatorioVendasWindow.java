package gui;

import fachada.Fachada;
import modelo.ItemVenda;
import modelo.Venda;

import javax.swing.*;
import java.awt.*;

public class RelatorioVendasWindow extends JFrame {

    private Fachada fachada;
    private JTextArea reportArea;

    public RelatorioVendasWindow() {
        super("Relatório de Vendas");
        fachada = Fachada.getInstace();

        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(reportArea);

        add(scrollPane, BorderLayout.CENTER);

        loadReport();
    }

    private void loadReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Relatório Detalhado de Vendas ===\n\n");

        Venda[] vendas = fachada.getHistoricoVendas();

        if (vendas.length == 0) {
            sb.append("Nenhuma venda registrada até o momento.");
        } else {
            for (Venda venda : vendas) {
                sb.append("------------------------------------------------------------------------\n");
                sb.append(String.format("Venda ID: %s | Data: %s\n", venda.getId(), venda.getDataVenda()));
                sb.append("Itens vendidos:\n");

                for (ItemVenda item : venda.getItens()) {
                    double custoPorcao = item.getFichaTecnica().calcularCustoPorPorcao();
                    double precoUnitario = item.getPrecoVenda();
                    double lucroUnitario = precoUnitario - custoPorcao;

                    sb.append(String.format("  - Item: %s (Qtd: %d)\n", item.getFichaTecnica().getNome(), item.getQuantidade()));
                    sb.append(String.format("      > Custo Unitário: R$%.2f\n", custoPorcao));
                    sb.append(String.format("      > Preço Venda Unitário: R$%.2f\n", precoUnitario));
                    sb.append(String.format("      > Lucro Unitário: R$%.2f\n", lucroUnitario));
                    sb.append(String.format("      > Subtotal (Item): R$%.2f\n", item.getSubtotal()));
                    sb.append(String.format("      > Lucro Total (Item): R$%.2f\n\n", item.calcularLucroTotalItem()));
                }
                sb.append(String.format("Resumo da Venda -> Receita Total: R$%.2f | Lucro Bruto Total: R$%.2f\n",
                        venda.calcularReceitaTotal(), venda.calcularLucroBruto()));
                sb.append("------------------------------------------------------------------------\n\n");
            }
        }

        reportArea.setText(sb.toString());
        reportArea.setCaretPosition(0); // Rola para o topo
    }
}
