package com.example.InterfaceGrafica;

import exceptions.LoginOuSenhaIncorretoException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Usuario;
import org.jetbrains.annotations.NotNull;
import fachada.Fachada;
import javafx.event.ActionEvent;

import java.io.IOException;


public class TelaInicialController {
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;

    public void validarLogin(@NotNull ActionEvent event) {
        String login = txtLogin.getText();
        String senha = txtSenha.getText();
        try {
            Usuario usuario = Fachada.getInstace().validarEntrada(login, senha);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfaceGrafica/TelaRecuperarSenha.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (LoginOuSenhaIncorretoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
