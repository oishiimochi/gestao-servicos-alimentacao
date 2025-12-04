package gui;

import fachada.Fachada;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {

    private Fachada fachada;

    public LoginWindow() {
        super("Login - Gestão de Serviços de Alimentação");
        fachada = Fachada.getInstace();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel principal com margens
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel para os campos de entrada
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField loginField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        fieldsPanel.add(new JLabel("Login (ID):"));
        fieldsPanel.add(loginField);
        fieldsPanel.add(new JLabel("Senha:"));
        fieldsPanel.add(passwordField);

        // Painel para os botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Entrar");
        JButton registerButton = new JButton("Cadastrar");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        mainPanel.add(fieldsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        // Ação do botão de login
        loginButton.addActionListener(e -> {
            String login = loginField.getText();
            String senha = new String(passwordField.getPassword());

            Usuario usuario = fachada.validarEntrada(login, senha);

            if (usuario != null) {
                // Se o login for bem-sucedido, abre a janela principal
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
                // Fecha a janela de login
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login ou senha inválidos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação do botão de cadastro - abre a janela de registro
        registerButton.addActionListener(e -> {
            RegistrarUserWindow registerWindow = new RegistrarUserWindow();
            registerWindow.setVisible(true);
        });
    }

    public static void main(String[] args) {
        // Para testar, vamos pré-cadastrar um usuário
        try {
            Fachada.getInstace().adicionarUsuario(
                new Usuario("admin", "admin", "admin", "admin", "dica", enums.TipoUsuario.Administrador)
            );
        } catch (Exception e) {
            // Ignora se o usuário já existir
        }

        SwingUtilities.invokeLater(() -> {
            new LoginWindow().setVisible(true);
        });
    }
}
