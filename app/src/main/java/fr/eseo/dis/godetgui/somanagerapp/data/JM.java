package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
@Entity(tableName = "jm")
public class JM {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int idJm ;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    @NonNull
    private String login;



    public JM(@NonNull int idJm, @NonNull String nom, @NonNull String prenom, @NonNull String login) {
        this.idJm = idJm;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
    }


    @NonNull
    public int getIdJm() {
        return idJm;
    }

    public void setIdJm(@NonNull int idJm) {
        this.idJm = idJm;
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
    public String getLogin() {
        return login;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }


}
