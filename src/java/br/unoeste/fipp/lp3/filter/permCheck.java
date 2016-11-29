package br.unoeste.fipp.lp3.filter;

import br.unoeste.fipp.lp3.entities.Funcionario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aluno
 */
@WebFilter(filterName = "VerificadorDePermissao", urlPatterns = {"/logado/cad_status.do", "/logado/cad_status.jsp", "/logado/cad_funcionario.do", "/logado/cad_funcionario.jsp", "/logado/cad_classificacao.do", "/logado/cad_classificacao.jsp"})
public class permCheck implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain corrente)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (((Funcionario) session.getAttribute("usuarioLogado")).getTipo() != 'a') {
            resp.sendRedirect("/LP3_2Bim_DeskHelp/logado/menu.jsp");
        } else {
            corrente.doFilter(request, response);
//            resp.setHeader("Content-type", "aplication/pdf");
        }
    }

    @Override
    public void destroy() {
    }

}
