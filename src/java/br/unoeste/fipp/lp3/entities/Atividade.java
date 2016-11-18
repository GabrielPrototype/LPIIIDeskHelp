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
public class Atividade {
    
    private int codigo;
    private String descricao;
    private Date dtInicio;
    private Date dtFim;
    private Funcionario funcionario;
    private Status status;
    private Solicitante solicitante;
    private Classificacao classificacao;
    
    
}
