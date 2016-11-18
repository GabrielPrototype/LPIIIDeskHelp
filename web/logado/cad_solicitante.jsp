<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/cadastro/css/layout.css" rel="stylesheet" type="text/css"/>
        <title>Cadastro de Usuário</title>
    </head>
    <body>
        <h1>Cadastro de Usuário</h1>
        <c:if test="${not empty requestScope.erros.mensagens}">
            <ul>
                <c:forEach var="msg" items="${requestScope.erros.mensagens}">
                    <li>${msg}</li>
                    </c:forEach> 
            </ul>
        </c:if>
        <form method="post" action="cad_solicitante.do">
            <label>Código</label>
            <input type="text" name="txtCodigo" 
                   ${alterando ? "readonly=\"readonly\"": ""}
                   value="${empty erros.mensagens ? usuario.codigo : param.txtCodigo}" size="5" maxlength="5"/>
            <label>Nome</label>
            <input type="text" name="txtNome" 
                   value="${empty erros.mensagens ? usuario.nome : param.txtNome}" size="80" maxlength="100"/>
            <label>Login</label>
            <input type="text" name="txtLogin" 
                   value="${empty erros.mensagens ? usuario.login : param.txtLogin}" size="8" maxlength="6"/>
            <label>Senha</label>
            <input type="password" name="txtSenha" size="8" maxlength="6"/>
            <label>Nível Acesso</label>


            <select name="selAdmin">
                <c:if test="${empty erros.mensagens}">
                    <option value="false" ${usuario.admin ? "" : "selected=\"selected\""}>Normal</option>
                    <option value="true" ${usuario.admin ? "selected=\"selected\"" : ""}>Administrador</option>
                </c:if>
                <c:if test="${not empty erros.mensagens}">
                    <option value="false" ${param.selAdmin ? "" : "selected=\"selected\""}>Normal</option>
                    <option value="true" ${param.selAdmin ? "selected=\"selected\"" : ""}>Administrador</option>
                </c:if>
            </select>


            <p>
                <input type="submit" name="bInserir" value="Inserir"
                       ${alterando ? "disabled=\"disable\"" : ""}/>
                <input type="submit" name="bAlterar" value="Alterar"
                       ${alterando ? "" : "disabled=\"disable\""}/>
                <input type="submit" name="bLimpar" value="Limpar"/>
            </p>

        </form>
        <c:if test="${not empty cadastrados}">
            <h2>Usuários Cadastrados</h2>
            <table>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Login</th>
                    <th>Nível</th>
                    <th colspan="2">-</th>
                </tr>
                <c:forEach var="user" items="${cadastrados}">
                    <tr>
                        <td>${user.codigo}</td>
                        <td>${user.nome}</td>
                        <td>${user.login}</td>
                        <td>${user.admin}</td>
                        <td>
                            <a href="cad_usuario.do?sel=${user.codigo}">Selecionar</a>
                        </td>
                        <td>
                            <a href="cad_usuario.do?del=${user.codigo}">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
