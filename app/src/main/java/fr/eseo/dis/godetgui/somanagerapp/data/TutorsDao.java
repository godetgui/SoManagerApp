package fr.eseo.dis.godetgui.somanagerapp.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TutorsDao {

    @Query("SELECT * FROM tutors")
    public List<Tutors> findAllTutors();

    @Query("SELECT * FROM tutors WHERE idJm = :idJm")
    public Tutors findTutorByIdJM(int idJm);





}

