<%-- 
    Document   : cliente
    Created on : 30-apr-2016, 1.39.09
    Author     : Baboo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <jsp:include page="comuni/head.jsp?titolo=Descrizione" />

    <body>
        <div id="page">
            <jsp:include page="comuni/header.jsp" />
            <jsp:include page="comuni/sidebar.jsp" />


            <c:if test="${cliente == null}">
                <jsp:include page= "comuni/unlogged.jsp" />
            </c:if>
            <c:if test="${cliente != null}">
                <div id="content">

                    <h1>Conferma Acquisto</h1>
                    <table id="oggetti">
                        <tr class="intestazione">
                            <th>Nome</th>
                            <th class="foto">Foto</th>
                            <th>Prezzo</th>
                        </tr>

                        <tr class="dispari">
                            <td>${obj.nome}</td>
                            <td class="foto"><img class="oggetto" src="${obj.immagine}" alt="${obj.nome}"></td>
                            <td>${obj.prezzo}</td>
                        </tr>                 
                    </table>
                    <form action="cliente.html?acqId=${obj.id}" method="post">
                        <input type="submit" value="Conferma" id="conferma" name="conferma"/>
                    </form>
                </div>
            </c:if>
            <div id="magia" ></div>




            <jsp:include page="comuni/footer.jsp" />
        </div>    
    </body>
</html>
