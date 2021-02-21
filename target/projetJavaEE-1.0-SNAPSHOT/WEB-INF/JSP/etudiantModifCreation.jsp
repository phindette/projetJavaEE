
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Etudiant" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Module" %>
<%@ page import="model.Enseignant" %>
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
    List<Groupe> listeGroupes = (List<Groupe>) request.getAttribute("LISTE_GRP_KEY");
    List<Module> listeModules = (List<Module>) request.getAttribute("LISTE_MODULES_KEY");
    if(request.getAttribute("ETU_KEY") != null) {
        Etudiant etudiant = (Etudiant) request.getAttribute("ETU_KEY");
%>
<p>Modification du module :
<form action="<%=application.getContextPath()%>/do/etudiantModifCreationFin" method="post">
    <input type="hidden" name="idEtudiant" value="<%= etudiant.getIdEtudiant()%>">
    <input type="text" name="nomEtudiant" value="<%= etudiant.getNom()%>">
    <input type="text" name="prenomEtudiant" value="<%= etudiant.getPrenom()%>">
    <select id="groupe" name="groupe">
        <%

            for (int i = 0; i < listeGroupes.size();i++) {
                if(etudiant.getGroupe() != null && etudiant.getGroupe().getIdGroupe() == listeGroupes.get(i).getIdGroupe()){
        %>
            <option value="<%=listeGroupes.get(i).getIdGroupe()%>"><%=listeGroupes.get(i).getNom()%> selected</option>
        <% } else{%>
            <option value="<%=listeGroupes.get(i).getIdGroupe()%>"><%=listeGroupes.get(i).getNom()%> </option>
        <%
            }}
        %>
    </select>
    <%
        for (int y = 0; y < listeModules.size();y++) {
            if(!etudiant.getModules().isEmpty() && etudiant.getModules().contains(listeModules.get(y))){
    %>
        <input type="checkbox" name="listeIdModule" checked value="<%= listeModules.get(y).getIdModule()%>"> <%=listeModules.get(y).getNom() %> </input>
        <%}else{%>
        <input type="checkbox" name="listeIdModule" value="<%= listeModules.get(y).getIdModule()%>"> <%=listeModules.get(y).getNom() %> </input>

    <%
        }}
    %>
    <input type="submit" class="button btn-succes" value="Fin" />
</form>

<% } else{ %>
<p>Creation d'un module :
<form action="<%=application.getContextPath()%>/do/etudiantModifCreationFin" method="post">
    <input type="hidden" name="creation" value="true">
    <input type="text" name="nomEtudiant">
    <input type="text" name="prenomEtudiant">
    <select id="groupe" name="groupe">
        <%
            for (int i = 0; i < listeGroupes.size();i++) {
        %>

        <option value="<%=listeGroupes.get(i).getIdGroupe()%>"><%=listeGroupes.get(i).getNom()%> </option>
        <%
            }
        %>
    </select>
    <%
        for (int y = 0; y < listeModules.size();y++) {
    %>
    <input type="checkbox" name="listeIdModule" value="<%= listeModules.get(y).getIdModule()%>"> <%=listeModules.get(y).getNom() %> </input>
    <%}%>
    <%
        }
    %>
    <input type="submit" class="button btn-succes" value="Fin" />
</form>


<jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
