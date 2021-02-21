
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Enseignant" %>
<%@ page import="model.Module" %>
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
        <th scope="col">Numero enseignant</th>
        <th scope="col">Nom</th>
        <th scope="col">Prenom</th>
        <th scope="col">Mot de passe</th>
        <th scope="col">Module</th>
        <th scope="col">#</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Enseignant> listeEnseignants = (List<Enseignant>) request.getAttribute("LISTE_ENS_KEY");

        for (int i = 0; i < listeEnseignants.size();i++) {

    %>

    <tr>
        <th scope="row"><%=listeEnseignants.get(i).getIdEnseignant()%></th>
        <td><%=listeEnseignants.get(i).getNom() %></td>
        <td><%=listeEnseignants.get(i).getPrenom() %></td>
        <td><%=listeEnseignants.get(i).getMot_de_passe() %></td>
        <td>
            Modules :
            <%
                List<Module> listeModule = listeEnseignants.get(i).getModules();

                for (int y = 0; y < listeModule.size();y++) {

            %>
            <%= listeModule.get(y).getNom()%>, coef : <%= listeModule.get(y).getCoef()%>,
            <%
                }
            %>
        </td>
        <td>
            <a class="btn btn-primary"href="<%= application.getContextPath()%>/do/enseignantModifierCreer?id=<%=listeEnseignants.get(i).getIdEnseignant()%>">
                Modifier
            </a>
            <a class="btn btn-danger"href="<%= application.getContextPath()%>/do/enseignantSupprimer?id=<%=listeEnseignants.get(i).getIdEnseignant()%>">
                Supprimer
            </a>

        </td>
    </tr>

    <% }
    %>
    </tbody>
</table>
<p><a class="btn btn-primary" href="<%= application.getContextPath()%>/do/enseignantModifierCreer?creation=oui">
    Creer un nouvel enseignant
</a>

    <jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
