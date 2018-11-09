package fr.eseo.dis.godetgui.somanagerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

import fr.eseo.dis.godetgui.somanagerapp.data.MySQLite;
import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuryProject;

public class PseudoJuryProjectManager {


    private static final
    String TABLE_NAME = "pseudojuryproject";
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
    String CREATE_TABLE_PSEUDO_JURIES_PROJECT = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_PJP+" INTEGER primary key,"
            +
            " "+KEY_ID_PSEUDO_JURY_PJP+" INTEGER," +
            " "+KEY_ID_PROJECT_PJP+" INTEGER," +
            " "+KEY_GRADE_PJ+" TEXT," +
            " "+KEY_COMMENT_PJ+" TEXT" +
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
        values.put(KEY_ID_PSEUDO_JURY_PJP, pj.getIdPseudoJuryProject());
        values.put(KEY_ID_PJP, pj.getIdPseudoJury());
        values.put(KEY_ID_PROJECT_PJP, pj.getIdProject());
        values.put(KEY_GRADE_PJ, pj.getGrade());
        values.put(KEY_COMMENT_PJ, pj.getComment());
        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(
                TABLE_NAME,null,values);
    }

    public int modPJ(PseudoJuryProject pj
    ) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_ID_PSEUDO_JURY_PJP, pj.getIdPseudoJury());
        values.put(KEY_ID_PROJECT_PJP, pj.getIdProject());
        values.put(KEY_GRADE_PJ, pj.getGrade());
        values.put(KEY_COMMENT_PJ, pj.getComment());

        String where = KEY_ID_PSEUDO_JURY_PJP+" = ?";
        String[] whereArgs = {pj.getIdPseudoJuryProject()+""};

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

    /*
    public PseudoJuries getPJ(String garde) {
        // Retourne le pj dont l'id est passé en paramètre

        PseudoJuries a=new PseudoJuries(0,
                "", "","","");

        Cursor c = db.rawQuery(
                "SELECT * FROM "+TABLE_NAME+" WHERE "+
                        KEY_GRADE_PJ+"='"+gr+"'", null);
        if (c.moveToFirst())
        {
            a.setIdPseudoJuries(c.
                    getInt(c.getColumnIndex(KEY_ID_PJ)));
            a.setLoginPj(c.
                    getString(c.getColumnIndex(KEY_LOGIN_PJ)));
            a.setMdpPj(c.
                    getString(c.getColumnIndex(KEY_MDP_PJ)));
            a.setFonctionPj(c.
                    getString(c.getColumnIndex(KEY_FONCTION_PJ)));
            a.setEmailPj(c.
                    getString(c.getColumnIndex(KEY_EMAIL_PJ)));
            c.close();
        }

        return a;
    }
    */

    public Cursor getPJ() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,
                null);
    }

}