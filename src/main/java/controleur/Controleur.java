package controleur;


import model.*;
import model.Module;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Controleur extends HttpServlet {

    // ETUDIANT
    private String urlListeEtudiant;
    private String urlEtudiant;
    private String urlEtudiantModifCreation;
    private String urlEtudiantSupprimer;

    private String urlIndex;

    //GROUPE
    private String urlGroupeModifCreation;
    private String urlGroupeSupprimer;
    private String urlGroupes;

    //ABSENCE
    private String urlAbsences;
    private String urlAbsenceModifCreation;
    private String urlAbsenceSupprimer;

    //NOTE
    private String urlNotes;
    private String urlNoteModifCreation;
    private String urlNoteSupprimer;

    //ENSEIGNANT
    private String urlEnseignants;
    private String urlEnseignantModifCreation;
    private String urlEnseignantSupprimer;

    //MODULE
    private String urlModules;
    private String urlModuleModifCreation;
    private String urlModuleSupprimer;

    // CONSTANTE
    private static final String GESTION_KEY = "gestion";
    private static final String LISTE_ETU_KEY = "listeEtu";
    private static final String ETUDIANT_KEY = "etudiant";
    private static final String LISTE_GRP_KEY = "groupe";
    private static final String LISTE_NOTES_KEY = "note";
    private static final String LISTE_ABS_KEY = "absence";
    private static final String LISTE_MODULES_KEY = "modules";
    private static final String LISTE_ENS_KEY = "enseignants";
    private static final String ABSENCE_KEY = "absence";
    private static final String GROUPE_KEY = "groupe";
    private static final String NOTE_KEY = "note";
    private static final String ENSEIGNANT_KEY = "enseignant";
    private static final String MODULE_KEY = "module";



    // INIT
    public void init() throws ServletException {

        //ETUDIANT
        urlEtudiant = getInitParameter("urlEtudiant");
        urlListeEtudiant = getInitParameter("urlListeEtudiants");
        urlEtudiantModifCreation = getInitParameter("urlEtudiantModifCreation");
        urlEtudiantSupprimer = getInitParameter("urlEtudiantSupprimer");

        urlIndex= getInitParameter("urlAccueil");
        urlGroupes= getInitParameter("urlGroupes");
        urlAbsences= getInitParameter("urlAbsences");
        urlAbsenceModifCreation= getInitParameter("urlAbsenceModifCreation");
        urlAbsenceSupprimer= getInitParameter("urlAbsenceSupprimer");

        //GROUPE
        urlGroupeModifCreation = getInitParameter("urlGroupeModifCreation");
        urlGroupeSupprimer = getInitParameter("urlGroupeSupprimer");

        //NOTE
        urlNotes= getInitParameter("urlNotes");
        urlNoteModifCreation = getInitParameter("urlNoteModifCreation");
        urlNoteSupprimer = getInitParameter("urlNoteSupprimer");

        //ENSEIGNANT
        urlEnseignants= getInitParameter("urlEnseignants");
        urlEnseignantModifCreation = getInitParameter("urlEnseignantModifCreation");
        urlEnseignantSupprimer = getInitParameter("urlEnseignantSupprimer");

        //MODULE
        urlModules= getInitParameter("urlModules");
        urlModuleModifCreation = getInitParameter("urlModuleModifCreation");
        urlModuleSupprimer = getInitParameter("urlModuleSupprimer");



        GestionFactory.open();
    }

    @Override
    public void destroy() {
        super.destroy();

        // Fermeture de la factory
        GestionFactory.close();
    }

    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // on passe la main au GET
        doGet(request, response);
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        // On récupère la méthode d'envoi de la requête
        String methode = request.getMethod().toLowerCase();

        // On récupère l'action à exécuter
        String action = request.getPathInfo();
        if (action == null) {
            action = "/index";
        }

        // Exécution action
        if (methode.equals("get") && action.equals("/index")) {
            doIndex(request, response);

        } else if (methode.equals("post") && action.equals("/detail")) {
            doEtudiant(request, response);

        } else if (methode.equals("post") && action.equals("/absenceModifCreationFin")) {
            try {
                doModifCreationAbsenceFin(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (methode.equals("get") && action.equals("/absenceModifierCreer")) {
            doModifCreationAbsence(request, response);
        } else if (methode.equals("get") && action.equals("/absenceSupprimer")) {
            try {
                doSupprimerAbsence(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if (methode.equals("post") && action.equals("/etudiantModifCreationFin")) {
            try {
                doModifCreationEtudiantFin(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (methode.equals("get") && action.equals("/etudiantModifierCreer")) {
            doModifCreationEtudiant(request, response);
        } else if (methode.equals("get") && action.equals("/etudiantSupprimer")) {
            try {
                doSupprimerEtudiant(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (methode.equals("get") && action.equals("/groupes")) {
            doListeGroupes(request, response);
        }
        else if (methode.equals("post") && action.equals("/groupeModifCreationFin")) {
            try {
                doModifCreationGroupeFin(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (methode.equals("get") && action.equals("/groupeModifierCreer")) {
            doModifCreationGroupe(request, response);
        } else if (methode.equals("get") && action.equals("/groupeSupprimer")) {
            try {
                doSupprimerGroupe(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (methode.equals("get") && action.equals("/etudiants")) {
            doListeEtudiants(request, response);

        } else if (methode.equals("get") && action.equals("/absences")) {
            doListeAbsences(request, response);

        }
        //MODULE
        else if (methode.equals("get") && action.equals("/modules")) {
            doListeModules(request, response);
        } else if (methode.equals("post") && action.equals("/moduleModifCreationFin")) {
            try {
                doModifCreationModuleFin(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (methode.equals("get") && action.equals("/moduleModifierCreer")) {
            doModifCreationModule(request, response);
        } else if (methode.equals("get") && action.equals("/moduleSupprimer")) {
            try {
                doSupprimerModule(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //NOTES
        else if (methode.equals("get") && action.equals("/notes")) {
            doListeNotes(request, response);

        } else if (methode.equals("post") && action.equals("/noteModifCreationFin")) {
            try {
                doModifCreationNoteFin(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (methode.equals("get") && action.equals("/noteModifierCreer")) {
            doModifCreationNote(request, response);
        } else if (methode.equals("get") && action.equals("/noteSupprimer")) {
            try {
                doSupprimerNote(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //ENSEIGNANT
        else if (methode.equals("get") && action.equals("/enseignants")) {
            doListeEnseignants(request, response);

        } else if (methode.equals("post") && action.equals("/enseignantModifCreationFin")) {
            try {
                doModifCreationEnseignantFin(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (methode.equals("get") && action.equals("/enseignantModifierCreer")) {
            doModifCreationEnseignant(request, response);
        } else if (methode.equals("get") && action.equals("/enseignantSupprimer")) {
            try {
                doSupprimerEnseignant(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else {
            // Autres cas
            doIndex(request, response);
        }
    }

    //
    private void doEtudiant(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }
        /*
        Etudiant etudiant = gestion.(Integer.parseInt(request.getParameter("idEtu")));

        // Mettre l'objet jeu en attribut de requête
        request.setAttribute(ETUDIANT_KEY, etudiant);

        int nbAbsences = gestion.getAbsencesByEtudiantId(Integer.parseInt(request.getParameter("idEtu")));
        request.setAttribute("nbAbsences",nbAbsences); */
        loadJSP(urlEtudiant, request, response);
    }

    private void doModifCreationEtudiantFin(HttpServletRequest request,
                                           HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        // Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        String nomEtudiant = request.getParameter("nomEtudiant");
        String prenomEtudiant = request.getParameter("prenomEtudiant");
        int idGroupe = Integer.parseInt(request.getParameter("groupe"));
        Groupe groupe = GroupeDAO.getGroupe(idGroupe);

        String listesIdModules[] = request.getParameterValues("listeIdModule");
        List<Module> listeModules = new ArrayList<>();
        for(int i = 0;i < listesIdModules.length;i++){
            listeModules.add(ModuleDAO.getModule(Integer.parseInt(listesIdModules[i])));
        }
        if(request.getParameter("creation") != null && request.getParameter("creation").compareTo("true") == 0 ){
           EtudiantDAO.create(nomEtudiant,prenomEtudiant,groupe,listeModules);
        }
        else{
            int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));
            Etudiant etudiant = EtudiantDAO.getEtudiant(idEtudiant);
            etudiant.setNom(nomEtudiant);
            etudiant.setPrenom(prenomEtudiant);
            etudiant.setGroupe(groupe);
            etudiant.setModules(listeModules);

            EtudiantDAO.update(etudiant);
        }


        // Mettre l'objet jeu en attribut de requête */
        List<Etudiant> listeEtudiants = EtudiantDAO.getAll();

        request.setAttribute("LISTE_ETU_KEY", listeEtudiants);

        loadJSP(urlListeEtudiant, request, response);
    }

    //Fonction de suppression d'une absence
    //Renvoie vers la liste des absences
    private void doSupprimerEtudiant(HttpServletRequest request,
                                    HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        int idEtudiant = Integer.parseInt(request.getParameter("id"));
        EtudiantDAO.supprimerEtudiant(idEtudiant);


        // Mettre l'objet jeu en attribut de requête */
        List<Etudiant> listeEtudiants = EtudiantDAO.getAll();

        request.setAttribute("LISTE_ETU_KEY", listeEtudiants);

        loadJSP(urlListeEtudiant, request, response);
    }

    private void doModifCreationEtudiant(HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }


        List<Groupe> listeGroupes = GroupeDAO.getAll();
        request.setAttribute("LISTE_GRP_KEY", listeGroupes);

        List<Module> listeModules = ModuleDAO.getAll();
        request.setAttribute("LISTE_MODULES_KEY", listeModules);

//		// Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        if(request.getParameter("creation") == null){
            int idEtudiant = Integer.parseInt(request.getParameter("id"));
            Etudiant etudiant = EtudiantDAO.getEtudiant(idEtudiant);

            request.setAttribute("ETU_KEY", etudiant);
        }


        loadJSP(urlEtudiantModifCreation, request, response);
    }
    private void doModifCreationAbsenceFin(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

		// Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDebut = sdf.parse(request.getParameter("dateDebut"));
        Date dateFin = sdf.parse(request.getParameter("dateFin"));
        int idEtudiant = Integer.parseInt(request.getParameter("etudiant"));

        Etudiant etu = EtudiantDAO.getEtudiant(idEtudiant);
        if(request.getParameter("creation") != null && request.getParameter("creation").compareTo("true") == 0 ){
            Absence absence = AbsenceDAO.create(new java.sql.Date(dateDebut.getTime()),new java.sql.Date(dateFin.getTime()),etu);
        }
        else{
            int idAbsence = Integer.parseInt(request.getParameter("idAbsence"));
            Absence absence = AbsenceDAO.getAbsence(idAbsence);
            absence.setDebut(new java.sql.Date(dateDebut.getTime()));
            absence.setFin(new java.sql.Date(dateFin.getTime()));
            absence.setEtudiant(etu);
            AbsenceDAO.update(absence);
        }


        // Mettre l'objet jeu en attribut de requête */
        List<Absence> listeAbsences = AbsenceDAO.getAll();

        request.setAttribute("LISTE_ABS_KEY", listeAbsences);

        loadJSP(urlAbsences, request, response);
    }

    //Fonction de suppression d'une absence
    //Renvoie vers la liste des absences
    private void doSupprimerAbsence(HttpServletRequest request,
                                           HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        int idAbsence = Integer.parseInt(request.getParameter("id"));
        AbsenceDAO.supprimerAbsence(idAbsence);


        // Mettre l'objet jeu en attribut de requête */
        List<Absence> listeAbsences = AbsenceDAO.getAll();

        request.setAttribute("LISTE_ABS_KEY", listeAbsences);

        loadJSP(urlAbsenceSupprimer, request, response);
    }

    private void doModifCreationAbsence(HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

//		// Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        if(request.getParameter("creation") == null){
            int idAbsence = Integer.parseInt(request.getParameter("id"));
            Absence absence = AbsenceDAO.getAbsence(idAbsence);
            List<Etudiant> listeEtudiants = EtudiantDAO.getAll();

            request.setAttribute("ABSENCE_KEY", absence);
            request.setAttribute("LISTE_ETU_KEY", listeEtudiants);
        }
        else{

            List<Etudiant> listeEtudiants = EtudiantDAO.getAll();
            request.setAttribute("LISTE_ETU_KEY", listeEtudiants);
        }


        loadJSP(urlAbsenceModifCreation, request, response);
    }

    //
    private void doIndex(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {


        loadJSP(urlIndex, request, response);
    }


    private void doListeEtudiants(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        // Mettre l'objet jeu en attribut de requête */
        List<Etudiant> listeEtudiants = EtudiantDAO.getAll();

        request.setAttribute("LISTE_ETU_KEY", listeEtudiants);

        loadJSP(urlListeEtudiant, request, response);
    }

    //MODULE
    private void doListeModules(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        // Mettre l'objet jeu en attribut de requête */
        List<Module> listeModules = ModuleDAO.getAll();

        request.setAttribute("LISTE_MODULES_KEY", listeModules);

        loadJSP(urlModules, request, response);
    }


    private void doModifCreationModuleFin(HttpServletRequest request,
                                              HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        // Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        String nomModule = request.getParameter("nomModule");
        Integer coefModule = Integer.parseInt(request.getParameter("coefModule"));
        Integer idEnseignant = Integer.parseInt(request.getParameter("enseignant"));
        Enseignant enseignantModule = EnseignantDAO.getEnseignant(idEnseignant);

        String[] listeIdEtudiant = request.getParameterValues("listeIdEtudiant");
        List<Etudiant> listeEtudiants = new ArrayList<Etudiant>();
        for(int y=0;y < listeIdEtudiant.length;y++){
            Etudiant etu = EtudiantDAO.getEtudiant(Integer.parseInt(listeIdEtudiant[y]));
            listeEtudiants.add(etu);
        }
        if(request.getParameter("creation") != null && request.getParameter("creation").compareTo("true") == 0 ){
            Module module = ModuleDAO.create(nomModule,coefModule,enseignantModule,listeEtudiants);
        }
        else{
            int idModule = Integer.parseInt(request.getParameter("idModule"));
            Module module = ModuleDAO.getModule(idModule);
            module.setNom(nomModule);
            module.setCoef(coefModule);
            module.setEnseignant(enseignantModule);
            module.setEtudiants(listeEtudiants);

            ModuleDAO.update(module);
        }


        // Mettre l'objet jeu en attribut de requête */
        List<Module> listeModules = ModuleDAO.getAll();

        request.setAttribute("LISTE_MODULES_KEY", listeModules);

        loadJSP(urlModules, request, response);
    }

    //Fonction de suppression d'une absence
    //Renvoie vers la liste des absences
    private void doSupprimerModule(HttpServletRequest request,
                                       HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        int idModule = Integer.parseInt(request.getParameter("id"));
        ModuleDAO.supprimerModule(idModule);


        // Mettre l'objet jeu en attribut de requête */
        List<Module> listeModules = ModuleDAO.getAll();

        request.setAttribute("LISTE_MODULES_KEY", listeModules);

        loadJSP(urlModuleSupprimer, request, response);
    }

    private void doModifCreationModule(HttpServletRequest request,
                                           HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        List<Enseignant> listeEnseignants = EnseignantDAO.getAll();
        request.setAttribute("LISTE_ENSEIGNANT_KEY", listeEnseignants);

        List<Etudiant> listeEtudiants = EtudiantDAO.getAll();
        request.setAttribute("LISTE_ETU_KEY", listeEtudiants);

//		// Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        if(request.getParameter("creation") == null){
            int idModule = Integer.parseInt(request.getParameter("id"));
            Module module = ModuleDAO.getModule(idModule);

            request.setAttribute("MODULE_KEY", module);

        }
        else{


        }

        loadJSP(urlModuleModifCreation, request, response);
    }
    //ENSEIGNANTS
    private void doListeEnseignants(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {

        // Mettre l'objet jeu en attribut de requête */
        List<Enseignant> listeEnseignants = EnseignantDAO.getAll();

        request.setAttribute("LISTE_ENS_KEY", listeEnseignants);

        loadJSP(urlEnseignants, request, response);
    }


    private void doModifCreationEnseignantFin(HttpServletRequest request,
                                          HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        // Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        String nomEnseignant = request.getParameter("nomEnseignant");
        String prenomEnseignant = request.getParameter("prenomEnseignant");
        String mdpEnseignant = request.getParameter("mdpEnseignant");

        if(request.getParameter("creation") != null && request.getParameter("creation").compareTo("true") == 0 ){
            Enseignant enseignant = EnseignantDAO.create(nomEnseignant,prenomEnseignant,mdpEnseignant);
        }
        else{
            int idEnseignant = Integer.parseInt(request.getParameter("idEnseignant"));
            Enseignant enseignant = EnseignantDAO.getEnseignant(idEnseignant);
            enseignant.setNom(nomEnseignant);
            enseignant.setPrenom(prenomEnseignant);
            enseignant.setMot_de_passe(mdpEnseignant);

            EnseignantDAO.update(enseignant);
        }


        // Mettre l'objet jeu en attribut de requête */
        List<Enseignant> listeEnseignants = EnseignantDAO.getAll();

        request.setAttribute("LISTE_ENS_KEY", listeEnseignants);

        loadJSP(urlEnseignants, request, response);
    }

    //Fonction de suppression d'une absence
    //Renvoie vers la liste des absences
    private void doSupprimerEnseignant(HttpServletRequest request,
                                   HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        int idEnseignant = Integer.parseInt(request.getParameter("id"));
        EnseignantDAO.supprimerEnseignant(idEnseignant);


        // Mettre l'objet jeu en attribut de requête */
        List<Enseignant> listeEnseignants = EnseignantDAO.getAll();

        request.setAttribute("LISTE_ENS_KEY", listeEnseignants);

        loadJSP(urlEnseignantSupprimer, request, response);
    }

    private void doModifCreationEnseignant(HttpServletRequest request,
                                       HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

//		// Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        if(request.getParameter("creation") == null){
            int idEnseignant = Integer.parseInt(request.getParameter("id"));
            Enseignant enseignant = EnseignantDAO.getEnseignant(idEnseignant);

            request.setAttribute("ENSEIGNANT_KEY", enseignant);

        }
        else{

            List<Module> listeModules = ModuleDAO.getAll();
            request.setAttribute("LISTE_MODULES_KEY", listeModules);
        }

        loadJSP(urlEnseignantModifCreation, request, response);
    }


    private void doListeAbsences(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        // Mettre l'objet jeu en attribut de requête */
        List<Absence> listeAbsences = AbsenceDAO.getAll();

        request.setAttribute("LISTE_ABS_KEY", listeAbsences);

        loadJSP(urlAbsences, request, response);
    }

    //GROUPE
    private void doListeGroupes(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        // Mettre l'objet jeu en attribut de requête */
        List<Groupe> listeGroupes = GroupeDAO.getAll();

        request.setAttribute("LISTE_GRP_KEY", listeGroupes);

        loadJSP(urlGroupes, request, response);
    }

    private void doModifCreationGroupeFin(HttpServletRequest request,
                                           HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        // Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        String nomGroupe = request.getParameter("nomGroupe");

        if(request.getParameter("creation") != null && request.getParameter("creation").compareTo("true") == 0 ){
            Groupe groupe = GroupeDAO.create(nomGroupe);
        }
        else{
            int idGroupe = Integer.parseInt(request.getParameter("idGroupe"));
            Groupe groupe = GroupeDAO.getGroupe(idGroupe);
            groupe.setNom(nomGroupe);

            GroupeDAO.update(groupe);
        }


        // Mettre l'objet jeu en attribut de requête */
        List<Groupe> listeGroupes = GroupeDAO.getAll();

        request.setAttribute("LISTE_GRP_KEY", listeGroupes);

        loadJSP(urlGroupes, request, response);
    }

    //Fonction de suppression d'une absence
    //Renvoie vers la liste des absences
    private void doSupprimerGroupe(HttpServletRequest request,
                                    HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        int idGroupe = Integer.parseInt(request.getParameter("id"));
        GroupeDAO.supprimerGroupe(idGroupe);


        // Mettre l'objet jeu en attribut de requête */
        List<Groupe> listeGroupes = GroupeDAO.getAll();

        request.setAttribute("LISTE_GRP_KEY", listeGroupes);

        loadJSP(urlGroupeSupprimer, request, response);
    }

    private void doModifCreationGroupe(HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

//		// Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        if(request.getParameter("creation") == null){
            int idGroupe = Integer.parseInt(request.getParameter("id"));
            Groupe groupe = GroupeDAO.getGroupe(idGroupe);

            request.setAttribute("GROUPE_KEY", groupe);
        }
        else{

            List<Etudiant> listeEtudiants = EtudiantDAO.getAll();
            request.setAttribute("LISTE_ETU_KEY", listeEtudiants);
        }

        loadJSP(urlGroupeModifCreation, request, response);
    }

    //PARTIE SUR LES NOTES
    private void doListeNotes(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        // Mettre l'objet jeu en attribut de requête */
        List<Note> listeNotes = NoteDAO.getAll();

        request.setAttribute("LISTE_NOTES_KEY", listeNotes);

        loadJSP(urlNotes, request, response);
    }

    private void doModifCreationNoteFin(HttpServletRequest request,
                                           HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        // Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        float notation = Float.parseFloat(request.getParameter("notation"));
        int idEtudiant = Integer.parseInt(request.getParameter("etudiant"));

        Etudiant etu = EtudiantDAO.getEtudiant(idEtudiant);
        if(request.getParameter("creation") != null && request.getParameter("creation").compareTo("true") == 0 ){
            Note note = NoteDAO.create(notation,etu);
        }
        else{
            int idNote = Integer.parseInt(request.getParameter("idNote"));
            Note note = NoteDAO.getNote(idNote);
            note.setNotation(notation);
            note.setEtudiant(etu);
            NoteDAO.update(note);
        }


        // Mettre l'objet jeu en attribut de requête */
        List<Note> listeNotes = NoteDAO.getAll();

        request.setAttribute("LISTE_NOTES_KEY", listeNotes);

        loadJSP(urlNotes, request, response);
    }

    //Fonction de suppression d'une absence
    //Renvoie vers la liste des absences
    private void doSupprimerNote(HttpServletRequest request,
                                    HttpServletResponse response) throws ServletException, IOException, ParseException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

        int idNote = Integer.parseInt(request.getParameter("id"));
        NoteDAO.supprimerNote(idNote);


        // Mettre l'objet jeu en attribut de requête */
        List<Note> listeNotes = NoteDAO.getAll();

        request.setAttribute("LISTE_NOTES_KEY", listeNotes);

        loadJSP(urlNoteSupprimer, request, response);
    }

    private void doModifCreationNote(HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'attribut resultat de la session
        GestionFactory gestion = (GestionFactory) request.getSession().getAttribute(GESTION_KEY);
        if (gestion == null) {
            gestion = new GestionFactory();
            request.getSession().setAttribute(GESTION_KEY, gestion);
        }

//		// Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        if(request.getParameter("creation") == null){
            int idNote = Integer.parseInt(request.getParameter("id"));
            Note note = NoteDAO.getNote(idNote);
            List<Etudiant> listeEtudiants = EtudiantDAO.getAll();

            request.setAttribute("NOTE_KEY", note);
            request.setAttribute("LISTE_ETU_KEY", listeEtudiants);
        }
        else{

            List<Etudiant> listeEtudiants = EtudiantDAO.getAll();
            request.setAttribute("LISTE_ETU_KEY", listeEtudiants);
        }


        loadJSP(urlNoteModifCreation, request, response);
    }

    /**
     * Charge la JSP indiquée en paramètre
     *
     * @param url
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loadJSP(String url, HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

//		L'interface RequestDispatcher permet de transférer le contrôle à une autre servlet
//		Deux méthodes possibles :
//		- forward() : donne le contrôle à une autre servlet. Annule le flux de sortie de la servlet courante
//		- include() : inclus dynamiquement une autre servlet
//			+ le contrôle est donné à une autre servlet puis revient à la servlet courante (sorte d'appel de fonction).
//			+ Le flux de sortie n'est pas supprimé et les deux se cumulent

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }


}
