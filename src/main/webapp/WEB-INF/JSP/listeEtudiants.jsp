
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Module" %>
<%@ page import="model.Etudiant" %>
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

<table class="table">
    <thead>
    <tr>
        <th scope="col">Numero Etudiant</th>
        <th scope="col">Nom</th>
        <th scope="col">Prenom</th>
        <th scope="col">Groupe</th>
        <th scope="col">Modules</th>
        <th scope="col">Notes</th>
        <th scope="col">Absences</th>
        <th scope="col">#</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Etudiant> listeEtudiants = (List<Etudiant>) request.getAttribute("LISTE_ETU_KEY");

        for (int i = 0; i < listeEtudiants.size();i++) {
    %>

    <tr>
        <th scope="row"><%=listeEtudiants.get(i).getIdEtudiant()%></th>
        <td><%= listeEtudiants.get(i).getNom() %></td
        <td><%= listeEtudiants.get(i).getPrenom() %></td>
        <td>
        <% if(listeEtudiants.get(i).getGroupe() != null){

        %>
        <%=listeEtudiants.get(i).getGroupe().getNom() %></td>
        <% }else{
        %>
        Pas de groupe
        <%} %>
        <td>
            <% if(listeEtudiants.get(i).getModules().isEmpty() != true){ %>
            Modules :
            <%
                for (int z = 0; z < listeEtudiants.get(i).getModules().size();z++) {
            %>
            <%=listeEtudiants.get(i).getModules().get(z).getNom() %>

            <% }}else{
            %>
            Pas de modules
            <%} %>
        </td>
        <td>

        <% if(listeEtudiants.get(i).getNotes().isEmpty() != true){ %>
        Notes :
        <%
            for (int y = 0; y < listeEtudiants.get(i).getNotes().size();y++) {
        %>
            <%=listeEtudiants.get(i).getNotes().get(y).getNotation() %>

        <% }}else{
        %>
            Pas de notes
        <%} %>
        </td>
        <td>
            <% if(listeEtudiants.get(i).getAbsence().isEmpty() != true){ %>
            Absences :
            <%
                for (int a = 0; a < listeEtudiants.get(i).getAbsence().size();a++) {
            %>
            <%=listeEtudiants.get(i).getAbsence().get(a).getDebut() %> : <%=listeEtudiants.get(i).getAbsence().get(a).getFin() %>

            <% }}else{
            %>
            Pas d'absence
            <%} %>
        </td>
        <td>
            <a class="btn btn-primary"href="<%= application.getContextPath()%>/do/etudiantModifierCreer?id=<%=listeEtudiants.get(i).getIdEtudiant()%>">
                Modifier
            </a>
            <a class="btn btn-danger"href="<%= application.getContextPath()%>/do/etudiantSupprimer?id=<%=listeEtudiants.get(i).getIdEtudiant()%>">
                Supprimer
            </a>

        </td>
    </tr>

    <% }
    %>
    </tbody>
</table>
</p>
<p><a class="btn btn-primary" href="<%= application.getContextPath()%>/do/etudiantModifierCreer?creation=oui">
    Creer un nouvel etudiant
</a>

</p>

<jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
