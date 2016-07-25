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

                    <div id="acquistato"> 
                        <c:if test="${finito == true}"> <h2>Acquisto Andato a Buon Fine</h2> </c:if>
                        <c:if test="${finito == false}"> <h2>Errore: Non hai abbastanza soldi</h2> </c:if>
                    </div>

                        <h1>OLIO DA BARBA</h1>
                        <table id="oggetti">
                            <tr class="intestazione">
                                <th>Nome</th>
                                <th class="foto">Foto</th>
                                <th>Quantità</th>
                                <th>Prezzo</th>
                                <th>Acquista</th>
                            </tr>

                        <c:forEach var="obj" items="${lista}" >
                            <tr class="dispari">
                                <td>${obj.getNome()}</td>
                                <td class="foto"><img class="oggetto" src="${obj.getImmagine()}" alt="${obj.getNome()}"></td>
                                <td>${obj.getQuantita()}</td>
                                <td>${obj.getPrezzo()}</td>
                                <td><a href="cliente.html?objId=${obj.id}"><img class="cart" src="img/cart.png" alt="Aggiungi al Carrello"></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                        
                </div>
            </c:if>
            <div id="magia" ></div>
            <jsp:include page="comuni/footer.jsp" />
        </div>    
    </body>
</html>
