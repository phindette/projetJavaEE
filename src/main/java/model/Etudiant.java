package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue
    private Integer idEtudiant;

    @Column(nullable=false)
    private String prenom;

    @Column(nullable=false)
    private String nom;

    @Column(nullable=false)
    private int nbAbsence;

    @ManyToOne
    @JoinColumn(name="idGroupe")
    private Groupe groupe;

    @OneToMany(targetEntity = Note.class,mappedBy = "etudiant")
    private List<Note> notes;

    @OneToMany(targetEntity = Absence.class,mappedBy = "etudiant")
    private List<Absence> absence;

    @ManyToMany(targetEntity = Module.class)
    @JoinTable(name = "etudiant_module",
            joinColumns = @JoinColumn(name = "idEtudiant"),
            inverseJoinColumns = @JoinColumn(name = "idModule")
    )
    private List<Module> modules = new ArrayList<>();

    public Etudiant() {
        super();
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer id) {
        this.idEtudiant = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbAbsence() {
        return nbAbsence;
    }

    public void setNbAbsence(int nbAbsence) {
        this.nbAbsence = nbAbsence;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Absence> getAbsence() {
        return absence;
    }

    public void setAbsence(List<Absence> absence) {
        this.absence = absence;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
