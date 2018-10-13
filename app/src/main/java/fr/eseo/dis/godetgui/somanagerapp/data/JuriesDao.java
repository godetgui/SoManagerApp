package fr.eseo.dis.godetgui.somanagerapp.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface JuriesDao {

    // Liste de tous les juries existants
    @Query("SELECT * FROM juries")
    public List<Juries> findAllJuries();

    //Liste jurie grâce à l'id
    @Query("SELECT * FROM juries WHERE idJury = :idJurie")
    public Juries findJuriesFromId(int idJurie);

    //Liste de tous les membres de chaque jurie
    //@Query("SELECT JM.* FROM juries, JM, isMemberOf WHERE isMemberOf.idJury = :idJury and JM.idJm = isMemberOf.idJm")
    //public Juries findAllJMFromId(int idJury);
}

