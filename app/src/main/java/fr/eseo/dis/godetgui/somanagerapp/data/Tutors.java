package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "tutors", primaryKeys = {"idProject", "idJm"},
        foreignKeys = {@ForeignKey(entity = Projects.class, parentColumns ="idProject", childColumns = "idProject"),
                    @ForeignKey(entity = JM.class, parentColumns = "idJm", childColumns = "idJm")})
public class Tutors {

    @NonNull
    private int idJm;
    @NonNull
    private int idProject;



    public Tutors(@NonNull int idJm, @NonNull int idProject) {
        this.idJm = idJm;
        this.idProject = idProject;
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

}
