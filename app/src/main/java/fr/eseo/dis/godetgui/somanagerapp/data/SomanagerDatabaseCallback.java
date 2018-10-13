package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

public class SomanagerDatabaseCallback extends RoomDatabase.Callback {





    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        /*
            for(String creationQuery : CREATE_TABLES){
                db.execSQL(creationQuery);
            }
        */
        Log.d("ROOM_Database_Insert:","Insert Artistes");
        for(String creationQuery : INSERT_ARTISTES){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Internautes");
        for(String creationQuery : INSERT_INTERNAUTES){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Pays");
        for(String creationQuery : INSERT_PAYS){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Films");
        for(String creationQuery : INSERT_FILMS){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Roles");
        for(String creationQuery : INSERT_ROLES){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Notations");
        for(String creationQuery : INSERT_NOTATIONS){
            db.execSQL(creationQuery);
        }
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
        Log.d("ROOM_Database:","onOpen()");
        super.onOpen(db);
    }

}
