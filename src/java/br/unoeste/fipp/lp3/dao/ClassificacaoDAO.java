/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.dao;

import br.unoeste.fipp.lp3.entities.Classificacao;
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
public class ClassificacaoDAO {

    public static List<Classificacao> lista() {
        List<Classificacao> temp = new ArrayList();
        //to do
        String sql = "select cla_codigo, cla_nome, cla_ativa from classificacao ORDER BY cla_nome;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            temp.add(new Classificacao(rs.getInt("cla_codigo"),
                                    rs.getString("cla_nome"),
                                    rs.getBoolean("cla_ativa")));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return temp;
    }

    public static Classificacao busca(String nome) {
        String sql = "select cla_codigo, cla_nome, cla_ativa from classificacao where cla_nome = '" + nome + "';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Classificacao(rs.getInt("cla_codigo"), rs.getString("cla_nome"), rs.getBoolean("cla_ativa"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public static Classificacao busca(int cod) {
        String sql = "select cla_codigo, cla_nome, cla_ativa from classificacao where cla_codigo = '" + cod + "';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Classificacao(rs.getInt("cla_codigo"), rs.getString("cla_nome"), rs.getBoolean("cla_ativa"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public static void insere(Classificacao clas)
            throws DAOException {
        String sql = "insert into classificacao (cla_nome, cla_ativa) values (?,?)";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, clas.getNome());
                    st.setBoolean(2, clas.isAtiva());
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro inserindo registro.");
        }
    }

    public static void altera(Classificacao clas)
            throws DAOException {
        String sql = "update classificacao set cla_nome = ?, cla_ativa = ? where cla_codigo = ?";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, clas.getNome());
                    st.setBoolean(2, clas.isAtiva());
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro alterando registro.");
        }
    }
    public static void exclui(int codigo)
            throws DAOException {
        String sql = "delete from classificacao where cla_codigo = ?";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, codigo);
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro excluindo registro.");
        }
    }
}
