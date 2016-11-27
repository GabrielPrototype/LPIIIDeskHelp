/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.dao;

import br.unoeste.fipp.lp3.entities.Funcionario;
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
public class FuncionarioDAO {

    public static List<Funcionario> lista() {
        List<Funcionario> temp = new ArrayList();
        String sql = "select fun_codigo,fun_nome,fun_dtcontratacao,fun_dtdemissao,fun_ativo,fun_senha,fun_tipo from funcionario where fun_codigo ORDER BY fun_nome;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            temp.add(new Funcionario(rs.getInt("fun_codigo"),
                                    rs.getString("fun_nome"),
                                    rs.getDate("fun_dtcontratacao"),
                                    rs.getDate("fun_dtdemissao"),
                                    rs.getBoolean("fun_ativo"),
                                    rs.getString("fun_senha"),
                                    (char) rs.getByte("fun_tipo")));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return temp;
    }

    public static Funcionario busca(String nome) {
        String sql = "select fun_codigo,fun_nome,fun_dtcontratacao,fun_dtdemissao,fun_ativo,fun_senha,fun_tipo from funcionario where fun_nome = '" + nome + "';";
        try (Connection conn = Conexao.abre()) {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sql)) {
                    if (rs.next()) {
                        return new Funcionario(rs.getInt("fun_codigo"),
                                rs.getString("fun_nome"),
                                rs.getDate("fun_dtcontratacao"),
                                rs.getDate("fun_dtdemissao"),
                                rs.getBoolean("fun_ativo"),
                                rs.getString("fun_senha"),
                                (char) rs.getByte("fun_tipo"));
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static Funcionario busca(int cod) {
        String sql = "select fun_codigo,fun_nome,fun_dtcontratacao,fun_dtdemissao,fun_ativo,fun_senha,fun_tipo from funcionario where fun_codigo = '" + cod + "';";
        try (Connection conn = Conexao.abre()) {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sql)) {
                    if (rs.next()) {
                        return new Funcionario(rs.getInt("fun_codigo"),
                                rs.getString("fun_nome"),
                                rs.getDate("fun_dtcontratacao"),
                                rs.getDate("fun_dtdemissao"),
                                rs.getBoolean("fun_ativo"),
                                rs.getString("fun_senha"),
                                (char) rs.getByte("fun_tipo"));
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void insere(Funcionario fun)
            throws DAOException {
        String sql = "insert into funcionario (fun_nome,fun_dtcontratacao,fun_dtdemissao,fun_ativo,fun_senha,fun_tipo) values (?,?,?,?,?,?);";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, fun.getNome());
                    st.setDate(2, fun.getDtContratacao());
                    st.setDate(3, fun.getDtDemissão());
                    st.setBoolean(4, fun.isAtivo());
                    st.setString(5, fun.getSenha());
                    st.setString(6, "" + fun.getTipo());
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro inserindo registro.");
        }
    }

    public static void altera(Funcionario fun)
            throws DAOException {
        String sql = "update funcionario set fun_nome=?,fun_dtcontratacao = ?, fun_dtdemissao = ?, fun_ativo = ?,fun_senha = ?,fun_tipo = ? where fun_cod = ?";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, fun.getNome());
                    st.setDate(2, fun.getDtContratacao());
                    st.setDate(3, fun.getDtDemissão());
                    st.setBoolean(4, fun.isAtivo());
                    st.setString(5, fun.getSenha());
                    st.setString(6, "" + fun.getTipo());
                    st.setInt(7, fun.getCod());
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro alterando registro.");
        }
    }

    public static void exclui(int cod) throws DAOException {
        String sql = "delete from funcionarios where fun_codigo=?";
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
