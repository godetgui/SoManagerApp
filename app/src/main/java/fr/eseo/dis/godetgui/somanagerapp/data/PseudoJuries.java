package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "pseudojuries", primaryKeys = {"idPseudoJuries"},
        foreignKeys = {@ForeignKey(entity = Projects.class, parentColumns ="idProject", childColumns = "idProject"),
                @ForeignKey(entity = Comm.class, parentColumns = "idComm", childColumns = "idComm")})

public class PseudoJuries {
    @NonNull
    private int idComm;

    @NonNull
    private int idProject;

    public PseudoJuries(@NonNull int idComm, @NonNull int idProject) {
        this.idComm = idComm;
        this.idProject = idProject;
    }

    @NonNull
    public int getIdComm() {
        return idComm;
    }

    public void setIdComm(@NonNull int idComm) {
        this.idComm = idComm;
    }

    @NonNull
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(@NonNull int idProject) {
        this.idProject = idProject;
    }
}
