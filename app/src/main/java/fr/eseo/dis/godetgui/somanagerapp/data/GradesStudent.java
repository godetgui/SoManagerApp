package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "gradesStudent", primaryKeys ={"idJM","idStudent"} ,
        foreignKeys = {@ForeignKey(entity=JM.class, parentColumns = "idJM", childColumns = "idJM"),
                @ForeignKey(entity = Students.class, parentColumns = "idStudent",childColumns = "idStudent")})

public class GradesStudent {

    @NonNull
    private int idJM;

    @NonNull
    private int idStudent;

    @NonNull
    private String commentsGrade;

    @NonNull
    private int grade;

    public GradesStudent(@NonNull int idJM, @NonNull int idStudent, @NonNull String commentsGrade, @NonNull int grade) {
        this.idJM = idJM;
        this.idStudent = idStudent;
        this.commentsGrade = commentsGrade;
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
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(@NonNull int idStudent) {
        this.idStudent = idStudent;
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
