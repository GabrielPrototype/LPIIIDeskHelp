package br.unoeste.fipp.lp3.filter;

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
@WebFilter(filterName = "VerificadorDeLogin", urlPatterns = {"/logado/*"})
public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain corrente)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session.getAttribute("usuarioLogado") == null) {
            session.invalidate();
            resp.sendRedirect("/cadastro/index.do");
        } else {
            corrente.doFilter(request, response);
//            resp.setHeader("Content-type", "aplication/pdf");
        }
    }

    @Override
    public void destroy() {
    }

}
