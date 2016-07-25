<%-- 
    Document   : venditore
    Created on : 30-apr-2016, 1.49.57
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




            <c:if test="${venditore == null}">
                <jsp:include page= "comuni/unlogged.jsp" />
            </c:if>
            <c:if test="${venditore != null}">
                <div id="content">

                    <h1>Metti in vendita un nuovo oggetto</h1>
                    <fieldset>
                        <form action="venditore.html" method="GET">
                            <label for="NomeOggetto">Nome dell’oggetto:</label>
                            <input type="text" name="NomeOggetto" placeholder="Nome.." id="NomeOggetto">
                            <label for="ImmagineOggetto">URL dell'Immagine:</label>
                            <input type="url" name="ImmagineOggetto" placeholder="URL Immagine.." id="ImmagineOggetto">
                            <label for="DescrizioneOggetto">Descrizione dell'oggetto:</label>
                            <textarea rows="8" cols="40" name="DescrizioneOggetto" placeholder="Descrizione.."
                                      id="DescrizioneOggetto"></textarea>
                            <label for="PrezzoOggetto">Prezzo dell'oggetto (€):</label>
                            <input type="number" name="PrezzoOggetto" placeholder="Prezzo.." id="PrezzoOggetto">
                            <label for="QuantitaOggetto">Quantità disponibile:</label>
                            <input type="number" name="QuantitaOggetto" placeholder="Quantità.." id="QuantitaOggetto">
                            <input type="submit" name="invia" value="Invia">
                            <input type="reset" name="reset" value="Reset">
                        </form>  
                    </fieldset>






                </div>
            </c:if>
            <div id="magia" ></div>




            <jsp:include page="comuni/footer.jsp" />
        </div>    
    </body>
</html>
