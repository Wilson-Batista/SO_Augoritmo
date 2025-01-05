package com.so.algoritmos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Algoritmo de escalonamento de processos por shortest job first
 */
public class SJF {

    private int timer;
    private Queue<Processo> processos;
    private List<Processo> processosFinalizados;
    private List<Processo> processadores;
    private int numProcessadores;

    public SJF(int numProcessadores) {
        this.processos = new PriorityQueue<>(Comparator.comparingInt(p -> p.tempoTotal));
        this.processosFinalizados = new ArrayList<>();
        this.timer = 0;
        this.numProcessadores = numProcessadores;
        this.processadores = new ArrayList<>();
    }

    // Adiciona um processo na fila de processos
    public void adicionarProcesso(String nomeProcesso, int tempoExecucao) {
        this.processos.add(new Processo(nomeProcesso, tempoExecucao, this.timer));
    }

    // Avança o timer e executa os processos
    public void avancarTimer() {
        this.timer += 1;

        // Adiciona processos aos processadores
        while (processos.size() > 0 && processadores.size() < this.numProcessadores) {
            Processo processo = this.processos.poll();
            this.processadores.add(processo);
        }

        List<Processo> processosEncerrados = new ArrayList<>();

        // Executa os processos
        for (Processo processo : this.processadores) {
            processo.tempoRestante -= 1;

            if (processo.tempoRestante <= 0) {
                processosEncerrados.add(processo);
            }
        }

        // Remove os processos finalizados
        for (Processo processo : processosEncerrados) {
            processo.encerrarProcesso(timer);
            this.processosFinalizados.add(processo);
            this.processadores.remove(processo);
        }
    }

    // Get processos
    public Queue<Processo> getProcessos() {
        return processos;
    }

    // Get processadores
    public List<Processo> getProcessadores() {
        return processadores;
    }

    // Get timer
    public int getTimer() {
        return timer;
    }

    // Get processos finalizados
    public List<Processo> getProcessosFinalizados() {
        return processosFinalizados;
    }

    // Get numProcessadores
    public int getNumProcessadores() {
        return numProcessadores;
    }
}
