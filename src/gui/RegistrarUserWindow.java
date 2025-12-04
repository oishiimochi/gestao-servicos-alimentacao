package gui;

import fachada.Fachada;
import modelo.Usuario;
import enums.TipoUsuario;
import exceptions.IDExistenteException;

import javax.swing.*;
import java.awt.*;

public class RegistrarUserWindow extends JFrame {
    private final Fachada fachada = Fachada.getInstace();

    public RegistrarUserWindow() {
        super("Cadastrar Usuário");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8,8));

        JPanel form = new JPanel(new GridLayout(0,2,6,6));

        JTextField idField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField loginField = new JTextField();
        JPasswordField senhaField = new JPasswordField();
        JTextField dicaField = new JTextField();
        JComboBox<TipoUsuario> tipoCombo = new JComboBox<>(TipoUsuario.values());

        form.add(new JLabel("ID:")); form.add(idField);
        form.add(new JLabel("Nome:")); form.add(nomeField);
        form.add(new JLabel("Login:")); form.add(loginField);
        form.add(new JLabel("Senha:")); form.add(senhaField);
        form.add(new JLabel("Dica da senha:")); form.add(dicaField);
        form.add(new JLabel("Tipo:")); form.add(tipoCombo);

        JButton cadastrarBtn = new JButton("Cadastrar");
        cadastrarBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String nome = nomeField.getText().trim();
            String login = loginField.getText().trim();
            String senha = new String(senhaField.getPassword());
            String dica = dicaField.getText().trim();
            TipoUsuario tipo = (TipoUsuario) tipoCombo.getSelectedItem();

            if (id.isEmpty() || nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Usuario usuario = new Usuario(id, nome, login, senha, dica, tipo);
            try {
                fachada.adicionarUsuario(usuario);
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso.");
                dispose();
            } catch (IDExistenteException ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(form, BorderLayout.CENTER);
        add(cadastrarBtn, BorderLayout.SOUTH);
    }
}

