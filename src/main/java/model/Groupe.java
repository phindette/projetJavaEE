package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Groupe implements Serializable {

    @Id
    @GeneratedValue
    private Integer idGroupe;

    @Column(unique=true, nullable = false)
    private String nom;

    @OneToMany(targetEntity = Etudiant.class, mappedBy = "groupe")
    private List<Etudiant> etudiants;

    public Groupe() {super();}

    public Integer getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Integer id) {
        this.idGroupe = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
}
