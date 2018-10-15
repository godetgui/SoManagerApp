package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;
import android.support.annotation.NonNull;

@Entity(tableName = "projects")
public class Projects {

    @PrimaryKey
    @NonNull
    private int idProject;

    @NonNull
    private String nom;

    @NonNull
    private int confidentialite;

    @NonNull
    private boolean poster;

    @NonNull
    private String forename;

    @NonNull
    private String surname;


    public Projects(){};

    public Projects(@NonNull int idProject, @NonNull String nom, @NonNull int confidentialite, @NonNull boolean poster,  @NonNull String forename, @NonNull String surname) {
        this.idProject = idProject;
        this.nom = nom;
        this.confidentialite = confidentialite;
        this.poster = poster;
        this.forename = forename;
        this.surname = surname;
    }

    @NonNull
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(@NonNull int idProject) {
        this.idProject = idProject;
    }

    @NonNull
    public String getNom() {
        return nom;
    }

    public void setNom(@NonNull String nom) {
        this.nom = nom;
    }

    @NonNull
    public int getConfidentialite() {
        return confidentialite;
    }

    public void setConfidentialite(@NonNull int confidentialite) {
        confidentialite = confidentialite;
    }

    @NonNull
    public boolean isPoster() {
        return poster;
    }

    public void setPoster(@NonNull boolean poster) {
        this.poster = poster;
    }

    @NonNull
    public String getForename() {
        return forename;
    }

    public void setForename(@NonNull String forename) {
        this.forename = forename;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }


    @Override
    public String toString(){
        String Newligne=System.getProperty("line.separator");
        return "Project id: "+this.getIdProject()+Newligne+"Title: "+this.getNom()+Newligne+"Conf: "
                +this.confidentialite+Newligne+"Poster: "+this.isPoster()+Newligne
                +"Supervisor: "+this.getForename()+" "+this.getSurname()+Newligne;
    }


}
