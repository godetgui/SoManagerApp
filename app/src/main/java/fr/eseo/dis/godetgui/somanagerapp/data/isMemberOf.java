package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "isMemberOf", primaryKeys ={"idJm","idJury"} ,
        foreignKeys = {@ForeignKey(entity=JM.class, parentColumns = "idJm", childColumns = "idJm"),
                @ForeignKey(entity = Juries.class, parentColumns = "idJury",childColumns = "idJury")})
public class isMemberOf {


    @NonNull
    private int idJm;

    @NonNull
    private int idJury;

    public isMemberOf(int idJm, @NonNull int idJury) {
        this.idJm = idJm;
        this.idJury = idJury;
    }

    public int getIdJm() {
        return idJm;
    }

    public void setIdJm(int idJM) {
        this.idJm = idJM;
    }

    @NonNull
    public int getIdJury() {
        return idJury;
    }

    public void setIdJury(@NonNull int idJurie) {
        this.idJury = idJurie;
    }
}
