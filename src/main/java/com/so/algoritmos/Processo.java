package com.so.algoritmos;

/*
 * Classe que representa um processo
 */
public class Processo {
    String nome;
    int tempoTotal;
    int tempoRestante;
    int timerInicio;
    int timerFinal;
    int prioridade;
    int turnaround;
    int espera;

    Processo(String nome, int tempoTotal, int timerInicio) {
        this.nome = nome;
        this.tempoTotal = tempoTotal;
        this.tempoRestante = tempoTotal;
        this.timerInicio = timerInicio;
        this.timerFinal = -1;
    }

    Processo(String nome, int tempoTotal, int timerInicio, int prioridade) {
        this.nome = nome;
        this.tempoTotal = tempoTotal;
        this.tempoRestante = tempoTotal;
        this.timerInicio = timerInicio;
        this.timerFinal = -1;
        this.prioridade = prioridade;
    }

    // Encerra o processo
    public void encerrarProcesso(int timer) {
        this.timerFinal = timer;
        this.turnaround = this.calculateTAT(timer);
        this.espera = this.calculateEspera(timer);
    }

    // Calcula o tempo de turnaround
    public int calculateTAT(int timer) {
        if (this.timerFinal > 0) {
            return (this.timerFinal - this.timerInicio);
        } else {
            return (timer - this.timerInicio);
        }
    }

    // Calcula o tempo de espera
    public int calculateEspera(int timer) {
        if (this.timerFinal > 0) {
            return (this.timerFinal - this.timerInicio - this.tempoTotal);
        } else {
            return (timer + this.tempoRestante - this.timerInicio - this.tempoTotal);
        }
    }

    //Getters and Setters
    public String getNome() {
        return this.nome;
    }

    public int getTempoTotal() {
        return this.tempoTotal;
    }

    public int getTempoRestante() {
        return this.tempoRestante;
    }

    public int getPrioridade() {
        return this.prioridade;
    }

    public int getTurnaround() {
        return this.turnaround;
    }

    public int getEspera() {
        return this.espera;
    }
}
