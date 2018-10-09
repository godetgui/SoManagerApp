package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "roles", primaryKeys ={"idJM","idJurie"} ,
        foreignKeys = {@ForeignKey(entity=JM.class, parentColumns = "idJM", childColumns = "idJM"),
                @ForeignKey(entity = Juries.class, parentColumns = "idJurie",childColumns = "idJurie")})

public class isMemberOf {

    @NonNull
    private int idJM;

    @NonNull
    private int idJurie;

    public isMemberOf(int idJM, @NonNull int idJurie) {
        this.idJM = idJM;
        this.idJurie = idJurie;
    }

    public int getIdJM() {
        return idJM;
    }

    public void setIdJM(int idJM) {
        this.idJM = idJM;
    }

    @NonNull
    public int getIdJurie() {
        return idJurie;
    }

    public void setIdJurie(@NonNull int idJurie) {
        this.idJurie = idJurie;
    }
}
