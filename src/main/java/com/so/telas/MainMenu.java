package com.so.telas;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenu {

    @FXML
    private Button results;

    @FXML
    private Button rr;

    @FXML
    private Button ssjf;

    // Abre a tela de configuração do algoritmo de round robin
    @FXML
    void rrConfig(ActionEvent event) throws IOException {
        
            Parent root = FXMLLoader.load(getClass().getResource("RRConfig.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }

    // Abre a tela de resultados gerais
    @FXML
    void showResults(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showResult.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            showResult controller = loader.getController();
            controller.initialLoad();
            stage.show();
    }

    // Abre a tela de configuração do algoritmo de shortest job first
    @FXML
    void sjfConfig(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("sjfConfig.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

}
