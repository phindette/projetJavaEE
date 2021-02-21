package model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ModuleDAO {

    public static Module create(String nom, int coef,Enseignant enseignant,List<Etudiant> listeEtudiants){
        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        Module module = new Module();
        module.setNom(nom);
        module.setCoef(coef);
        module.setEnseignant(enseignant);
        module.setEtudiants(listeEtudiants);

        em.persist(module);
        em.flush();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return module;
    }


    // Retourne l'ensemble des auteurs
    public static List<Module> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT e FROM Module e");

        @SuppressWarnings("unchecked")
        List<Module> list = q.getResultList();

        return list;
    }

    // Retourne l'ensemble des auteurs
    public static Module getModule(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        @SuppressWarnings("unchecked")
        Query query = em.createQuery(
                "SELECT e FROM Module e WHERE e.idModule = :idModule")
                .setParameter("idModule", id);

        Module module = (Module) query.getSingleResult();

        return module;
    }

    // Retourne l'ensemble des auteurs
    public static void supprimerModule(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        Query query = em.createQuery(
                "SELECT e FROM Module e WHERE e.idModule = :idModule")
                .setParameter("idModule", id);

        Module module = (Module) query.getSingleResult();

        List<Etudiant> listeEtudiants = module.getEtudiants();

        for(int i=0;i < listeEtudiants.size();i++){
            for(int y=0;y < listeEtudiants.get(i).getModules().size();y++){
                if(listeEtudiants.get(i).getModules().get(y).getIdModule().compareTo(module.getIdModule()) == 0){
                    List<Module> nouvelleListeModuleEtudiant = listeEtudiants.get(i).getModules();
                    nouvelleListeModuleEtudiant.remove(module);
                    EtudiantDAO.update(listeEtudiants.get(i));
                }
            }
        }
        em.getTransaction().begin();
        em.remove(module);
        em.getTransaction().commit();

        em.close();

    }
    public static Module update(Module module) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (auteur) à l’EntityManager courant pour réaliser la modification
        em.merge(module);


        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return module;
    }
}
