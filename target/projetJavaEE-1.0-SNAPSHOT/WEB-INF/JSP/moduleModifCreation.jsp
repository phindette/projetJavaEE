
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Etudiant" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Module" %>
<%@ page import="model.Enseignant" %>
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
    List<Enseignant> listeEnseignants = (List<Enseignant>) request.getAttribute("LISTE_ENSEIGNANT_KEY");
    List<Etudiant> listeEtudiants = (List<Etudiant>) request.getAttribute("LISTE_ETU_KEY");
    if(request.getAttribute("MODULE_KEY") != null) {
        Module module = (Module) request.getAttribute("MODULE_KEY");
%>


<p>Modification d'un module :
<form action="<%=application.getContextPath()%>/do/moduleModifCreationFin" method="post">
    <input type="hidden" name="idModule" value="<%=module.getIdModule()%>">
    <input type="text" name="nomModule" value="<%=module.getNom()%>">
    <input type="number"name="coefModule" value="<%=module.getCoef()%>">
    <select id="enseignant" name="enseignant">
        <%
            for (int i = 0; i < listeEnseignants.size();i++) {
                if(module.getEnseignant().getIdEnseignant() == listeEnseignants.get(i).getIdEnseignant()){


        %>

        <option value="<%=listeEnseignants.get(i).getIdEnseignant()%>" selected><%=listeEnseignants.get(i).getPrenom()%> <%=listeEnseignants.get(i).getNom()%> </option>

        <%
        }else{

        %>
        <option value="<%=listeEnseignants.get(i).getIdEnseignant()%>"><%=listeEnseignants.get(i).getPrenom()%> <%=listeEnseignants.get(i).getNom()%> </option>

        <%
                }}
        %>
    </select>
    <%
        for (int y = 0; y < listeEtudiants.size();y++) {
            if(module.getEtudiants().contains(listeEtudiants.get(y))){
    %>
    <input type="checkbox" name="listeIdEtudiant" checked value="<%= listeEtudiants.get(y).getIdEtudiant()%>"> <%=listeEtudiants.get(y).getNom() %> </input>
    <%}else{ %>
        <input type="checkbox" name="listeIdEtudiant" value="<%= listeEtudiants.get(y).getIdEtudiant()%>"> <%=listeEtudiants.get(y).getNom() %> </input>
    <%}}%>

    <input type="submit" class="button btn-succes" value="Fin" />
</form>
</p>
<% } else{ %>
<p>Creation d'un module :
<form action="<%=application.getContextPath()%>/do/moduleModifCreationFin" method="post">
    <input type="hidden" name="creation" value="true">
    <input type="text" name="nomModule">
    <input type="number"name="coefModule">
    <select id="enseignant" name="enseignant">
        <%
            for (int i = 0; i < listeEnseignants.size();i++) {
        %>

        <option value="<%=listeEnseignants.get(i).getIdEnseignant()%>"><%=listeEnseignants.get(i).getPrenom()%> <%=listeEnseignants.get(i).getNom()%> </option>
        <%
                }
        %>
    </select>
        <%
        for (int y = 0; y < listeEtudiants.size();y++) {
    %>
    <input type="checkbox" name="listeIdEtudiant" value="<%= listeEtudiants.get(y).getIdEtudiant()%>"> <%=listeEtudiants.get(y).getNom() %> </input>
        <%}%>
<%
    }
%>
    <input type="submit" class="button btn-succes" value="Fin" />
</form>


<jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
