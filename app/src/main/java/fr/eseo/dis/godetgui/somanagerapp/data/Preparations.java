package fr.eseo.dis.godetgui.somanagerapp.data;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "preparations", foreignKeys = {@ForeignKey(entity= JM.class, parentColumns = "idJM", childColumns="idJM"),@ForeignKey(entity= Projects.class, parentColumns = "idProject", childColumns="idProject")})

public class Preparations {
    @PrimaryKey
    @NonNull
    private int idprepa;

    @NonNull
    private String commentsPrepa;

    @NonNull
    private String questionsPrepa;

    @NonNull
    private int idJM;

    @NonNull
    private int idProject;

    public Preparations(@NonNull int idprepa, @NonNull String commentsPrepa, @NonNull String questionsPrepa, @NonNull int idJM, @NonNull int idProject) {
        this.idprepa = idprepa;
        this.commentsPrepa = commentsPrepa;
        this.questionsPrepa = questionsPrepa;
        this.idJM = idJM;
        this.idProject = idProject;
    }

    @NonNull
    public int getIdprepa() {
        return idprepa;
    }

    public void setIdprepa(@NonNull int idprepa) {
        this.idprepa = idprepa;
    }

    @NonNull
    public String getCommentsPrepa() {
        return commentsPrepa;
    }

    public void setCommentsPrepa(@NonNull String commentsPrepa) {
        this.commentsPrepa = commentsPrepa;
    }

    @NonNull
    public String getQuestionsPrepa() {
        return questionsPrepa;
    }

    public void setQuestionsPrepa(@NonNull String questionsPrepa) {
        this.questionsPrepa = questionsPrepa;
    }

    @NonNull
    public int getIdJM() {
        return idJM;
    }

    public void setIdJM(@NonNull int idJM) {
        this.idJM = idJM;
    }

    @NonNull
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(@NonNull int idProject) {
        this.idProject = idProject;
    }
}
