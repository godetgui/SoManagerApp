package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "roles", primaryKeys ={"idJM","idJurie"} ,
        foreignKeys = {@ForeignKey(entity=JM.class, parentColumns = "id_film", childColumns = "id_film"),
                @ForeignKey(entity = Artiste.class, parentColumns = "id_artiste",childColumns = "id_artiste")})

public class isMemberOf {

}
