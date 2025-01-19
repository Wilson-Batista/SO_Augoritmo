package com.so.telas;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Queue;

import com.so.Resultados;
import com.so.algoritmos.Processo;
import com.so.algoritmos.RoundRobin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RRrun {

    @FXML
    private Button avancarTimer;

    @FXML
    private TextArea inProcess;

    @FXML
    private TextField processName;

    @FXML
    private TextArea processQueue;

    @FXML
    private TextField tempoTotal;

    @FXML
    private Text timerValue;

    @FXML
    private Text quantumNumber;

    @FXML
    private Text numProcessadores;

    private RoundRobin roundRobin;

    @FXML
    void addNewProcess(ActionEvent event) {
        String nomeProcesso = this.processName.getText();
        if (nomeProcesso == null || nomeProcesso.isEmpty()) {
            nomeProcesso = "processo_" + new Date().getTime();
        }
        int bursTime = Integer.parseInt(this.tempoTotal.getText());
        if (bursTime <= 0) {
            bursTime = 1;
        }
        this.roundRobin.adicionarProcesso(nomeProcesso, bursTime);
        this.atualizarTexto();
    }

    @FXML
    void avancarTimer(ActionEvent event) {
        this.roundRobin.avancarTimer();
        this.atualizarTexto();
    }

    public void initData(int numProcessadores, int quantum) {
        this.roundRobin = new RoundRobin(quantum, numProcessadores);
        this.quantumNumber.setText(Integer.toString(quantum));
        this.numProcessadores.setText(Integer.toString(numProcessadores));
    }

    private void atualizarTexto() {
        this.inProcess.setText("Nome | Tempo Restante\n");
        this.processQueue.setText("Nome | Tempo Restante\n");
        Map<Processo, Integer> processadores = this.roundRobin.getProcessadores();
        Queue<Processo> processos = this.roundRobin.getProcessos();
        for (Processo processo : processos) {
            this.inProcess.appendText(processo.getNome() + " | " + processo.getTempoRestante() + "\n");
            this.processQueue.appendText(processo.getNome() + " | " + processo.getTempoRestante() + "\n");
        }
        this.timerValue.setText(Integer.toString(this.roundRobin.getTimer()));
    }

    @FXML
    void encerrarExecucao(ActionEvent event) throws IOException {
        Resultados.getInstance().saveRR(this.roundRobin);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afterResult.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        afterResult controller = loader.getController();
        controller.initialLoad(this.roundRobin);
        stage.show();
    }
}
