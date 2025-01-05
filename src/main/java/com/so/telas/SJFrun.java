package com.so.telas;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import com.so.Resultados;
import com.so.algoritmos.Processo;
import com.so.algoritmos.SJF;

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

public class SJFrun {

    @FXML
    private Button avancarTimer;

    @FXML
    private TextArea inProcess;

    @FXML
    private Text numProcessadores;

    @FXML
    private TextField processName;

    @FXML
    private TextArea processQueue;

    @FXML
    private TextField tempoTotal;

    @FXML
    private Text timerValue;

    private SJF sjf;

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
        this.sjf.adicionarProcesso(nomeProcesso, bursTime);
        this.atualizarTexto();
    }

    @FXML
    void avancarTimer(ActionEvent event) {
        this.sjf.avancarTimer();
        this.atualizarTexto();
    }

    public void initData(int numProcessadores) {
        this.sjf = new SJF(numProcessadores);
        this.numProcessadores.setText(Integer.toString(numProcessadores));
    }

    private void atualizarTexto() {
        this.inProcess.setText("Nome | Tempo Restante\n");
        this.processQueue.setText("Nome | Tempo Restante\n");
        List<Processo> processadores = this.sjf.getProcessadores();
        Queue<Processo> processos = this.sjf.getProcessos();
        for (Processo processo : processadores) {
            this.inProcess.appendText(processo.getNome() + " | " + processo.getTempoRestante() + "\n");
        }
        for (Processo processo : processos) {
            this.processQueue.appendText(processo.getNome() + " | " + processo.getTempoRestante() + "\n");
        }
        this.timerValue.setText(Integer.toString(this.sjf.getTimer()));
    }

    @FXML
    void encerrarExecucao(ActionEvent event) throws IOException {
        Resultados.getInstance().saveSJF(sjf);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afterResult.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        afterResult controller = loader.getController();
        controller.initialLoad(this.sjf);
        stage.show();
    }
}
