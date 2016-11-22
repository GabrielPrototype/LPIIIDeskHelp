/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.servlet;

import br.unoeste.fipp.lp3.dao.ClassificacaoDAO;
import br.unoeste.fipp.lp3.entities.Classificacao;
import br.unoeste.fipp.lp3.persistencia.DAOException;
import br.unoeste.fipp.lp3.util.Erro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "CadClassificacao", urlPatterns = {"/cad_classificacao.do"})
public class CadClassificacao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       Erro erros = new Erro();

        if (request.getParameter("sel") != null) {
            try {
                Classificacao selecionado = ClassificacaoDAO.busca(request.getParameter("cod"));
                if (selecionado == null) {
                    erros.add("Não cadastrado.");
                } else {
                    request.setAttribute("classificacao", selecionado);
                    request.setAttribute("alterando", true);
                }
            } catch (Exception ex) {
                erros.add("Uso inválido.");
            }
        }
        boolean inserir = request.getParameter("bInserir") != null;
        boolean alterar = request.getParameter("bAlterar") != null;
        if (inserir || alterar) {
            
            Classificacao classificacao = new Classificacao();
            
            try {
                classificacao.setCod(Integer.parseInt(request.getParameter("txtCodigo")));
            } catch (NumberFormatException ex) {
                classificacao.setCod(-1);
                erros.add("Código não informado corretamente.");
            }
            
            classificacao.setNome(request.getParameter("txtNome"));
            classificacao.setAtiva(!"".equals(request.getParameter("chkAtiva")));
          
            if (classificacao.getNome() == null || classificacao.getNome().isEmpty()) {
                erros.add("Nome não informado.");
            }

            //Validações
            if (erros.isEmpty()) {
                try {
                    if (inserir) {
                        ClassificacaoDAO.insere(classificacao);
                    } else {
                        ClassificacaoDAO.altera(classificacao);
                    }
                } catch (DAOException ex) {
                    erros.add(ex.getLocalizedMessage());
                }
            }

        }
        if (!erros.isEmpty()) {
            request.setAttribute("alterando", alterar);
        }

        if (request.getParameter("del") != null) {
            try {
                ClassificacaoDAO.exclui(Integer.parseInt(
                        request.getParameter("del")
                ));
            } catch (DAOException ex) {
                erros.add(ex.getLocalizedMessage());
            } catch (NumberFormatException ex) {
                erros.add("Parâmetro inválido");
            }
        }

        List<Classificacao> cadastrados = ClassificacaoDAO.lista();
        request.setAttribute("erros", erros);
        request.setAttribute("cadastrados", cadastrados);
        RequestDispatcher rd = request.getRequestDispatcher("/logado/cad_classificacao.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
