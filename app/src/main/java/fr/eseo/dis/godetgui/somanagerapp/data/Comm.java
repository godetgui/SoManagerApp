package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

public class Comm {

    @PrimaryKey
    @NonNull
    private int id_comm;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    @NonNull
    private String email;
    @NonNull
    private String login;
    @NonNull
    private String hash;

    public Comm(@NonNull int id_comm, @NonNull String nom, @NonNull String prenom, @NonNull String email, @NonNull String login, @NonNull String hash) {
        this.id_comm = id_comm;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.login = login;
        this.hash = hash;
    }

    @NonNull
    public int getId_comm() {
        return id_comm;
    }

    public void setId_comm(@NonNull int id_comm) {
        this.id_comm = id_comm;
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
    public String getLogin() {
        return login;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    @NonNull
    public String getHash() {
        return hash;
    }

    public void setHash(@NonNull String hash) {
        this.hash = hash;
    }
}
