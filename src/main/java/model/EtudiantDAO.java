package model;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EtudiantDAO {

    public static void create(String prenom, String nom,Groupe groupe,List<Module> listeModules){
        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setGroupe(groupe);
        etudiant.setModules(listeModules);
        System.out.println("Etudiant : ");
        System.out.println(etudiant.toString());


        em.persist(etudiant);
        em.flush();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

    }

    public static Etudiant update(Etudiant etudiant) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (etudiant) à l’EntityManager courant  pour réaliser la modification
        em.merge(etudiant);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return etudiant;
    }

    // Retourne l'ensemble des auteurs
    public static Etudiant getEtudiant(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        Query query = em.createQuery(
                "SELECT e FROM Etudiant e WHERE e.idEtudiant = :idEtudiant")
                .setParameter("idEtudiant", id);

        Etudiant etudiant = (Etudiant) query.getSingleResult();

        return etudiant;
    }
    // Retourne l'ensemble des auteurs
    public static void supprimerEtudiant(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        Query query = em.createQuery(
                "SELECT e FROM Etudiant e WHERE e.idEtudiant = :idEtudiant")
                .setParameter("idEtudiant", id);

        Etudiant etudiant = (Etudiant) query.getSingleResult();

        List<Module> listeModules = etudiant.getModules();

        for(int i=0;i < listeModules.size();i++){
            for(int y=0;y < listeModules.get(i).getEtudiants().size();y++){
                if(listeModules.get(i).getEtudiants().get(y).getIdEtudiant().compareTo(etudiant.getIdEtudiant()) == 0){
                    List<Etudiant> nouvelleListeEtudiantModule = listeModules.get(i).getEtudiants();
                    nouvelleListeEtudiantModule.remove(etudiant);
                    ModuleDAO.update(listeModules.get(i));
                }
            }
        }
        em.getTransaction().begin();
        em.remove(etudiant);
        em.getTransaction().commit();

        em.close();

    }

    // Retourne l'ensemble des auteurs
    public static List<Etudiant> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT e FROM Etudiant e");

        @SuppressWarnings("unchecked")
        List<Etudiant> list = q.getResultList();

        return list;
    }
}

