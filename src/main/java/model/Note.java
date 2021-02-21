package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Note implements Serializable {

    @Id
    @GeneratedValue
    private Integer idNote;

    @Column(nullable=false)
    private float notation;

    @ManyToOne
    @JoinColumn(name="idEtudiant")
    private Etudiant etudiant;

    public Note() {super();}

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer id) {
        this.idNote = id;
    }

    public float getNotation() {
        return notation;
    }

    public void setNotation(float notation) {
        this.notation = notation;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
