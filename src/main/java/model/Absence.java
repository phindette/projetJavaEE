package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Absence implements Serializable{
    @Id
    @GeneratedValue
    private Integer idAbsence;

    @Column(nullable=false)
    private Date debut;

    @Column(nullable=false)
    private Date fin;

    @ManyToOne
    @JoinColumn(name="idEtudiant")
    private Etudiant etudiant;

    public Absence(){super();}

    public Integer getIdAbsence() {
        return idAbsence;
    }

    public void setIdAbsence(Integer idAbsence) {
        this.idAbsence = idAbsence;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
