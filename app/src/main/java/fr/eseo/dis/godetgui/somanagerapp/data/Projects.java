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
    private boolean isConfidential;

    @NonNull
    private Image poster;

    @NonNull
    private boolean isDone;

    public Projects(@NonNull int idProject, @NonNull String nom, @NonNull boolean isConfidential, @NonNull Image poster,@NonNull boolean isDone) {
        this.idProject = idProject;
        this.nom = nom;
        this.isConfidential = isConfidential;
        this.poster = poster;
        this.isDone = isDone;
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
    public boolean isConfidential() {
        return isConfidential;
    }

    public void setConfidential(@NonNull boolean confidential) {
        isConfidential = confidential;
    }

    @NonNull
    public Image getPoster() {
        return poster;
    }

    public void setPoster(@NonNull Image poster) {
        this.poster = poster;
    }
}
