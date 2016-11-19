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
public class Solicitante {
    private String theEmail;
    private String nome;
    private String telefone;
    private String Observacao;

    public Solicitante() {
    }

    public Solicitante(String theEmail, String nome, String telefone, String Observacao) {
        this.theEmail = theEmail;
        this.nome = nome;
        this.telefone = telefone;
        this.Observacao = Observacao;
    }
    

    public String getTheEmail() {
        return theEmail;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setTheEmail(String theEmail) {
        this.theEmail = theEmail;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }
    
    
}