/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.dao;

import br.unoeste.fipp.lp3.entities.Solicitante;
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
public class SolicitanteDAO{
    
    /*
    public static List<Solicitante> lista()
    {
        List<Solicitante> temp = new ArrayList();
        String sql="select ati_codigo,ati_descricao,ati_dtinicio,ati_dtfim,fun_codigo,sta_codigo,sol_email from atividade a"+
                    "inner join funcionario f on a.fun_codigo=f.fun_codigo"+
                    "inner join status st on st.sta_codigo=a.sta_codigo"+ 
                    "inner join solicitacao s on s.sol_email=a.sol_email  ORDER BY ati_codigo;";
        try(Connection conn = Conexao.abre())
        {
            if(conn!=null)
            {
                try(Statement st = conn.createStatement()) 
                {
                    try(ResultSet rs = st.executeQuery(sql))
                    {
                        
                    } 
                    catch (Exception e)
                    {
                        
                    }
                }
                catch (Exception e) 
                {
                }
            }
        }
        catch(SQLException ex){
        }
        return temp;  
    }
    */
    
    public static Solicitante busca(String str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Solicitante busca(int cod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void insere(Solicitante solicitante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void altera(Solicitante solicitante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void exclui(int cod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
