
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Etudiant" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Absence" %>
<%@ page import="model.Groupe" %>
<%@ page import="model.Enseignant" %>
<%--
  Created by IntelliJ IDEA.
  User: Theophane DOMESTICO
  Date: 05/01/2021
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= application.getInitParameter("title")%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ressources/css/bootstrap.css" />

</head>
<body>

    <jsp:include page='<%= application.getInitParameter("entetedepage")%>'/>
    <%
        if(request.getAttribute("ENSEIGNANT_KEY") != null) {
            Enseignant enseignant = (Enseignant) request.getAttribute("ENSEIGNANT_KEY");
    %>
    <p>Modification d'un enseignant :
    <form action="<%=application.getContextPath()%>/do/enseignantModifCreationFin" method="post">
        <input type="hidden" name="idEnseignant" value="<%=enseignant.getIdEnseignant()%>">
        <input type="text" name="nomEnseignant" value="<%=enseignant.getNom()%>">
        <input type="text" name="prenomEnseignant" value="<%=enseignant.getPrenom()%>">
        <input type="text" name="mdpEnseignant" value="<%=enseignant.getMot_de_passe()%>">
        <input type="submit" class="button btn-succes" value="Fin" />
    </form>
    </p>
    <% } else{ %>
    <p>Creation d'un enseignant :
    <form action="<%=application.getContextPath()%>/do/enseignantModifCreationFin" method="post">
        <input type="hidden" name="creation" value="true">
        Nom : <input type="text" name="nomEnseignant">

        Prenom : <input type="text" name="prenomEnseignant">
        Mot de passe : <input type="text" name="mdpEnseignant">
        <input type="submit" class="button btn-succes" value="Fin" />
    </form>
    </p>
    <%
        }
    %>

    <jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>
</body>
</html>
