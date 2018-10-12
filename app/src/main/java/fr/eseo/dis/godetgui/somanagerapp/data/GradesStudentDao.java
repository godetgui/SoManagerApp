package fr.eseo.dis.godetgui.somanagerapp.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GradesStudentDao {

    @Query("SELECT * FROM gradesStudent")
    public List<GradesStudent> findAllProjects();

    @Query("SELECT students.* FROM gradesStudent, students WHERE  students.idStudent= :idStudent and gradesStudent.idStudent = :idStudent")
    public Projects findGradeByIdStudent(int idStudent);


}

