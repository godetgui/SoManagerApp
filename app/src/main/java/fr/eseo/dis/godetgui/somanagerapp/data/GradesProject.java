package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "gradesProject", primaryKeys ={"idJM","idProject"} ,
        foreignKeys = {@ForeignKey(entity=JM.class, parentColumns = "idJM", childColumns = "idJM"),
                @ForeignKey(entity = Projects.class, parentColumns = "idProject",childColumns = "idProject")})

public class GradesProject {

    @NonNull
    private int idJM;

    @NonNull
    private int idProject;

    @NonNull
    private String commentsGrades;

    @NonNull
    private int grade;

    public GradesProject(@NonNull int idJM, @NonNull int idProject, @NonNull String commentsGrades, @NonNull int grade) {
        this.idJM = idJM;
        this.idProject = idProject;
        this.commentsGrades = commentsGrades;
        this.grade = grade;
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
