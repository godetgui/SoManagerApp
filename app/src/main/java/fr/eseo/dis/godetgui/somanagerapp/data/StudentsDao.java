package fr.eseo.dis.godetgui.somanagerapp.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface StudentsDao {

    @Query("SELECT * FROM students")
    public List<Students> findAllStudents();

    @Query("SELECT * FROM students WHERE idStudent = :idStudent")
    public JM findJmFromId(int idStudent);


}

