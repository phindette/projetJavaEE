<%@ page contentType="text/html;charset=UTF-8" %>


    <h2 class="display-1">Site de gestion d'étudiants</h2>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="<%= application.getContextPath()%>/do/index">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="<%= application.getContextPath()%>/do/etudiants">Etudiants</a>
            </li>
            <li class="nav-item active">
            <a class="nav-link" href="<%= application.getContextPath()%>/do/groupes">Groupes</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="<%= application.getContextPath()%>/do/notes">Notes</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="<%= application.getContextPath()%>/do/absences">Absences</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="<%= application.getContextPath()%>/do/modules">Modules</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="<%= application.getContextPath()%>/do/enseignants">Enseignants</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Features</a>
            </li>
        </ul>
    </div>
</nav>