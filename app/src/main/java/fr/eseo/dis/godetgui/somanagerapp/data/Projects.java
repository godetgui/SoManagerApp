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



    public Projects(){};



    public Projects(@NonNull int idProject, @NonNull String nom, @NonNull int confidentialite, @NonNull boolean poster) {
        this.idProject = idProject;
        this.nom = nom;
        this.confidentialite = confidentialite;
        this.poster = poster;

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

    @Override
    public String toString(){
        return "Project id: "+this.getIdProject()+", Title: "+this.getNom()+", Conf: "+this.confidentialite+", Poster: "+this.isPoster();
    }


}
