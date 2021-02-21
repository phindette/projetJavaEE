package model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class NoteDAO {
    public static Note create(float notation, Etudiant etu){
        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        Note note = new Note();
        note.setNotation(notation);
        note.setEtudiant(etu);
        em.persist(note);


        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return note;
    }

    // Retourne l'ensemble des auteurs
    public static List<Note> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        List<Note> list = em.createQuery("SELECT e FROM Note e").getResultList();

        return list;
    }
    // Retourne l'ensemble des auteurs
    public static Note getNote(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        Query query = em.createQuery(
                "SELECT e FROM Note e WHERE e.idNote = :idNote")
                .setParameter("idNote", id);

        Note note = (Note) query.getSingleResult();

        return note;
    }

    // Retourne l'ensemble des auteurs
    public static void supprimerNote(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        Query query = em.createQuery(
                "SELECT e FROM Note e WHERE e.idNote = :idNote")
                .setParameter("idNote", id);

        Note note = (Note) query.getSingleResult();

        em.getTransaction().begin();
        em.remove(note);
        em.getTransaction().commit();

        em.close();

    }

    public static Note update(Note note) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (auteur) à l’EntityManager courant pour réaliser la modification
        em.merge(note);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return note;
    }
}
