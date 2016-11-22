<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/LP3_2Bim_DeskHelp/css/layout.css" rel="stylesheet" type="text/css"/>
        <title>Cadastro de Classificação</title>
    </head>
    <body>
        <h1>Cadastro de Classificação</h1>
        <c:if test="${not empty requestScope.erros.mensagens}">
            <ul>
                <c:forEach var="msg" items="${requestScope.erros.mensagens}">
                    <li>${msg}</li>
                    </c:forEach> 
            </ul>
        </c:if>
        <form method="post" action="cad_classificacao.do">
            <label>Código</label>
            <input type="text" name="txtCodigo" 
                   ${alterando ? "readonly=\"readonly\"": ""}
                   value="${empty erros.mensagens ? classificacao.codigo : param.txtCodigo}" size="5" maxlength="5"/>
            <label>Nome</label>
            <input type="text" name="txtNome" 
                   value="${empty erros.mensagens ? classificacao.nome : param.txtNome}" size="80" maxlength="100"/>
            <label>Nível Acesso</label>


            <input type="checkbox" name="chkAtiva" value="ativo"  
                <c:if test="${empty erros.mensagens}">
                    ${classificacao.admin ? "checked=\"checked\"" : ""}
                </c:if>
                <c:if test="${not empty erros.mensagens}">
                    ${param.selAdmin ? "checked=\"checked\"" : ""}
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
            <h2>Classificações Cadastradas</h2>
            <table>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Ativa</th>
                    <th colspan="2">-</th>
                </tr>
                <c:forEach var="classif" items="${cadastrados}">
                    <tr>
                        <td>${classif.codigo}</td>
                        <td>${classif.nome}</td>
                        <td>${classif.ativa}</td>
                        <td>
                            <a href="cad_classificacao.do?sel=${classif.codigo}">Selecionar</a>
                        </td>
                        <td>
                            <a href="cad_classificacao.do?del=${classif.codigo}">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
