package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Enseignant implements Serializable{
    @Id
    @GeneratedValue
    private Integer idEnseignant;

    @Column(unique=true, nullable = false)
    private String nom;

    @Column(unique=true, nullable = false)
    private String prenom;

    @Column(unique=true, nullable = false)
    private String mot_de_passe;

    @OneToMany(targetEntity = Module.class, mappedBy = "enseignant")
    private List<Module> modules;

    public Enseignant(){super();}

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
}
