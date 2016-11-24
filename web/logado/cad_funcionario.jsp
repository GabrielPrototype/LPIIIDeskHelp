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
        <form method="post" action="cad_funcionario.do">
            <label>Código</label>
            <input type="text" name="txtCodigo" 
                   ${alterando ? "readonly=\"readonly\"": ""}
                   value="${empty erros.mensagens ? funcionario.codigo : param.txtCodigo}" size="5" maxlength="5"/>
            <label>Nome</label>
            <input type="text" name="txtNome" 
                   value="${empty erros.mensagens ? funcionario.nome : param.txtNome}" size="80" maxlength="100"/>
            <label>Data de Contratação</label>
            <input type="text" name="txtDtContratacao" 
                   value="${empty erros.mensagens ? funcionario.nome : param.DtContratacao}" size="80" maxlength="12"/>
            <label>Data de Demissão</label>
            <input type="text" name="txtDtDemissao" 
                   value="${empty erros.mensagens ? funcionario.nome : param.txtDtDemissao}" size="80" maxlength="12"/>
            <label>Senha</label>
            <input type="password" name="txtSenha" size="8" maxlength="6"/>
            
            <select name="selAdmin">
                <c:if test="${empty erros.mensagens}">
                    <option value="n" ${usuario.tipo == 'n' ? "" : "selected=\"selected\""}>Normal</option>
                    <option value="a" ${usuario.tipo == 'a' ? "selected=\"selected\"" : ""}>Administrador</option>
                </c:if>
                <c:if test="${not empty erros.mensagens}">
                    <option value="n" ${param.tipo == 'n' ? "" : "selected=\"selected\""}>Normal</option>
                    <option value="a" ${param.tipo == 'a' ? "selected=\"selected\"" : ""}>Administrador</option>
                </c:if>
            </select>
            
            <label>Ativo</label>
            <input type="checkbox" name="chkAtiva"    
                <c:if test="${empty erros.mensagens}">
                    ${funcionario.ativo ? " value=\"ativo\" checked=\"checked\"" : " value=\"\""}
                </c:if>
                <c:if test="${not empty erros.mensagens}">
                    ${param.chkAtiva ? " value=\"ativo\" checked=\"checked\"" : " value=\"\""}
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
                <c:forEach var="func" items="${cadastrados}">
                    <tr>
                        <td>${func.codigo}</td>
                        <td>${func.nome}</td>
                        <td>${func.ativa}</td>
                        <td>
                            <a href="cad_funcionario.do?sel=${func.codigo}">Selecionar</a>
                        </td>
                        <td>
                            <a href="cad_funcionario.do?del=${func.codigo}">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
