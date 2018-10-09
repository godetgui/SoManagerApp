package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

import java.sql.Time;
import java.util.Date;

@Entity(tableName = "assess", primaryKeys ={"idJurie","idProject"} ,
        foreignKeys = {@ForeignKey(entity=Juries.class, parentColumns = "idJurie", childColumns = "idJurie"),
                @ForeignKey(entity = Students.class, parentColumns = "idProject",childColumns = "idProject")})

public class assess {

    @NonNull
    private int idJurie;

    @NonNull
    private int idProject;

    @NonNull
    private Date date;

    @NonNull
    private Time time;

    public assess(@NonNull int idJurie, @NonNull int idProject, @NonNull Date date, @NonNull Time time) {
        this.idJurie = idJurie;
        this.idProject = idProject;
        this.date = date;
        this.time = time;
    }

    @NonNull
    public int getIdJurie() {
        return idJurie;
    }

    public void setIdJurie(@NonNull int idJurie) {
        this.idJurie = idJurie;
    }

    @NonNull
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(@NonNull int idProject) {
        this.idProject = idProject;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    @NonNull
    public Time getTime() {
        return time;
    }

    public void setTime(@NonNull Time time) {
        this.time = time;
    }
}
