<%-- 
    Document   : sidebar
    Created on : 28-apr-2016, 15.05.22
    Author     : Baboo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar">               
    <ul>

        <li><h2 id="nav">MEN&Ugrave;</h2></li>
        <li><a href="descrizione.html">Descrizione</a></li>
        <li><a href="cliente.html">Cliente</a></li>
        <li><a href="venditore.html">Venditore</a></li>    
        <li>            
            <c:if test="${sessionScope.loggedIn == null}">
                <a href="login.html">Login</a>
            </c:if>
            <c:if test="${sessionScope.loggedIn != null }">
                <a href="logout.html">Logout</a>
            </c:if>       
        </li>
    </ul>
    
        <c:if test="${cliente != null}">
            <div id="benvenuto">
            Benvenuto ${cliente.getNome()}! 
            <br>Il tuo saldo &egrave;: ${saldo} €
            <p>buono shopping</p>
            </div>
        </c:if>
        <c:if test="${sessionScope.venditore != null}">
            <div id="benvenuto">
            Benvenuto ${venditore.getNome()}!
            <p>buona vendita!</p>
                </div>

        </c:if>
</div>
