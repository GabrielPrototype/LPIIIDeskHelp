/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.entities;

/**
 *
 * @author titan
 */
public class Classificacao {
    private int cod;
    private String nome;
    private boolean ativa;

    public Classificacao() {
    }

    public Classificacao(int cod, String nome, boolean ativa) {
        this.cod = cod;
        this.nome = nome;
        this.ativa = ativa;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
    
    
}