package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "gradesProject", primaryKeys ={"idJm","idProject"} ,
        foreignKeys = {@ForeignKey(entity=JM.class, parentColumns = "idJm", childColumns = "idJm"),
                @ForeignKey(entity = Projects.class, parentColumns = "idProject",childColumns = "idProject")})
public class GradesProject {

    @NonNull
    private int idJm;

    @NonNull
    private int idProject;

    @NonNull
    private String commentsGrades;

    @NonNull
    private int grade;

    public GradesProject(@NonNull int idJm, @NonNull int idProject, @NonNull String commentsGrades, @NonNull int grade) {
        this.idJm = idJm;
        this.idProject = idProject;
        this.commentsGrades = commentsGrades;
        this.grade = grade;
    }

    @NonNull
    public int getIdJm() {
        return idJm;
    }

    public void setIdJm(@NonNull int idJm) {
        this.idJm = idJm;
    }

    @NonNull
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(@NonNull int idProject) {
        this.idProject = idProject;
    }

    @NonNull
    public String getCommentsGrades() {
        return commentsGrades;
    }

    public void setCommentsGrades(@NonNull String commentsGrades) {
        this.commentsGrades = commentsGrades;
    }

    @NonNull
    public int getGrade() {
        return grade;
    }

    public void setGrade(@NonNull int grade) {
        this.grade = grade;
    }
}
