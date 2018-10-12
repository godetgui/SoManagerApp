package fr.eseo.dis.godetgui.somanagerapp.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GradesProjectDao {

    @Query("SELECT * FROM gradesProject")
    public List<GradesProject> findAllGrades();

    @Query("SELECT projects.* FROM gradesProject, projects WHERE  projects.idProject= :idProject and gradesProject.idProject = :idProject")
    public Projects findGradeByProject(int idProject);


}

