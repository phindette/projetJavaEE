package model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EnseignantDAO {
    public static Enseignant create(String nom,String prenom, String mdp){
        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        Enseignant enseignant = new Enseignant();
        enseignant.setNom(nom);
        enseignant.setPrenom(prenom);
        enseignant.setMot_de_passe(mdp);

        em.persist(enseignant);
        em.flush();
        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return enseignant;
    }

    // Retourne l'ensemble des auteurs
    public static List<Enseignant> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        List<Enseignant> list = em.createQuery("SELECT e FROM Enseignant e").getResultList();

        return list;
    }

    // Retourne l'ensemble des auteurs
    public static Enseignant getEnseignant(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        Query query = em.createQuery(
                "SELECT e FROM Enseignant e WHERE e.idEnseignant = :idEnseignant")
                .setParameter("idEnseignant", id);

        Enseignant enseignant = (Enseignant) query.getSingleResult();

        return enseignant;
    }

    public static void supprimerEnseignant(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        Query query = em.createQuery(
                "SELECT e FROM Enseignant e WHERE e.idEnseignant = :idEnseignant")
                .setParameter("idEnseignant", id);

        Enseignant enseignant = (Enseignant) query.getSingleResult();
        List<Module> listeModules = enseignant.getModules();

        for(int i=0;i < listeModules.size();i++){
            listeModules.get(i).setEnseignant(null);
            ModuleDAO.update(listeModules.get(i));
        }

        em.getTransaction().begin();
        em.remove(enseignant);
        em.getTransaction().commit();

        em.close();

    }


    public static Enseignant update(Enseignant enseignant) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (auteur) à l’EntityManager courant pour réaliser la modification
        em.merge(enseignant);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return enseignant;
    }
}



