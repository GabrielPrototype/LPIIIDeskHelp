/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.dao;

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
public class StatusDAO {

    public static List<Status> lista() {
        List<Status> temp = new ArrayList();
        String sql = "select sta_codigo,sta_status,sta_ativo from status where sta_codigo;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            temp.add(new Status(rs.getInt("sta_codigo"),
                                    rs.getString("sta_status"),
                                    rs.getBoolean("sta_ativo")));
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return temp;
    }

    public static Status busca(String nome) {
        String sql = "select sta_codigo,sta_status,sta_ativo from status where sta_nome = '" + nome + "';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Status(rs.getInt("sta_codigo"),
                                    rs.getString("sta_status"),
                                    rs.getBoolean("sta_ativo"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public static Status busca(int cod) {
        String sql = "select sta_codigo,sta_status,sta_ativo from status where sta_nome = '" + cod + "';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Status(rs.getInt("sta_codigo"),
                                    rs.getString("sta_status"),
                                    rs.getBoolean("sta_ativo"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public static void insere(Status sta)
            throws DAOException {
        String sql = "insert into status (sta_status,sta_ativo) values (?,?);";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, sta.getStatus());
                    st.setBoolean(2, sta.isAtivo());
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro inserindo registro.");
        }
    }

    public static void altera(Status sta)
            throws DAOException {
        String sql = "update status set sta_status=?,sta_ativo=? where sta_codigo=?;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, sta.getStatus());
                    st.setBoolean(2, sta.isAtivo());
                    st.setInt(3,sta.getCod());
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro alterando registro.");
        }
    }

    public static void exclui(int cod) throws DAOException {
        String sql = "delete from status where sta_codigo=?;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, cod);
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro excluindo registro.");
        }
    }
}
