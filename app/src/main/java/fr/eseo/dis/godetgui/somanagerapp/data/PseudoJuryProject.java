package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "pseudojuryproject", primaryKeys = {"idPseudoJuryProject"})
public class PseudoJuryProject {

    @NonNull
    private int idPseudoJuryProject;

    private int idPseudoJury;

    private int idProject;

    private String grade;

    private String comment;

    private String titleProject;

    private String description;

    public PseudoJuryProject(@NonNull int idPseudoJuryProject, int idPseudoJury, int idProject, String grade, String comment, String titleProject, String description) {
        this.idPseudoJuryProject = idPseudoJuryProject;

        this.idPseudoJury = idPseudoJury;
        this.idProject = idProject;
        this.grade = grade;
        this.comment = comment;
        this.titleProject = titleProject;
        this.description = description;
    }

    @NonNull
    public int getIdPseudoJuryProject() {
        return idPseudoJuryProject;
    }

    public void setIdPseudoJuryProject(@NonNull int idPseudoJuryProject) {
        this.idPseudoJuryProject = idPseudoJuryProject;
    }

    public int getIdPseudoJury() {
        return idPseudoJury;
    }

    public void setIdPseudoJury(int idPseudoJury) {
        this.idPseudoJury = idPseudoJury;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitleProject() {
        return titleProject;
    }

    public void setTitleProject(String titleProject) {
        this.titleProject = titleProject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
