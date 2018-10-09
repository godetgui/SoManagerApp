package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "isMemberOfPseudo", primaryKeys ={"idVisitor","idPseudoJuries"} ,
        foreignKeys = {@ForeignKey(entity=Visitors.class, parentColumns = "idVisitor", childColumns = "idVisitor"),
                @ForeignKey(entity = PseudoJuries.class, parentColumns = "idPseudoJuries",childColumns = "idPseudoJuries")})

public class IsMemberOfPseudo {

    @NonNull
    private int idVisitors;

    @NonNull
    private int idPseudoJuries;

    public IsMemberOfPseudo(int idVisitors, @NonNull int idPseudoJuries) {
        this.idVisitors = idVisitors;
        this.idPseudoJuries = idPseudoJuries;
    }

    public int getIdVisitors() {
        return idVisitors;
    }

    public void setIdVisitors(int idVisitors) {
        this.idVisitors = idVisitors;
    }

    @NonNull
    public int getIdPseudoJuries() {
        return idPseudoJuries;
    }

    public void setIdPseudoJuries(@NonNull int idPseudoJuries) {
        this.idPseudoJuries = idPseudoJuries;
    }
}
