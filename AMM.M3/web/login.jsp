<%-- 
    Document   : login
    Created on : 30-apr-2016, 1.50.15
    Author     : Baboo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <jsp:include page="comuni/head.jsp?titolo=Login" />

    <body>
        <div id="page">
            <jsp:include page="comuni/header.jsp" />
            <jsp:include page="comuni/sidebar.jsp" />




            <div id="content">

                <h1>Login</h1>

                <fieldset>
                    <c:if test="${(errore != null)}">
                        <h3>username e/o password errati</h3>     
                    </c:if>
                    <form action="login.html" method="post">
                        <label for="user">Username</label>
                        <input type="text" name="user" id="user" placeholder="Username" />
                        <label for="pswd">Password</label>
                        <input type="password" name="pswd" id="pswd" placeholder="Password" />
                        <input type="submit" name="login" value="Login" id="login"/>
                    </form>
                </fieldset>
            </div>

            <div id="magia" ></div>




            <jsp:include page="comuni/footer.jsp" />
        </div>    
    </body>
</html>
