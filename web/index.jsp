<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.unoeste.fipp.lp3.entities.Funcionario"%>
<%@page import="br.unoeste.fipp.lp3.dao.FuncionarioDAO"%>
<%@page import="br.unoeste.fipp.lp3.util.Erro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<jsp:useBean id="erros" scope="request"
             class="br.unoeste.fipp.lp3.util.Erro"/>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/LP3_2Bim_DeskHelp/css/layout.css" rel="stylesheet" type="text/css"/>
        <title>Autenticação de Usuário</title>
    </head>
    <body id="home">
        <h1>Autenticação de Usuário</h1>
        <c:if test="${not empty requestScope.erros.mensagens}">
            <ul>
                <c:forEach var="msg" items="${requestScope.erros.mensagens}">
                    <li>${msg}</li>
                    </c:forEach> 
            </ul>
        </c:if>
        <div class="rain">
            <div class="border start">
                <form method="post" action="index.do">
                    <label>Login:</label> 
                    <input type="text" name="txtLogin" value="${param.txtLogin}" size="11"/>
                    <br/>
                    <label>Senha:</label> 
                    <input type="password" name="txtSenha" size="10"/>
                    <p>
                        <br/>
                        <input type="submit" name="bEntrar" value="Entrar"/>
                        <input type="submit" name="bLimpa" value="Limpar"/>
                    </p>
                </form>
            </div>
        </div>
    </body>
</html>
