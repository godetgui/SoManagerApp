package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "students", foreignKeys = {@ForeignKey(entity= Projects.class, parentColumns = "idProject", childColumns="idProject")})


public class Students {
    @PrimaryKey
    @NonNull
    private int idStudent;

    @NonNull
    private String nom;

    @NonNull
    private String prenom;

    @NonNull
    private String email;

    @NonNull

    private String promo;

    @NonNull
    private int idProject;

    public Students(@NonNull int idStudent, @NonNull String nom, @NonNull String prenom, @NonNull String email, @NonNull String promo, @NonNull int idProject) {
        this.idStudent = idStudent;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.promo = promo;
        this.idProject = idProject;
    }

    @NonNull
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(@NonNull int idStudent) {
        this.idStudent = idStudent;
    }

    @NonNull
    public String getNom() {
        return nom;
    }

    public void setNom(@NonNull String nom) {
        this.nom = nom;
    }

    @NonNull
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(@NonNull String prenom) {
        this.prenom = prenom;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPromo() {
        return promo;
    }

    public void setPromo(@NonNull String promo) {
        this.promo = promo;
    }

    @NonNull
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(@NonNull int idProject) {
        this.idProject = idProject;
    }
}
