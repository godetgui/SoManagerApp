package fr.eseo.dis.godetgui.somanagerapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PseudoJuriesManager {


    private static final
    String TABLE_NAME = "pseudojuries";
    public static final
    String KEY_ID_PJ="idPseudoJuries";
    public static final
    String KEY_LOGIN_PJ="loginPj";
    public static final
    String KEY_MDP_PJ="mdpPj";
    public static final
    String KEY_FONCTION_PJ="fonctionPJ";
    public static final
    String KEY_EMAIL_PJ="emailPj";


    public static final
    String CREATE_TABLE_PSEUDO_JURIES = "CREATE TABLE "+TABLE_NAME+

            " (" +
            " "+KEY_ID_PJ+" INTEGER primary key,"
            +
            " "+KEY_LOGIN_PJ+" TEXT," +
            " "+KEY_MDP_PJ+" TEXT," +
            " "+KEY_FONCTION_PJ+" TEXT," +
            " "+KEY_EMAIL_PJ+" TEXT" +

            ");";


    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public PseudoJuriesManager(
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

    public long addPJ(PseudoJuries pj
    ) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN_PJ, pj.getLoginPj());
        values.put(KEY_MDP_PJ, pj.getMdpPj());
        values.put(KEY_FONCTION_PJ, pj.getFonctionPj());
        values.put(KEY_EMAIL_PJ, pj.getEmailPj());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(
                TABLE_NAME,null,values);
    }

    public int modPJ(PseudoJuries pj
    ) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN_PJ, pj.getLoginPj());
        values.put(KEY_MDP_PJ, pj.getMdpPj());
        values.put(KEY_FONCTION_PJ, pj.getFonctionPj());
        values.put(KEY_EMAIL_PJ, pj.getEmailPj());

        String where = KEY_ID_PJ+" = ?";
        String[] whereArgs = {pj.getIdPseudoJuries()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supPJ(PseudoJuries pj
    ) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_PJ+" = ?";
        String[] whereArgs = {pj.getIdPseudoJuries()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public PseudoJuries getPJ(String login) {
        // Retourne le pj dont l'id est passé en paramètre

        PseudoJuries a=new PseudoJuries(0,
                "", "","","");

        Cursor c = db.rawQuery(
                "SELECT * FROM "+TABLE_NAME+" WHERE "+
                        KEY_LOGIN_PJ+"='"+login+"'", null);
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

    public Cursor getPJ() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,
                null);
    }

}