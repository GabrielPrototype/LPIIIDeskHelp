<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/LP3_2Bim_DeskHelp/css/layout.css" rel="stylesheet" type="text/css"/>
        <title>Cadastro de Status</title>
    </head>
    <body>
        <h1>Cadastro de Status</h1>
        <c:if test="${not empty requestScope.erros.mensagens}">
            <ul>
                <c:forEach var="msg" items="${requestScope.erros.mensagens}">
                    <li>${msg}</li>
                    </c:forEach> 
            </ul>
        </c:if>
        <form method="post" action="cad_status.do">
            <label>Código</label>
            <input type="text" name="txtCodigo" readonly="readonly" 
                   value="${empty erros.mensagens ? status.cod : param.txtCodigo}" size="5" maxlength="5"/>
            <label>Nome</label>
            <input type="text" name="txtStatus" 
                   value="${empty erros.mensagens ? status.status : param.txtNome}" size="80" maxlength="100"/>
            <label>Ativo</label>


            <input type="checkbox" name="chkAtiva" value="ativo"   
                   <c:if test="${empty erros.mensagens}">
                       ${status.ativo ? " checked=\"checked\"" : " checked=\"\""}
                   </c:if>
                   <c:if test="${not empty erros.mensagens}">
                       ${param.chkAtiva ? " checked=\"checked\"" : " checked=\"\""}
                   </c:if>
                   >  Ativo? <br>


            <p>
                <input type="submit" name="bInserir" value="Inserir"
                       ${alterando ? "disabled=\"disable\"" : ""}/>
                <input type="submit" name="bAlterar" value="Alterar"
                       ${alterando ? "" : "disabled=\"disable\""}/>
                <input type="submit" name="bLimpar" value="Limpar"/>
            </p>

        </form>
        <c:if test="${not empty cadastrados}">
            <h2>Status Cadastradas</h2>
            <table>
                <tr>
                    <th>Código</th>
                    <th>Status</th>
                    <th>Ativo</th>
                    <th colspan="2">-</th>
                </tr>
                <c:forEach var="stat" items="${cadastrados}">
                    <tr>
                        <td>${stat.cod}</td>
                        <td>${stat.status}</td>
                        <td>${stat.ativo}</td>
                        <td>
                            <a href="cad_status.do?sel=${stat.cod}">Selecionar</a>
                        </td>
                        <td>
                            <a href="cad_status.do?del=${stat.cod}">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
