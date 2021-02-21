
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Etudiant" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Absence" %>
<%@ page import="model.Groupe" %>
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
    if(request.getAttribute("GROUPE_KEY") != null) {
        Groupe groupe = (Groupe) request.getAttribute("GROUPE_KEY");
%>
<p>Modification d'un groupe :
<form action="<%=application.getContextPath()%>/do/groupeModifCreationFin" method="post">
    <input type="hidden" name="idGroupe" value="<%=groupe.getIdGroupe()%>">
    Nom : <input type="text" name="nomGroupe" value="<%=groupe.getNom()%>">
    <input type="submit" class="button btn-succes" value="Fin" />
</form>
</p>
<% } else{ %>
<p>Creation d'un groupe :
<form action="<%=application.getContextPath()%>/do/groupeModifCreationFin" method="post">
    <input type="hidden" name="creation" value="true">
    Nom : <input type="text" name="nomGroupe">
    <input type="submit" class="button btn-succes" value="Fin" />
</form>
</p>
<%
    }
%>


<jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
