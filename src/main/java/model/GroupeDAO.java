package model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GroupeDAO {
    public static Groupe create(String nom){
        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        Groupe groupe = new Groupe();
        groupe.setNom(nom);
        em.persist(groupe);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return groupe;
    }

    // Retourne l'ensemble des auteurs
    public static List<Groupe> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        List<Groupe> list = em.createQuery("SELECT e FROM Groupe e").getResultList();

        return list;
    }
    // Retourne l'ensemble des auteurs
    public static Groupe getGroupe(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        Query query = em.createQuery(
                "SELECT e FROM Groupe e WHERE e.idGroupe = :idGroupe")
                .setParameter("idGroupe", id);

        Groupe groupe = (Groupe) query.getSingleResult();

        return groupe;
    }

    // Retourne l'ensemble des auteurs
    public static void supprimerGroupe(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        Query query = em.createQuery(
                "SELECT e FROM Groupe e WHERE e.idGroupe = :idGroupe")
                .setParameter("idGroupe", id);

        Groupe groupe = (Groupe) query.getSingleResult();
        List<Etudiant> listeEtudiants = groupe.getEtudiants();

        for(int i=0;i < listeEtudiants.size();i++){
            listeEtudiants.get(i).setGroupe(null);
            EtudiantDAO.update(listeEtudiants.get(i));
        }

        em.getTransaction().begin();
        em.remove(groupe);
        em.getTransaction().commit();

        em.close();

    }


    public static Groupe update(Groupe groupe) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (auteur) à l’EntityManager courant pour réaliser la modification
        em.merge(groupe);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return groupe;
    }
}


