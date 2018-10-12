package fr.eseo.dis.godetgui.somanagerapp.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CommDao {

    @Query("SELECT * FROM comm")
    public List<Comm> findAllComm();

    @Query("SELECT * FROM comm WHERE idComm = :idComm")
    public Comm findCommFromId(int idComm);
}

