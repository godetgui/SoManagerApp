package fr.eseo.dis.godetgui.somanagerapp.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PseudoJuriesDao {

    @Query("SELECT * FROM pseudojuries")
    public List<PseudoJuries> findAllPseudoJuries();




}

