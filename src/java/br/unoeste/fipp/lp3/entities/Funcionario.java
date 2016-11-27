/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.entities;

import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author titan
 */
public class Funcionario 
{
    private int cod;
    private String nome;
    private String dtContratacaoString;
    private String dtDemissaoString;
    private Date dtContratacao;
    private Date dtDemissao;
    private boolean ativo;
    private String senha;
    private char tipo;

    public Funcionario(int cod, String nome, Date dtContratacao, Date dtDemissão, boolean ativo, String senha, char tipo) {
        this.cod = cod;
        this.nome = nome;
        this.dtContratacao = dtContratacao;
        this.dtDemissao = dtDemissão;
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

    public void setDtDemissao(Date dtDemissão) {
        this.dtDemissao = dtDemissão;
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

    public Date getDtDemissao() {
        return dtDemissao;
    }
    
    public String getDtDemissaoString(){
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(dtDemissao);
                
        return cal.get(Calendar.DAY_OF_MONTH)+"//"+cal.get(Calendar.MONTH)+"//"+cal.get(Calendar.YEAR);
    }
    
    public String getDtContratacaoString(){
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(dtContratacao);
                
        return cal.get(Calendar.DAY_OF_MONTH)+"//"+cal.get(Calendar.MONTH)+"//"+cal.get(Calendar.YEAR);
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