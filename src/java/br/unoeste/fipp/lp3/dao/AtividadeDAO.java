/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.dao;

import br.unoeste.fipp.lp3.entities.Atividade;
import br.unoeste.fipp.lp3.entities.Classificacao;
import br.unoeste.fipp.lp3.entities.Funcionario;
import br.unoeste.fipp.lp3.entities.Solicitante;
import br.unoeste.fipp.lp3.entities.Status;
import br.unoeste.fipp.lp3.persistencia.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author titan
 */
public class AtividadeDAO {

    public static List<Atividade> lista() {
        List<Atividade> temp = new ArrayList();
        String sql = "select * from atividade a "
                + "inner join funcionario f on a.fun_codigo=f.fun_codigo "
                + "inner join status st on st.sta_codigo=a.sta_codigo "
                + "inner join solicitante s on s.sol_email=a.sol_email "
                + "inner join atividadeclassificacao ac on a.ati_codigo=ac.ati_codigo inner join classificacao c on ac.cla_codigo=c.cla_codigo"
                + "ORDER BY a.ati_codigo;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {

                            temp.add(new Atividade(rs.getInt("ati_codigo"),
                                    rs.getString("ati_descricao"),
                                    rs.getDate("ati_dtinicio"),
                                    rs.getDate("ati_dtfim"),
                                    new Funcionario(rs.getInt("fun_codigo"), rs.getString("fun_nome"), rs.getDate("fun_dtcontracao"), rs.getDate("fun_dtdemissão"), rs.getBoolean("fun_ativo"), rs.getString("fun_senha"), (char) rs.getByte("fun_tipo")),
                                    new Status(rs.getInt("sta_codigo"), rs.getString("sta_status"), rs.getBoolean("sta_ativo")),
                                    new Solicitante(rs.getString("sol_email"), rs.getString("sol_nome"), rs.getString("sol_telefone"), rs.getString("sol_observacao")),
                                    new Classificacao(rs.getInt("cla_codigo"), rs.getString("cla_nome"), rs.getBoolean("cla_ativa"))));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return temp;
    }

    public static void busca(String str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void busca() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void insere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void altera() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void exclui() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
