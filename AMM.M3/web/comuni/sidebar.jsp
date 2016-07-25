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
        
        <div id="benvenuto">
        
        <c:if test="${sessionScope.cliente != null}">
            Benvenuto ${cliente.getNome()}!
            <br>Il tuo saldo &egrave;: ${cliente.getSaldo()} €
            <p>buono shopping</p>
            </c:if>
                <c:if test="${sessionScope.venditore != null}">
                    Benvenuto ${venditore.getNome()}!
            <p>buona vendita!</p>
            </c:if>
                    
        </div>
        
    </ul> 
</div>
