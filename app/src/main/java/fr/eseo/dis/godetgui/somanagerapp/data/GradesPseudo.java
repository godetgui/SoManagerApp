package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "gradesPseudo", primaryKeys ={"idVisitor","idProject"} ,
        foreignKeys = {@ForeignKey(entity=Visitors.class, parentColumns = "idVisitor", childColumns = "idVisitor"),
                @ForeignKey(entity = Projects.class, parentColumns = "idProject",childColumns = "idProject")})

public class GradesPseudo {

    @NonNull
    private int idVisitor;

    @NonNull
    private int idProject;

    @NonNull
    private String commentsGrade;

    @NonNull
    private int grade;

    public GradesPseudo(@NonNull int idVisitor, @NonNull int idProject, @NonNull String commentsGrade, @NonNull int grade) {
        this.idVisitor = idVisitor;
        this.idProject = idProject;
        this.commentsGrade = commentsGrade;
        this.grade = grade;
    }

    @NonNull
    public int getIdVisitor() {
        return idVisitor;
    }

    public void setIdVisitor(@NonNull int idVisitor) {
        this.idVisitor = idVisitor;
    }

    @NonNull
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(@NonNull int idProject) {
        this.idProject = idProject;
    }

    @NonNull
    public String getCommentsGrade() {
        return commentsGrade;
    }

    public void setCommentsGrade(@NonNull String commentsGrade) {
        this.commentsGrade = commentsGrade;
    }

    @NonNull
    public int getGrade() {
        return grade;
    }

    public void setGrade(@NonNull int grade) {
        this.grade = grade;
    }
}
