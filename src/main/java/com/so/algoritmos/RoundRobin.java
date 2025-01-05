package com.so.algoritmos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * Algoritmo de escalonamento de processos por round robin
 */
public class RoundRobin {

    private int timer;
    private int quantum;
    Queue<Processo> processos;
    List<Processo> processosFinalizados;
    private Map<Processo, Integer> processadores;
    private int numProcessadores;

    public RoundRobin(int quantum, int numProcessadores) {
        this.processos = new LinkedList<>();
        this.processosFinalizados = new ArrayList<>();
        this.timer = 0;
        this.quantum = quantum;
        this.numProcessadores = numProcessadores;
        this.processadores = new LinkedHashMap<>();
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
            this.processadores.put(processo, this.quantum);
        }

        // Executa os processos
        for (Processo processo : processadores.keySet()) {
            processo.tempoRestante -= 1;
            this.processadores.put(processo, this.processadores.get(processo) - 1);
        }

        List<Processo> processosEncerrados = new ArrayList<>();
        List<Processo> processosQuantum = new ArrayList<>();

        // Verifica se os processos terminaram
        for (Processo processo : processadores.keySet()) {
            if (processo.tempoRestante == 0) {
                processo.encerrarProcesso(this.timer);
                processosEncerrados.add(processo);
            } else if (processadores.get(processo) == 0) {
                processosQuantum.add(processo);
            }
        }

        // Remove os processos finalizados
        for (Processo processo : processosEncerrados) {
            this.processadores.remove(processo);
            this.processosFinalizados.add(processo);
        }

        // Remove os processos que atingiram o quantum
        // e coloca de volta na fila de processos
        for (Processo processo : processosQuantum) {
            this.processadores.remove(processo);
            this.processos.add(processo);
        }
    }

    // Get processos
    public Queue<Processo> getProcessos() {
        return processos;
    }

    // Get processadores
    public Map<Processo, Integer> getProcessadores() {
        return processadores;
    }

    // Get timer
    public int getTimer() {
        return timer;
    }

    // Get processosFinalizados
    public List<Processo> getProcessosFinalizados() {
        return processosFinalizados;
    }

    // Get numProcessadores
    public int getNumProcessadores() {
        return numProcessadores;
    }
}