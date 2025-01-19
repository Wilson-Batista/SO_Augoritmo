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
        //this.processos.add(new Processo(nomeProcesso, tempoExecucao, this.timer));
        if (tempoExecucao <= 0) {
            throw new IllegalArgumentException("Tempo de execução deve ser maior que 0");
        }
        Processo novoProcesso = new Processo(nomeProcesso, tempoExecucao, this.timer);
        this.processos.add(novoProcesso);
    }

    // Avança o timer e executa os processos
    public void avancarTimer() {
        this.timer += 1;
        
        // Adiciona processos aos processadores disponíveis
        while (!processos.isEmpty() && processadores.size() < this.numProcessadores) {
            Processo processo = this.processos.poll();
            if (processo != null) {
                this.processadores.put(processo, this.quantum);
            }
        }

        // Se não houver processos em execução, apenas avança o timer
        if (processadores.isEmpty()) {
            return;
        }

        List<Processo> processosEncerrados = new ArrayList<>();
        List<Processo> processosQuantum = new ArrayList<>();

        // Executa os processos e atualiza seus estados
        for (Map.Entry<Processo, Integer> entry : new ArrayList<>(processadores.entrySet())) {
            Processo processo = entry.getKey();
            int quantumRestante = entry.getValue();

            if (processo.tempoRestante > 0) {
                processo.tempoRestante--;
                processadores.put(processo, quantumRestante - 1);

                // Verifica se o processo terminou
                if (processo.tempoRestante == 0) {
                    processo.encerrarProcesso(this.timer);
                    processosEncerrados.add(processo);
                }
                // Verifica se o quantum acabou
                else if (quantumRestante - 1 <= 0) {
                    processosQuantum.add(processo);
                }
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