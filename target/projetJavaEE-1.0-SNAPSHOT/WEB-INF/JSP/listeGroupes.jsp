
<%@ page import="model.Groupe" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
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
        <th scope="col">Numero groupe</th>
        <th scope="col">Nom groupe</th>
        <th scope="col">Etudiants</th>
        <th scope="col">#</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Groupe> listeGroupes = (List<Groupe>) request.getAttribute("LISTE_GRP_KEY");

        for (int i = 0; i < listeGroupes.size();i++) {

    %>

    <tr>
        <th scope="row"><%=listeGroupes.get(i).getIdGroupe()%></th>
        <td><%=listeGroupes.get(i).getNom() %></td>
        <td>
            Etudiants :
            <%
                List<Etudiant> listeEtudiant = listeGroupes.get(i).getEtudiants();

                for (int y = 0; y < listeEtudiant.size();y++) {

            %>
            <%= listeEtudiant.get(y).getNom()%> <%= listeEtudiant.get(y).getPrenom()%>,
            <%
                }
            %>
        </td>
        <td>
            <a class="btn btn-primary"href="<%= application.getContextPath()%>/do/groupeModifierCreer?id=<%=listeGroupes.get(i).getIdGroupe()%>">
                Modifier
            </a>
            <a class="btn btn-danger"href="<%= application.getContextPath()%>/do/groupeSupprimer?id=<%=listeGroupes.get(i).getIdGroupe()%>">
                Supprimer
            </a>

        </td>
    </tr>

    <% }
    %>
    </tbody>
</table>
<p><a class="btn btn-primary" href="<%= application.getContextPath()%>/do/groupeModifierCreer?creation=oui">
    Creer un nouveau groupe
</a>

</p>
<jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
