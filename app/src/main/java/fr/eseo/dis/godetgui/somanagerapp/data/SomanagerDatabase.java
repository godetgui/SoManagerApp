package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {assess.class, Comm.class, GradesProject.class, GradesPseudo.class, GradesStudent.class, isMemberOf.class, IsMemberOfPseudo.class, JM.class, Juries.class, Preparations.class, Projects.class, PseudoJuries.class, Students.class, Tutors.class, Visitors.class }, version =1)
public abstract class SomanagerDatabase extends RoomDatabase {

    private static SomanagerDatabase INSTANCE;

    public abstract assessDao assessDaoDao();

    public abstract CommDao commDao();

    public abstract GradesProjectDao gradesProjectDao();

    public abstract GradesPseudoDao gradesPseudoDao();

    public abstract GradesStudentDao gradesStudentDao();

    public abstract isMemberOfDao isMemberOfDao();

    public abstract isMemberOfPseudoDao isMemberOfPseudoDao();

    public abstract JMDao jmDao();

    public abstract JuriesDao juriesDao();

    public abstract PreparationsDao preparationsDao();

    public abstract ProjectsDao projectsDao();

    public abstract PseudoJuriesDao pseudoJuriesDao();

    public abstract StudentsDao studentsDao();

    public abstract TutorsDao tutorsDao();

    public abstract VisitorsDao visitorsDao();



    public static SomanagerDatabase getDatabase(Context context){
        if(INSTANCE == null) {
            //Database needs to be 'bound' to a context, identified by a sub class of RoomDatabase
            // and have a filename where the database will be stored physically on the device
            INSTANCE = Room.databaseBuilder(context, SomanagerDatabase.class, "somanager.db")
                    // For ease of use only => Need to delete this for production code
                    .allowMainThreadQueries()
                    //When migrating delete the database and recreate it
                    .fallbackToDestructiveMigration()
                    .addCallback(new SomanagerDatabaseCallback())
                    //Create the database
                    .build();
        }
        return INSTANCE;
    }



    public static void destroyInstance(){
        INSTANCE = null;
    }
}
