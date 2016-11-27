/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.entities;

import java.util.Date;


/**
 *
 * @author titan
 */
public class Funcionario 
{
    private int cod;
    private String nome;
    private Date dtContratacao;
    private Date dtDemissão;
    private boolean ativo;
    private String senha;
    private char tipo;

    public Funcionario(int cod, String nome, Date dtContratacao, Date dtDemissão, boolean ativo, String senha, char tipo) {
        this.cod = cod;
        this.nome = nome;
        this.dtContratacao = dtContratacao;
        this.dtDemissão = dtDemissão;
        this.ativo = ativo;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Funcionario() {
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDtContratacao(Date dtContratacao) {
        this.dtContratacao = dtContratacao;
    }

    public void setDtDemissão(Date dtDemissão) {
        this.dtDemissão = dtDemissão;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public Date getDtContratacao() {
        return dtContratacao;
    }

    public Date getDtDemissão() {
        return dtDemissão;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getSenha() {
        return senha;
    }

    public char getTipo() {
        return tipo;
    }
    
    
}