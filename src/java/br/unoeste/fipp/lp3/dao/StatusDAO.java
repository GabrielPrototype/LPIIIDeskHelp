/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.dao;

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

    public static void insere(Status status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void altera(Status status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void exclui(int cod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
