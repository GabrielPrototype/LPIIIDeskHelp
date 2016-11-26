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
import br.unoeste.fipp.lp3.persistencia.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public static Atividade busca(String descr) {
        String sql = "select * from atividade a "
                + "inner join funcionario f on a.fun_codigo=f.fun_codigo "
                + "inner join status st on st.sta_codigo=a.sta_codigo "
                + "inner join solicitante s on s.sol_email=a.sol_email "
                + "inner join atividadeclassificacao ac on a.ati_codigo=ac.ati_codigo inner join classificacao c on ac.cla_codigo=c.cla_codigo where a.ati_descricao =  '" + descr + "';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {

                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Atividade(rs.getInt("ati_codigo"),
                                    rs.getString("ati_descricao"),
                                    rs.getDate("ati_dtinicio"),
                                    rs.getDate("ati_dtfim"),
                                    new Funcionario(rs.getInt("fun_codigo"), rs.getString("fun_nome"), rs.getDate("fun_dtcontracao"), rs.getDate("fun_dtdemissão"), rs.getBoolean("fun_ativo"), rs.getString("fun_senha"), (char) rs.getByte("fun_tipo")),
                                    new Status(rs.getInt("sta_codigo"), rs.getString("sta_status"), rs.getBoolean("sta_ativo")),
                                    new Solicitante(rs.getString("sol_email"), rs.getString("sol_nome"), rs.getString("sol_telefone"), rs.getString("sol_observacao")),
                                    new Classificacao(rs.getInt("cla_codigo"), rs.getString("cla_nome"), rs.getBoolean("cla_ativa")));
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static Atividade busca(int cod) {
        String sql = "select * from atividade a "
                + "inner join funcionario f on a.fun_codigo=f.fun_codigo "
                + "inner join status st on st.sta_codigo=a.sta_codigo "
                + "inner join solicitante s on s.sol_email=a.sol_email "
                + "where a.ati_cod =  '" + cod + "';";
        String sql2 = "select * from atividadeclassificacao where ati_codigo = ?;";
        Atividade atividade;
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {

                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            atividade = new Atividade(rs.getInt("ati_codigo"),
                                    rs.getString("ati_descricao"),
                                    rs.getDate("ati_dtinicio"),
                                    rs.getDate("ati_dtfim"),
                                    new Funcionario(rs.getInt("fun_codigo"), rs.getString("fun_nome"), rs.getDate("fun_dtcontracao"), rs.getDate("fun_dtdemissão"), rs.getBoolean("fun_ativo"), rs.getString("fun_senha"), (char) rs.getByte("fun_tipo")),
                                    new Status(rs.getInt("sta_codigo"), rs.getString("sta_status"), rs.getBoolean("sta_ativo")),
                                    new Solicitante(rs.getString("sol_email"), rs.getString("sol_nome"), rs.getString("sol_telefone"), rs.getString("sol_observacao"))
                            );
                            try (Statement st2 = conn.createStatement()) {
                                try (ResultSet rs2 = st2.executeQuery(sql2)) {
                                    ArrayList<Classificacao> classificacoes = new ArrayList();
                                    while (rs2.next()) {
                                        classificacoes.add(new Classificacao(rs.getInt("cla_codigo"), rs.getString("cla_nome"), rs.getBoolean("cla_ativa")));
                                    }
                                    atividade.setClassificacoes(classificacoes);
                                }
                            }
                            return atividade;
                        }
                    }
                }

            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void insere(Atividade ativ)
            throws DAOException {
        String sql = "insert into atividade (ati_descricao,ati_dtinicio,ati_dtfim,fun_codigo,sta_codigo,sol_email) values (?,?,?,?,?,?)";
        String sql1 = "insert into atividadeclassificacao(ati_codigo,cla_codigo) values (?,?);";
        //Funcionario fun = new Funcionario();
        //Status sta = new Status();
        //Solicitante sol = new Solicitante();
        //Classificacao cla = new Classificacao();
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {

                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, ativ.getDescricao());
                    st.setDate(2, ativ.getDtInicio());
                    st.setDate(3, ativ.getDtFim());
                    st.setInt(4, ativ.getFuncionario().getCod());
                    st.setInt(5, ativ.getStatus().getCod());
                    st.setString(6, ativ.getSolicitante().getTheEmail());
                    st.executeUpdate();
                }
                try (PreparedStatement st = conn.prepareStatement(sql1)) {
                    st.setInt(1, ativ.getCodigo());
                    st.setInt(2, ativ.getClassificacao().getCod());
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro inserindo registro.");
        }
    }

    public static void altera(Atividade ativ)
            throws DAOException {
        String sql = "update atividade set ati_descricao = ?, ati_dtinicio = ?, ati_dtfim=?,fun_codigo=?,sta_codigo=?,sol_email=? where ati_codigo = ?;";
        String sql1 = "update atividadeclassificacao ati_cod=?,cla_cod=? where ati_cod =?;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, ativ.getDescricao());
                    st.setDate(2, ativ.getDtInicio());
                    st.setDate(3, ativ.getDtFim());
                    st.setInt(4, ativ.getFuncionario().getCod());
                    st.setInt(5, ativ.getStatus().getCod());
                    st.setString(6, ativ.getSolicitante().getTheEmail());
                    st.executeUpdate();
                }
                try (PreparedStatement st = conn.prepareStatement(sql1)) {
                    st.setInt(1, ativ.getCodigo());
                    st.setInt(2, ativ.getClassificacao().getCod());
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro alterando registro.");
        }
    }

    public void exclui() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
