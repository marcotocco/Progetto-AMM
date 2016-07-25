
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <jsp:include page="comuni/head.jsp?titolo=Modifica" />

    <body>
        <div id="page">
            <jsp:include page="comuni/header.jsp" />
            <jsp:include page="comuni/sidebar.jsp" />

            <c:if test="${cliente == null}">
                <jsp:include page= "comuni/unlogged.jsp" />
            </c:if>
            <c:if test="${cliente != null}">
                <div id="content">

                  PARTE NON FINITA
                        
                </div>
            </c:if>
            <div id="magia" ></div>
            <jsp:include page="comuni/footer.jsp" />
        </div>    
    </body>
</html>
