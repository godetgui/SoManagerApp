package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "juries")
public class Juries {
    @PrimaryKey
    @NonNull
    private int idJurie;


    public Juries(@NonNull int idJurie) {
        this.idJurie = idJurie;
    }

    @NonNull
    public int getIdJurie() {
        return idJurie;
    }

    public void setIdJurie(@NonNull int idJurie) {
        this.idJurie = idJurie;
    }
}
