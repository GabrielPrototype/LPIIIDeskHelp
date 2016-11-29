/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.dao;

import br.unoeste.fipp.lp3.entities.Solicitante;
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
public class SolicitanteDAO {

    public static List<Solicitante> lista() {
        List<Solicitante> temp = new ArrayList();
        String sql = "select sol_email, sol_nome, sol_telfone,sol_observacao from solicitante ORDER BY sol_email;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            temp.add(new Solicitante(rs.getString("sol_email"),
                                    rs.getString("sol_nome"),
                                    rs.getString("sol_telefone"),
                                    rs.getString("sol_observacao")));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return temp;
    }

    public static Solicitante busca(String nome) {
        String sql = "select sol_email, sol_nome, sol_telfone,sol_observacao from solicitante where sol_nome = '" + nome + "';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Solicitante(rs.getString("sol_email"),
                                    rs.getString("sol_nome"),
                                    rs.getString("sol_telefone"),
                                    rs.getString("sol_observacao"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public static Solicitante buscaEmail(String email) {
        String sql = "select sol_email, sol_nome, sol_telfone,sol_observacao from solicitante where sol_nome = '" + email + "';";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        if (rs.next()) {
                            return new Solicitante(rs.getString("sol_email"),
                                    rs.getString("sol_nome"),
                                    rs.getString("sol_telefone"),
                                    rs.getString("sol_observacao"));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public static void insere(Solicitante sol)
            throws DAOException {
        String sql = "insert into solicitante (sol_email, sol_nome,sol_telfone,sol_observacao) values (?,?,?,?);";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, sol.getTheEmail());
                    st.setString(2, sol.getNome());
                    st.setString(3, sol.getTelefone());
                    st.setString(4, sol.getObservacao());
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro inserindo registro.");
        }
    }

    public static void altera(Solicitante sol)
            throws DAOException {
        String sql = "update solicitante set sol_nome = ?, sol_telfone = ?, sol_observacao = ? where sol_email = ?";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, sol.getNome());
                    st.setString(2, sol.getTelefone());
                    st.setString(3, sol.getObservacao());
                    st.setString(4, sol.getTheEmail());
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro alterando registro.");
        }
    }

   public static void exclui(String email)
            throws DAOException {
        String sql = "delete from solicitante where sol_email = ?";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, email);
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro excluindo registro.");
        }
    }
}
