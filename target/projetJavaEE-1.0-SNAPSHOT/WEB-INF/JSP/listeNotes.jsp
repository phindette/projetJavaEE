
<%@ page import="model.Groupe" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Note" %>
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
        <th scope="col">Numero note</th>
        <th scope="col">Notation</th>
        <th scope="col">Etudiant</th>
        <th scope="col">#</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Note> listeNotes = (List<Note>) request.getAttribute("LISTE_NOTES_KEY");

        for (int i = 0; i < listeNotes.size();i++) {

    %>

    <tr>
        <th scope="row"><%=listeNotes.get(i).getIdNote()%></th>
        <td><%= listeNotes.get(i).getNotation() %></td>
        <td><%= listeNotes.get(i).getEtudiant().getNom() %> <%= listeNotes.get(i).getEtudiant().getPrenom()%></td>
        <td>
            <a class="btn btn-primary"href="<%= application.getContextPath()%>/do/noteModifierCreer?id=<%=listeNotes.get(i).getIdNote()%>">
                Modifier
            </a>
            <a class="btn btn-danger"href="<%= application.getContextPath()%>/do/noteSupprimer?id=<%=listeNotes.get(i).getIdNote()%>">
                Supprimer
            </a>

        </td>
    </tr>

    <% }
    %>
    </tbody>
</table>
</p>
<p><a class="btn btn-primary" href="<%= application.getContextPath()%>/do/noteModifierCreer?creation=oui">
    Creer une nouvelle note
</a>

</p>
    <jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
