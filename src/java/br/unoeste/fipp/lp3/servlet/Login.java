package br.unoeste.fipp.lp3.servlet;

import br.unoeste.fipp.lp3.dao.FuncionarioDAO;
import br.unoeste.fipp.lp3.entities.Funcionario;
import br.unoeste.fipp.lp3.util.Erro;
import java.io.IOException;
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
@WebServlet(name = "Login", urlPatterns = {"/index.do"})
public class Login extends HttpServlet {

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
        Erro errosDoProcessamento = new Erro();
        if (request.getParameter("bEntrar") != null) {
            String login = request.getParameter("txtLogin");
            String senha = request.getParameter("txtSenha");
            if (login == null || login.isEmpty()) {
                errosDoProcessamento.add("Login não informado.");
            }
            if (senha == null || senha.isEmpty()) {
                errosDoProcessamento.add("Senha não informada.");
            }
            if (errosDoProcessamento.isEmpty()) {
                Funcionario funcionario = FuncionarioDAO.busca(login);
                if (funcionario == null) {
                    errosDoProcessamento.add("Usuário não cadastrado.");
                } else if (funcionario.getSenha().equals(senha)) {
                    request.getSession().setAttribute("usuarioLogado", funcionario);
                    response.sendRedirect("logado/menu.jsp");
                    return;
                } else {
                    errosDoProcessamento.add("Senha inválida.");
                }
            }
        }
        request.getSession().invalidate();

        request.setAttribute("erros", errosDoProcessamento);

        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
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
