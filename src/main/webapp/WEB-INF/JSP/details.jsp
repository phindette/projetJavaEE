<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Etudiant" %>
<%@ page import="model.GestionFactory" %><%--
  Created by IntelliJ IDEA.
  User: Theophane DOMESTICO
  Date: 05/01/2021
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detail étudiant</title>
</head>
<body>
    <%
    //Methode avec request
    /*int idEtu = (int) Integer.parseInt(request.getParameter("idEtu"));
    model.Etudiant etudiant = model.GestionFactory.getEtudiantById(idEtu);
    int nbAbsenceEtu = model.GestionFactory.getAbsencesByEtudiantId(idEtu); */

        int nbAbsence = (int) request.getAttribute("nbAbsences");
 %>

    <jsp:useBean id="etudiant" class="model.Etudiant" scope="request"/>

    <div>
        <h3> Nom : <%=etudiant.getNom()%> </h3>
        <h3> Prenom : <%=etudiant.getPrenom()%> </h3>
        <h3> Absences : <%=nbAbsence%> </h3>
    </div>
</body>
</html>
