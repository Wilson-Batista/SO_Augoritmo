package com.so;

import java.io.IOException;
import java.net.URL;//add

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        String caminho = "/com/so/telas/MainMenu.fxml";
        // Carrega a tela principal
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent root = loader.load();
        Scene tela = new Scene(root);
        stage.setTitle("Escalonador");
        stage.setScene(tela);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
