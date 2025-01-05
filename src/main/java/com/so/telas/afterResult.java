package com.so.telas;

import com.so.algoritmos.RoundRobin;
import com.so.algoritmos.SJF;
import com.so.algoritmos.Processo;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class afterResult {

    @FXML
    private TableColumn<?, ?> btr;

    @FXML
    private Text esperaValue;

    @FXML
    private TableColumn<?, ?> esperac;
    
    @FXML
    private Button home;
    
    @FXML
    private TableColumn<?, ?> nomec;

    @FXML
    private TableColumn<?, ?> prioridadec;

    @FXML
    private TableView<Processo> resultTable;

    @FXML
    private TableColumn<?, ?> trc;

    @FXML
    private Text turnaroundValue;

    public void initialLoad(RoundRobin rr) {
        this.initiateColumns();
        List<Processo> processos = rr.getProcessosFinalizados();
        float turnaround = 0;
        float espera = 0;
        for (Processo processo : processos) {
            turnaround += processo.getTurnaround();
            espera += processo.getEspera();
            this.resultTable.getItems().add(processo);
        }
        this.turnaroundValue.setText(String.valueOf(turnaround / processos.size()));
        this.esperaValue.setText(String.valueOf(espera / processos.size()));
    }

    public void initialLoad(SJF sjf) {
        this.initiateColumns();
        List<Processo> processos = sjf.getProcessosFinalizados();
        float turnaround = 0;
        float espera = 0;
        for (Processo processo : processos) {
            turnaround += processo.getTurnaround();
            espera += processo.getEspera();
            this.resultTable.getItems().add(processo);
        }
        this.turnaroundValue.setText(String.valueOf(turnaround / processos.size()));
        this.esperaValue.setText(String.valueOf(espera / processos.size()));
    }
    private void initiateColumns() {
        this.nomec.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.prioridadec.setCellValueFactory(new PropertyValueFactory<>("prioridade"));
        this.btr.setCellValueFactory(new PropertyValueFactory<>("tempoTotal"));
        this.trc.setCellValueFactory(new PropertyValueFactory<>("turnaround"));
        this.esperac.setCellValueFactory(new PropertyValueFactory<>("espera"));
    }

    @FXML
    void mainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
