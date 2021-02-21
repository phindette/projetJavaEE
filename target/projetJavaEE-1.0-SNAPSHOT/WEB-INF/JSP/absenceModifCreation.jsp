
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Etudiant" %>
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

<%
    List<Etudiant> listeEtudiants = (List<Etudiant>) request.getAttribute("LISTE_ETU_KEY");
    if(request.getAttribute("ABSENCE_KEY") != null) {
    Absence absence = (Absence) request.getAttribute("ABSENCE_KEY");
%>


<p>Modification d'une absence :
<form action="<%=application.getContextPath()%>/do/absenceModifCreationFin" method="post">
    <input type="hidden" name="idAbsence" value="<%=absence.getIdAbsence()%>">
    <input type="date" name="dateDebut" value="<%=absence.getDebut()%>">
    <input type="date" name="dateFin" value="<%=absence.getFin()%>">
    <select id="etudiant" name="etudiant">
        <%
            for (int i = 0; i < listeEtudiants.size();i++) {
                if(absence.getEtudiant().getIdEtudiant() == listeEtudiants.get(i).getIdEtudiant()){


        %>

        <option value="<%=listeEtudiants.get(i).getIdEtudiant()%>" selected><%=listeEtudiants.get(i).getPrenom()%> <%=listeEtudiants.get(i).getNom()%> </option>

        <%
            }else{

        %>
        <option value="<%=listeEtudiants.get(i).getIdEtudiant()%>"><%=listeEtudiants.get(i).getPrenom()%> <%=listeEtudiants.get(i).getNom()%> </option>

        <%
        }}
    %>

    </select>
    <input type="submit" class="button btn-succes" value="Fin" />
    </form>
    </p>
<% } else{ %>
    <p>Creation d'une absence :
    <form action="<%=application.getContextPath()%>/do/absenceModifCreationFin" method="post">
        <input type="hidden" name="creation" value="true">
        <input type="date" name="dateDebut">
        <input type="date" name="dateFin">
        <select id="etudiant" name="etudiant">
            <%
                for (int i = 0; i < listeEtudiants.size();i++) {
            %>
            <option value="<%=listeEtudiants.get(i).getIdEtudiant()%>"><%=listeEtudiants.get(i).getPrenom()%> <%=listeEtudiants.get(i).getNom()%> </option>

            <%
                }
            %>

        </select>
        <input type="submit" class="button btn-succes" value="Fin" />
    </form>
    </p>
<%
    }
%>


<jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
