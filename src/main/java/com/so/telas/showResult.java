package com.so.telas;

import java.io.IOException;

import com.so.Resultados;
//import com.so.algoritmos.PrioridadeCooperativo;
import com.so.algoritmos.Processo;
import com.so.algoritmos.RoundRobin;
import com.so.algoritmos.SJF;
//import com.so.algoritmos.SRTF;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class showResult {

    @FXML
    private Button home;

    @FXML
    private TextArea resultTable;

    @FXML
    void mainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void showRR(ActionEvent event) throws IOException {
        RoundRobin rr = Resultados.getInstance().getRr();
        if (rr != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afterResult.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            afterResult controller = loader.getController();
            controller.initialLoad(rr);
            stage.show();
        }
    }

    @FXML
    void showSjf(ActionEvent event) throws IOException {
        SJF sjf = Resultados.getInstance().getSjf();
        if (sjf != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afterResult.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            afterResult controller = loader.getController();
            controller.initialLoad(sjf);
            stage.show();
        }
    }
    public void initialLoad() {
        this.resultTable.setText("");
        SJF sjf = Resultados.getInstance().getSjf();
        if (sjf != null) {
            float turnaround = 0;
            float espera = 0;
            for (Processo processo : sjf.getProcessosFinalizados()) {
                turnaround += processo.getTurnaround();
                espera += processo.getEspera();
            }
            espera = espera / sjf.getProcessosFinalizados().size();
            turnaround = turnaround / sjf.getProcessosFinalizados().size();
            this.resultTable.appendText("Nome: SJF | Num. Processadores: " + sjf.getNumProcessadores()
                    + " | Num. Processos Finalizados: " + sjf.getProcessosFinalizados().size() + " | Turnaround medio: "
                    + turnaround + " | Tempo de espera medio: " + espera + "\n");
        }

        RoundRobin rr = Resultados.getInstance().getRr();
        if (rr != null) {
            float turnaround = 0;
            float espera = 0;
            for (Processo processo : rr.getProcessosFinalizados()) {
                turnaround += processo.getTurnaround();
                espera += processo.getEspera();
            }
            espera = espera / rr.getProcessosFinalizados().size();
            turnaround = turnaround / rr.getProcessosFinalizados().size();
            this.resultTable.appendText("Nome: Round Robin | Num. Processadores: " + rr.getNumProcessadores()
                    + " | Num. Processos Finalizados: " + rr.getProcessosFinalizados().size() + " | Turnaround medio: "
                    + turnaround + " | Tempo de espera medio: " + espera + "\n");
        }
        
    }
}
