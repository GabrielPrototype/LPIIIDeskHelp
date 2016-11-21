/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.dao;

import br.unoeste.fipp.lp3.entities.Funcionario;
import br.unoeste.fipp.lp3.persistencia.Conexao;
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
                    if(rs.next())
                    {
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
                    if(rs.next())
                    {
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
