package model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

public class AbsenceDAO {
    public static Absence create(Date debut, Date fin,Etudiant etu){
        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        Absence absence = new Absence();
        absence.setDebut(debut);
        absence.setFin(fin);
        absence.setEtudiant(etu);
        em.persist(absence);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return absence;
    }

    // Retourne l'ensemble des auteurs
    public static List<Absence> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        List<Absence> list = em.createQuery("SELECT e FROM Absence e").getResultList();

        return list;
    }

    // Retourne l'ensemble des auteurs
    public static Absence getAbsence(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        Query query = em.createQuery(
                "SELECT e FROM Absence e WHERE e.idAbsence = :idAbsence")
                .setParameter("idAbsence", id);

        Absence absence = (Absence) query.getSingleResult();

        return absence;
    }

    // Retourne l'ensemble des auteurs
    public static void supprimerAbsence(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        Query query = em.createQuery(
                "SELECT e FROM Absence e WHERE e.idAbsence = :idAbsence")
                .setParameter("idAbsence", id);

        Absence absence = (Absence) query.getSingleResult();

        em.getTransaction().begin();
        em.remove(absence);
        em.getTransaction().commit();

        em.close();

    }

    public static Absence update(Absence absence) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (auteur) à l’EntityManager courant pour réaliser la modification
        em.merge(absence);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return absence;
    }
}
