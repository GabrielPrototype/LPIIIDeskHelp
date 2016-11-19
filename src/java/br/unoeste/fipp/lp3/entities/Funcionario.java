/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.entities;

import java.sql.Date;

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
    
    
}