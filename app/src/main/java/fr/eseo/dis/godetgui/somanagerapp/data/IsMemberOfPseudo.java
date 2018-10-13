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
    private int idVisitor;

    @NonNull
    private int idPseudoJuries;

    public IsMemberOfPseudo(int idVisitor, @NonNull int idPseudoJuries) {
        this.idVisitor = idVisitor;
        this.idPseudoJuries = idPseudoJuries;
    }

    public int getIdVisitor() {
        return idVisitor;
    }

    public void setIdVisitor(int idVisitor) {
        this.idVisitor = idVisitor;
    }

    @NonNull
    public int getIdPseudoJuries() {
        return idPseudoJuries;
    }

    public void setIdPseudoJuries(@NonNull int idPseudoJuries) {
        this.idPseudoJuries = idPseudoJuries;
    }
}
