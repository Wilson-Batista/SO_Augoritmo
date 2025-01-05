package com.so.telas;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class sjfConfig {

    @FXML
    private Button backButton;

    @FXML
    private TextField numProcessadoresInput;

    @FXML
    private Button startButton;

    @FXML
    void iniciarRR(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SJFrun.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        SJFrun controller = loader.getController();
        int numeroProcessadores = Integer.parseInt(this.numProcessadoresInput.getText());
        if (numeroProcessadores <= 0) {
            numeroProcessadores = 1;
        }
        controller.initData(numeroProcessadores);
        stage.show();
    }

    @FXML
    void menuInicial(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
