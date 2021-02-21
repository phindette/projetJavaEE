package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Module implements Serializable {
    @Id
    @GeneratedValue
    private Integer idModule;

    @ManyToMany(targetEntity = Etudiant.class)
    @JoinTable(name = "etudiant_module",
            joinColumns = @JoinColumn(name = "idModule"),
            inverseJoinColumns = @JoinColumn(name = "idEtudiant")
    )
    private List<Etudiant> etudiants = new ArrayList<>();

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private Integer coef;

    @ManyToOne
    @JoinColumn(name="idEnseignant")
    private Enseignant enseignant;

    public Module() {super();}

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer id) {
        this.idModule = id;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getCoef() {
        return coef;
    }

    public void setCoef(Integer coef) {
        this.coef = coef;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
}


