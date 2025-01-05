package com.so;

import com.so.algoritmos.RoundRobin;
import com.so.algoritmos.SJF;

/*
 * Singleton para armazenar os resultados dos algoritmos
 */
public class Resultados {

    private static Resultados instance;

    //private PrioridadeCooperativo epc;
    private RoundRobin rr;
    private SJF sjf;
    //private SRTF srtf;

    private Resultados() {
    }

    // Singleton
    public static Resultados getInstance() {
        if (instance == null) {
            instance = new Resultados();
        }
        return instance;
    }

    // Salva os resultados dos algoritmos
    public void saveRR(RoundRobin rr) {
        this.rr = rr;
    }

    public void saveSJF(SJF sjf) {
        this.sjf = sjf;
    }
    public RoundRobin getRr() {
        return rr;
    }

    public SJF getSjf() {
        return sjf;
    }

}
