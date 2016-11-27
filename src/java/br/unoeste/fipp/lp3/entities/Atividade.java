/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author titan
 */
public class Atividade {

    private int codigo;
    private String descricao;
    private Date dtInicio;
    private Date dtFim;
    private Funcionario funcionario;
    private Status status;
    private Solicitante solicitante;
    private ArrayList<Classificacao> classificacoes;

    public Atividade() {
    }

    public Atividade(int codigo, String descricao, Date dtInicio, Date dtFim, Funcionario funcionario, Status status, Solicitante solicitante) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.funcionario = funcionario;
        this.status = status;
        this.solicitante = solicitante;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public ArrayList<Classificacao> getClassificacoes() {
        return classificacoes;
    }

    public void setClassificacoes(ArrayList<Classificacao> classificacoes) {
        this.classificacoes = classificacoes;
    }

}
