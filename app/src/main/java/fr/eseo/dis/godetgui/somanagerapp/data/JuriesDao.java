package fr.eseo.dis.godetgui.somanagerapp.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface JuriesDao {

    @Query("SELECT * FROM juries")
    public List<Juries> findAllJuries();

    @Query("SELECT * FROM juries WHERE idJurie = :idJurie")
    public Juries findJuriesFromId(int idJurie);


}

