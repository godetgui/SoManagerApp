package fr.eseo.dis.godetgui.somanagerapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PseudoJuryProjectManager {


    private static final
    String TABLE_NAME = "pseudojuryprojects";
    public static final
    String KEY_ID_PJP="idPseudoJuryProject";
    public static final
    String KEY_ID_PSEUDO_JURY_PJP="idPseudoJury";
    public static final
    String KEY_ID_PROJECT_PJP="idProject";
    public static final
    String KEY_GRADE_PJ="grade";
    public static final
    String KEY_COMMENT_PJ="comment";
    public static final
    String KEY_TITLE_PJ="title";
    public static final
    String KEY_DESC_PJ="description";


    public static final
    String CREATE_TABLE_PSEUDO_JURIES_PROJECT = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_PJP+" INTEGER primary key,"
            +
            " "+KEY_ID_PSEUDO_JURY_PJP+" INTEGER," +
            " "+KEY_ID_PROJECT_PJP+" INTEGER," +
            " "+KEY_GRADE_PJ+" TEXT," +
            " "+KEY_COMMENT_PJ+" TEXT ," +
            " "+KEY_TITLE_PJ+" TEXT," +
            " "+KEY_DESC_PJ+" TEXT" +
            ");";




    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public PseudoJuryProjectManager(
            Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addPJ(PseudoJuryProject pj
    ) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_ID_PSEUDO_JURY_PJP, pj.getIdPseudoJury());
        values.put(KEY_ID_PROJECT_PJP, pj.getIdProject());
        values.put(KEY_GRADE_PJ, pj.getGrade());
        values.put(KEY_COMMENT_PJ, pj.getComment());
        values.put(KEY_TITLE_PJ, pj.getTitle());
        values.put(KEY_DESC_PJ, pj.getDescription());
        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(
                TABLE_NAME,null,values);
    }

    public int modPJ(PseudoJuryProject animal
    ) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_GRADE_PJ, animal.getGrade());

        String where = KEY_ID_PJP+" = ?";
        System.out.println("gggggg"+animal.getIdPseudoJuryProject());
        String[] whereArgs = {animal.getIdPseudoJuryProject()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supPJ(PseudoJuryProject pj
    ) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_PSEUDO_JURY_PJP+" = ?";
        String[] whereArgs = {pj.getIdPseudoJuryProject()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public List<PseudoJuryProject> getPJList(int idPJ) {
        List<PseudoJuryProject> pseudoProject = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE "+
                KEY_ID_PSEUDO_JURY_PJP+"="+idPJ;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PseudoJuryProject pjp = new PseudoJuryProject(0,0,0,"","","","");
                pjp.setIdProject(cursor.getInt(cursor.getColumnIndex(KEY_ID_PROJECT_PJP)));
                pjp.setIdPseudoJury(cursor.getInt(cursor.getColumnIndex(KEY_ID_PSEUDO_JURY_PJP)));
                pjp.setGrade(cursor.getString(cursor.getColumnIndex(KEY_GRADE_PJ)));
                pjp.setComment(cursor.getString(cursor.getColumnIndex(KEY_COMMENT_PJ)));
                pjp.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE_PJ)));
                pjp.setDescription(cursor.getString(cursor.getColumnIndex(KEY_COMMENT_PJ)));
                pseudoProject.add(pjp);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return pseudoProject;
    }

    public List<PseudoJuryProject> getPJProjectById(int idProject) {
        List<PseudoJuryProject> pseudoProject = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE "+
                KEY_ID_PROJECT_PJP+"="+idProject;

        Cursor c = db.rawQuery(
                "SELECT * FROM "+TABLE_NAME+" WHERE "+
                        KEY_ID_PROJECT_PJP+"='"+idProject+"'", null);

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PseudoJuryProject pjp = new PseudoJuryProject(0,0,0,"","","","");
                pjp.setIdProject(cursor.getInt(cursor.getColumnIndex(KEY_ID_PROJECT_PJP)));
                pjp.setIdPseudoJury(cursor.getInt(cursor.getColumnIndex(KEY_ID_PSEUDO_JURY_PJP)));
                pjp.setGrade(cursor.getString(cursor.getColumnIndex(KEY_GRADE_PJ)));
                pjp.setComment(cursor.getString(cursor.getColumnIndex(KEY_COMMENT_PJ)));
                pjp.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE_PJ)));
                pjp.setDescription(cursor.getString(cursor.getColumnIndex(KEY_COMMENT_PJ)));
                pseudoProject.add(pjp);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return pseudoProject;
    }


    public Cursor getPJ() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,
                null);
    }

    public PseudoJuryProject getPJ(int id) {
        // Retourne le pj dont l'id est passé en paramètre

        PseudoJuryProject pjp=new PseudoJuryProject(0,
                0, 0,"","","","");

        Cursor cursor = db.rawQuery(
                "SELECT * FROM "+TABLE_NAME+" WHERE "+
                        KEY_ID_PROJECT_PJP+"='"+id+"'", null);

        if (cursor.moveToFirst())
        {
            pjp.setIdPseudoJuryProject(cursor.getInt(cursor.getColumnIndex(KEY_ID_PJP)));

            pjp.setIdProject(cursor.getInt(cursor.getColumnIndex(KEY_ID_PROJECT_PJP)));
            pjp.setIdPseudoJury(cursor.getInt(cursor.getColumnIndex(KEY_ID_PSEUDO_JURY_PJP)));
            pjp.setGrade(cursor.getString(cursor.getColumnIndex(KEY_GRADE_PJ)));
            pjp.setComment(cursor.getString(cursor.getColumnIndex(KEY_COMMENT_PJ)));
            pjp.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE_PJ)));
            pjp.setDescription(cursor.getString(cursor.getColumnIndex(KEY_COMMENT_PJ)));
            cursor.close();
        }

        return pjp;
    }

}