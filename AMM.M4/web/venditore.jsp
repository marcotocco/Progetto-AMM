
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
                    
                    


                    <c:if test="${erroreAggiunta != null}">
                        <fieldset>
                            <form action="venditore.html" method="GET">
                            <label for="idoggetto">Seleziona oggetto</label>
                            <select name="idoggetto" id="idoggetto">
                                <option value="Nuovo">Nuovo</option>
                                <c:forEach var="obj" items="${listaV}" >
                                    <option value="${obj.id}"> ${obj.nome} </option>
                                </c:forEach> 
                                <input type="submit" value="Elimina" id="elimina" name="elimina"/> 
                                <input type="submit" value="Modifica" id="modifica" name="modifica"/> <%-- da fare --%>
                            </select>   
                            </form>

                            <form action="venditore.html" method="GET">
                                <label for="NomeOggetto">Nome dell’oggetto:</label>
                                <c:if test="${nomeCheck == true}">
                                    <input type="text" name="NomeOggetto" value="${param.NomeOggetto}"  id="NomeOggetto">
                                </c:if>
                                <c:if test="${nomeCheck == false}">
                                    <input class="formerror" type="text" name="NomeOggetto" placeholder="Inserisci nome." id="NomeOggetto">
                                </c:if>
                                <label for="ImmagineOggetto">URL dell'Immagine:</label>
                                <c:if test="${immagineCheck == true}">
                                    <input type="url" name="ImmagineOggetto" value="${param.ImmagineOggetto}" id="ImmagineOggetto">
                                </c:if>
                                <c:if test="${immagineCheck == false}">
                                    <input class="formerror" type="url" name="ImmagineOggetto" placeholder="Inserisci URL." id="ImmagineOggetto">
                                </c:if>
                                <label for="DescrizioneOggetto">Descrizione dell'oggetto:</label>
                                <c:if test="${descrizioneCheck == true}">
                                    <textarea rows="8" cols="40" name="DescrizioneOggetto" value="${param.DescrizioneOggetto}"
                                              id="DescrizioneOggetto"></textarea>
                                </c:if>
                                <c:if test="${descrizioneCheck == false}">
                                    <textarea class="formerror" rows="8" cols="40" name="DescrizioneOggetto" placeholder="Inserisci descrizione."
                                              id="DescrizioneOggetto"></textarea>
                                </c:if>
                                <label for="PrezzoOggetto">Prezzo dell'oggetto (€):</label>
                                <c:if test="${prezzoCheck == true}">
                                    <input type="number" name="PrezzoOggetto" value="${param.PrezzoOggetto}" id="PrezzoOggetto">
                                </c:if>
                                <c:if test="${prezzoCheck == false}">
                                    <input class="formerror" type="number" name="PrezzoOggetto" placeholder="Inserisci prezzo." id="PrezzoOggetto">
                                </c:if>
                                <label for="QuantitaOggetto">Quantità disponibile:</label>
                                <c:if test="${quantitaCheck == true}">
                                    <input type="number" name="QuantitaOggetto" value="${param.QuantitaOggetto}" id="QuantitaOggetto">
                                </c:if>
                                <c:if test="${quantitaCheck == false}">
                                    <input class="formerror" type="number" name="QuantitaOggetto" placeholder="Inserisci quantità." id="QuantitaOggetto">
                                </c:if>
 

                                <input type="submit" name="aggiungi" value="Conferma">
                                <input type="reset" name="reset" value="Reset">
                            </form>  
                        </fieldset>

                    </c:if>



                    <c:if test="${erroreAggiunta == null}">
                        <fieldset>
                            
                            <form action="venditore.html" method="GET">
                            <label for="idoggetto">Seleziona oggetto</label>
                            <select name="idoggetto" id="idoggetto">
                                <option value="Nuovo">Nuovo</option>
                                <c:forEach var="obj" items="${listaV}" >
                                    <option value="${obj.id}"> ${obj.nome} </option>
                                </c:forEach> 
                                <input type="submit" value="Elimina" id="elimina" name="elimina"/>
                                <input type="submit" value="Modifica" id="modifica" name="modifica"/>
                            </select>   
                            </form>
                            
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
                                <input type="submit" name="aggiungi" value="Conferma">
                                <input type="reset" name="reset" value="Invia">
                            </form>  
                        </fieldset>
                    </c:if>
                    
                    
                    
                    
                </div>
            </c:if>
            <div id="magia" ></div>

            <jsp:include page="comuni/footer.jsp" />
        </div>    
    </body>
</html>
