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
public class Status {
    private int cod;
    private String status;
    private boolean ativo;

    public Status() {
    }

    public Status(int cod, String status, boolean ativo) {
        this.cod = cod;
        this.status = status;
        this.ativo = ativo;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}