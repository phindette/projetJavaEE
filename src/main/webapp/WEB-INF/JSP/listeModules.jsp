
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
        <th scope="col">Numero module</th>
        <th scope="col">Nom</th>
        <th scope="col">Coefficient</th>
        <th scope="col">Enseignant</th>
        <th scope="col">Etudiants</th>
        <th scope="col">#</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Module> listeModules = (List<Module>) request.getAttribute("LISTE_MODULES_KEY");

        for (int i = 0; i < listeModules.size();i++) {

    %>

    <tr>
        <th scope="row"><%=listeModules.get(i).getIdModule()%></th>
        <td><%= listeModules.get(i).getNom() %></td>
        <td><%=listeModules.get(i).getCoef() %></td>

        <td>
            <% if(listeModules.get(i).getEnseignant() != null){

            %>
            <%=listeModules.get(i).getEnseignant().getNom() %> <%=listeModules.get(i).getEnseignant().getPrenom()%></td>
            <% }else{
                %>
            Pas d'enseignants
            <%} %>
        <td>
            Etudiants :
            <%
                List<Etudiant> listeEtudiants = listeModules.get(i).getEtudiants();

                for (int y = 0; y < listeEtudiants.size();y++) {

            %>
                <%= listeEtudiants.get(y).getNom()%> <%= listeEtudiants.get(y).getPrenom()%>,
            <%
                }
            %>
        </td>
        <td>
            <a class="btn btn-primary"href="<%= application.getContextPath()%>/do/moduleModifierCreer?id=<%=listeModules.get(i).getIdModule()%>">
                Modifier
            </a>
            <a class="btn btn-danger"href="<%= application.getContextPath()%>/do/moduleSupprimer?id=<%=listeModules.get(i).getIdModule()%>">
                Supprimer
            </a>

        </td>
    </tr>

    <% }
    %>
    </tbody>
</table>
</p>
<p><a class="btn btn-primary" href="<%= application.getContextPath()%>/do/moduleModifierCreer?creation=oui">
    Creer un nouveau module
</a>

</p>

<jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
