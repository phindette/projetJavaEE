
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Absence" %>
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
        <th scope="col">Numero absence</th>
        <th scope="col">Nom Etudiant</th>
        <th scope="col">Debut</th>
        <th scope="col">Fin</th>
        <th scope="col">#</th>
    </tr>
    </thead>
    <tbody>
<%
    List<Absence> listeAbsences = (List<Absence>) request.getAttribute("LISTE_ABS_KEY");

    for (int i = 0; i < listeAbsences.size();i++) {

%>

    <tr>
        <th scope="row"><%=listeAbsences.get(i).getIdAbsence()%></th>
        <td><%= listeAbsences.get(i).getEtudiant().getNom() %> <%= listeAbsences.get(i).getEtudiant().getPrenom() %></td>
        <td><%=listeAbsences.get(i).getDebut().toString() %></td>
        <td><%=listeAbsences.get(i).getFin().toString() %></td>
        <td>
            <a class="btn btn-primary"href="<%= application.getContextPath()%>/do/absenceModifierCreer?id=<%=listeAbsences.get(i).getIdAbsence()%>">
                Modifier
            </a>
            <a class="btn btn-danger"href="<%= application.getContextPath()%>/do/absenceSupprimer?id=<%=listeAbsences.get(i).getIdAbsence()%>">
                Supprimer
            </a>

        </td>
    </tr>

        <% }
%>
    </tbody>
</table>
   </p>
   <p><a class="btn btn-primary" href="<%= application.getContextPath()%>/do/absenceModifierCreer?creation=oui">
       Creer un nouvel etudiant
   </a>

   </p>

<jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
