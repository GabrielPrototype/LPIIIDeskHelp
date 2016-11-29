<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/LP3_2Bim_DeskHelp/css/layout.css" rel="stylesheet" type="text/css"/>
        <title>Cadastro de Solicitante</title>
    </head>
    <body id="home">
        <h1>Cadastro de Solicitante</h1>
        <c:if test="${not empty requestScope.erros.mensagens}">
            <ul>
                <c:forEach var="msg" items="${requestScope.erros.mensagens}">
                    <li>${msg}</li>
                    </c:forEach> 
            </ul>
        </c:if>
        <div class="border start4">
            <form method="post" action="cad_solicitante.do">
                <label>E-Mail</label>
                <input type="text" name="txtEmail" 
                       ${alterando ? "readonly=\"readonly\"": ""}
                       value="${empty erros.mensagens ? solicitante.theEmail : param.txtEmail}" size="80" maxlength="100"/>
                <label>Nome</label>
                <input type="text" name="txtNome" 
                       value="${empty erros.mensagens ? solicitante.nome : param.txtNome}" size="80" maxlength="100"/>
                <label>Telefone</label>
                <input type="text" name="txtTelefone" 
                       value="${empty erros.mensagens ? solicitante.telefone : param.txtTelefone}" size="80" maxlength="15"/>
                <label>Observação</label>
                <input type="text" name="txtObservacao" 
                       value="${empty erros.mensagens ? solicitante.observacao : param.txtObservacao}" size="80" maxlength="40"/>
        </div>
        <p>
            <input type="submit" name="bInserir" value="Inserir"
                   ${alterando ? "disabled=\"disable\"" : ""}/>
            <input type="submit" name="bAlterar" value="Alterar"
                   ${alterando ? "" : "disabled=\"disable\""}/>
            <input type="submit" name="bLimpar" value="Limpar"/>
        </p>

    </form>
    </br>
    </br>
    </br>
    <c:if test="${not empty cadastrados}">
        <h2>Usuários Cadastrados</h2>
        <table>
            <tr>
                <th>E-mail</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Observação</th>
                <th colspan="2">-</th>
            </tr>
            <c:forEach var="solic" items="${cadastrados}">
                <tr>
                    <td>${solic.theEmail}</td>
                    <td>${solic.nome}</td>
                    <td>${solic.telefone}</td>
                    <td>${solic.observacao}</td>
                    <td>
                        <a href="cad_solicitante.do?sel=${solic.theEmail}">Selecionar</a>
                    </td>
                    <td>
                        <a href="cad_solicitante.do?del=${solic.theEmail}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
