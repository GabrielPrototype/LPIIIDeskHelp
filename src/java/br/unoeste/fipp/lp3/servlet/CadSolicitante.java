package br.unoeste.fipp.lp3.servlet;

import br.unoeste.fipp.lp3.dao.SolicitanteDAO;
import br.unoeste.fipp.lp3.entities.Funcionario;
import br.unoeste.fipp.lp3.entities.Solicitante;
import br.unoeste.fipp.lp3.persistencia.DAOException;
import br.unoeste.fipp.lp3.util.Erro;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "CadSolicitante", urlPatterns = {"/logado/cad_solicitante.do"})
public class CadSolicitante extends HttpServlet {

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
            throws ServletException, IOException, DAOException {
        Erro erros = new Erro();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (request.getParameter("sel") != null) {

            try {
                Solicitante selecionado = SolicitanteDAO.busca(request.getParameter("sel"));
                if (selecionado == null) {
                    erros.add("Não cadastrado.");
                } else {
                    request.setAttribute("solicitante", selecionado);
                    request.setAttribute("alterando", true);
                }
            } catch (Exception ex) {
                erros.add("Uso inválido.");
            }

        }
        boolean inserir = request.getParameter("bInserir") != null;
        boolean alterar = request.getParameter("bAlterar") != null;
        boolean sair = request.getParameter("bSair") != null;
        if (sair) {
            RequestDispatcher rd = request.getRequestDispatcher("/logado/menu.jsp");
            rd.forward(request, response);
        }
        if (inserir || alterar) {

            Solicitante solicitante = new Solicitante();

            //to do
            // efetuar tratamento de email
            solicitante.setTheEmail(request.getParameter("txtEmail"));
            solicitante.setNome(request.getParameter("txtNome"));
            solicitante.setTelefone(request.getParameter("txtTelefone"));
            solicitante.setObservacao(request.getParameter("txtObservacao"));

            if (solicitante.getNome() == null || solicitante.getNome().isEmpty()) {
                erros.add("Nome não informado.");
            }
            if (solicitante.getTheEmail() == null || solicitante.getTheEmail().isEmpty()) {
                erros.add("Email não informado.");
            }

            //Validações
            if (erros.isEmpty()) {
                try {
                    if (inserir) {
                        SolicitanteDAO.insere(solicitante);
                    } else {
                        SolicitanteDAO.altera(solicitante);
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
                SolicitanteDAO.exclui(request.getParameter("del"));
            } catch (DAOException ex) {
                Logger.getLogger(CadSolicitante.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        List<Solicitante> cadastrados;
        cadastrados = SolicitanteDAO.lista();
        request.setAttribute("erros", erros);
        request.setAttribute("cadastrados", cadastrados);
        RequestDispatcher rd = request.getRequestDispatcher("/logado/cad_solicitante.jsp");
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
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(CadSolicitante.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(CadSolicitante.class.getName()).log(Level.SEVERE, null, ex);
        }
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
