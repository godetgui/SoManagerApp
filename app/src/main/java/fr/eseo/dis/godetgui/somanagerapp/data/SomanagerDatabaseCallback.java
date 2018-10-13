package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

public class SomanagerDatabaseCallback extends RoomDatabase.Callback {





    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        db.execSQL("INSERT INTO jm VALUES(0,'Vict','Serizot','serizovi')");

    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
        Log.d("ROOM_Database:","onOpen()");
        super.onOpen(db);
    }





}
