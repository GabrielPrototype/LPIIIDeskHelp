/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unoeste.fipp.lp3.servlet;

import br.unoeste.fipp.lp3.dao.FuncionarioDAO;
import br.unoeste.fipp.lp3.entities.Funcionario;
import br.unoeste.fipp.lp3.persistencia.DAOException;
import br.unoeste.fipp.lp3.util.Erro;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@WebServlet(name = "CadFuncionario", urlPatterns = {"/logado/cad_funcionario.do"})
public class CadFuncionario extends HttpServlet {

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

        String emailexpression = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        Pattern emailpattern = Pattern.compile(emailexpression);
        Matcher emailmatcher;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (request.getParameter("sel") != null) {
            if (((Funcionario) session.getAttribute("usuarioLogado")).getTipo() == 'a') {
                try {
                    Funcionario selecionado = FuncionarioDAO.busca(Integer.parseInt(request.getParameter("sel")));
                    if (selecionado == null) {
                        erros.add("Não cadastrado.");
                    } else {
                        request.setAttribute("funcionario", selecionado);
                        request.setAttribute("alterando", true);
                    }
                } catch (Exception ex) {
                    erros.add("Uso inválido.");
                }
            }
        }

        boolean inserir = request.getParameter("bInserir") != null;
        boolean alterar = request.getParameter("bAlterar") != null;

        if (inserir || alterar) {

            Funcionario funcionario = new Funcionario();
            String dtContratacao, dtDemissao, tipo;

            if (alterar) {
                try {
                    funcionario.setCod(Integer.parseInt(request.getParameter("txtCodigo")));
                } catch (NumberFormatException ex) {
                    funcionario.setCod(-1);
                    erros.add("Código não informado corretamente.");
                }
            }

            funcionario.setNome(request.getParameter("txtNome"));
            funcionario.setAtivo(request.getParameter("chkAtivo") != null && !"".equals(request.getParameter("chkAtivo")));
            funcionario.setSenha(request.getParameter("txtSenha"));
            tipo = request.getParameter("selAdmin");
            dtContratacao = request.getParameter("txtDtContratacao");
            dtDemissao = request.getParameter("txtDtDemissao");

            if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
                erros.add("Nome não informado.");
            }

            if (funcionario.getSenha() == null || funcionario.getSenha().isEmpty()) {
                erros.add("Senha não informada.");
            }

            if (dtContratacao == null || dtContratacao.isEmpty()) {
                erros.add("Data de Contratação não informada.");
            } else {
                emailmatcher = emailpattern.matcher(dtContratacao);
                if (!emailmatcher.matches()) {
                    erros.add("Data de Contratação não corresponde ao formato DD/MM/AAA");
                }
            }

            if (dtDemissao == null || dtContratacao.isEmpty()) {
                erros.add("Data de Contratação não informada.");
            } else {
                emailmatcher = emailpattern.matcher(dtContratacao);
                if (!emailmatcher.matches()) {
                    erros.add("Data de Contratação não corresponde ao formato DD/MM/AAA");
                }
            }

            if (tipo == null || tipo.isEmpty()) {
                erros.add("Tipo invalido");
            } else {
                funcionario.setTipo(tipo.charAt(0));
            }

            //Validações
            if (erros.isEmpty()) {
                String auxdata[];
                Calendar cal = Calendar.getInstance();
                try {
                    auxdata = dtContratacao.split("\\/|-|\\.");
                    cal.set(Calendar.YEAR, Integer.parseInt(auxdata[2]));
                    cal.set(Calendar.MONTH, Integer.parseInt(auxdata[1]));
                    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(auxdata[0]));
                    funcionario.setDtContratacao(cal.getTime());
                } catch (Exception e) {
                }

                try {
                    auxdata = dtDemissao.split("\\/|-|\\.");
                    cal.set(Calendar.YEAR, Integer.parseInt(auxdata[2]));
                    cal.set(Calendar.MONTH, Integer.parseInt(auxdata[1]));
                    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(auxdata[0]));
                    funcionario.setDtDemissao(cal.getTime());
                } catch (Exception e) {
                }

                try {
                    if (inserir) {
                        FuncionarioDAO.insere(funcionario);
                    } else {
                        FuncionarioDAO.altera(funcionario);
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
            if (((Funcionario) session.getAttribute("usuarioLogado")).getTipo() == 'a') {
                try {
                    FuncionarioDAO.exclui(Integer.parseInt(
                            request.getParameter("del")
                    ));
                } catch (DAOException ex) {
                    erros.add(ex.getLocalizedMessage());
                } catch (NumberFormatException ex) {
                    erros.add("Parâmetro inválido");
                }
            }
        }

        List<Funcionario> cadastrados = FuncionarioDAO.lista();
        request.setAttribute("erros", erros);
        request.setAttribute("cadastrados", cadastrados);
        RequestDispatcher rd = request.getRequestDispatcher("/logado/cad_funcionario.jsp");
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
