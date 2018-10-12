package fr.eseo.dis.godetgui.somanagerapp.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface JMDao {

    @Query("SELECT * FROM JM")
    public List<JM> findAllJM();

    @Query("SELECT * FROM jm WHERE idJm = :idJm")
    public JM findJmFromId(int idJm);

    @Query("SELECT * FROM jm, isMemberOf WHERE isMemberOf.idJM = :idJm and jm.idJm = :idJm")
    public JM findAllTheirJuries(int idJm);

    @Query("SELECT projects.* FROM tutors, jm, projects WHERE tutors.idJm = :idJm and jm.idJm = :idJm")
    public JM findProjectsJmisTutor(int idJm);

}

