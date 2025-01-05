package com.so;

import java.io.IOException;

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
        // Carrega a tela principal
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telas/MainMenu.fxml"));
            Parent root = loader.load();
            Scene tela = new Scene(root);
            stage.setTitle("Escalonador");
            stage.setScene(tela);
            stage.show();
        }catch (Exception e){
            System.out.println(" Erro ============================" + e.toString());
        }
    }

    public static void main(String[] args) {
        launch();
    }

}