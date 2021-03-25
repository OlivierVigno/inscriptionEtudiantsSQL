package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Etudiant;
import model.Programme;
import model.Province;
import model.Ville;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myDatabase";
    private static final String LOG = "DatabaseHelper";

    private static final String TABLE_ETUDIANT = "etudiant";
    private static final String TABLE_VILLE = "ville";
    private static final String TABLE_PROVINCE = "province";
    private static final String TABLE_PROGRAMME = "programme";

    //common column names
    private static final String KEY_ID = "id";
    private static final String KEY_NOM = "nom";
    private static final String KEY_IDPROVINCE = "id_province";

    //table Etudiant - column names
    private static final String KEY_PRENOM = "prenom";
    private static final String KEY_NOCIVIQUE = "noCivique";
    private static final String KEY_RUE = "rue";
    private static final String KEY_IDVILLE = "ville";
    private static final String KEY_TELEPHONE = "telephone";
    private static final String KEY_IDPROGRAMME = "id_programme";

    private static final String CREATE_TABLE_ETUDIANT = "CREATE TABLE " + TABLE_ETUDIANT +
            "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NOM + "TEXT," + KEY_PRENOM + "TEXT," +
            KEY_NOCIVIQUE + "INTEGER," + KEY_RUE + "TEXT," + KEY_IDVILLE + "INTEGER," +
            KEY_IDPROVINCE + "INTEGER," + KEY_TELEPHONE + "TEXT," + KEY_IDPROGRAMME +
            "INTEGER," + "FOREIGN KEY(" + KEY_IDPROGRAMME + ")REFERENCES " + TABLE_PROGRAMME + "(" + KEY_ID + ")," +
            "FOREIGN KEY(" + KEY_IDVILLE + ")REFERENCES " + TABLE_VILLE + "(" + KEY_ID + ")," +
            "FOREIGN KEY(" + KEY_IDPROVINCE + ")REFERENCES " + TABLE_PROVINCE + "(" + KEY_ID + "))";

    private static final String CREATE_TABLE_VILLE = "CREATE TABLE " + TABLE_VILLE +
            "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NOM + "TEXT," + KEY_IDPROVINCE +
            "INTEGER," + "FOREIGN KEY(" + KEY_IDPROVINCE + ")REFERENCES " + TABLE_PROVINCE + "(" + KEY_ID + "))";

    private static final String CREATE_TABLE_PROVINCE = "CREATE TABLE " + TABLE_PROVINCE +
            "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NOM + "TEXT" + ")";

    private static final String CREATE_TABLE_PROGRAMME = "CREATE TABLE " + TABLE_PROGRAMME +
            "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NOM + "TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ETUDIANT);
        db.execSQL(CREATE_TABLE_PROVINCE);
        db.execSQL(CREATE_TABLE_VILLE);
        db.execSQL(CREATE_TABLE_PROGRAMME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ETUDIANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROVINCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VILLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRAMME);

        onCreate(db);
    }

    public int createEtudiant(Etudiant etudiant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NOM, etudiant.getNom());
        values.put(KEY_PRENOM, etudiant.getPrenom());
        values.put(KEY_NOCIVIQUE, etudiant.getNoCivique());
        values.put(KEY_RUE, etudiant.getRue());
        values.put(KEY_IDVILLE, etudiant.getVille());
        values.put(KEY_IDPROVINCE, etudiant.getProvince());
        values.put(KEY_TELEPHONE, etudiant.getTelephone());
        values.put(KEY_IDPROGRAMME, etudiant.getVille());

        int id_etudiant = (int) db.insert(TABLE_ETUDIANT, null, values);
        return id_etudiant;
    }

    public int createProvince(Province province) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NOM, province.getNom());

        int id_province = (int) db.insert(TABLE_PROVINCE, null, values);

        return id_province;
    }

    public int createProgramme(Programme programme) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NOM, programme.getNom());

        int id_programme = (int) db.insert(TABLE_PROGRAMME, null, values);
        return id_programme;
    }

    public int createVille(Ville ville) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NOM, ville.getNom());
        values.put(KEY_IDPROVINCE, ville.getProvince());

        int id_ville = (int) db.insert(TABLE_VILLE, null, values);
        return id_ville;
    }

    public Etudiant getEtudiant(int id_etudiant) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ETUDIANT + " WHERE "
                + KEY_ID + " = " + id_etudiant;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Etudiant etudiant = new Etudiant();
        etudiant.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        etudiant.setNom(c.getString(c.getColumnIndex(KEY_NOM)));
        etudiant.setPrenom(c.getString(c.getColumnIndex(KEY_PRENOM)));
        etudiant.setNoCivique(c.getInt(c.getColumnIndex(KEY_NOCIVIQUE)));
        etudiant.setRue(c.getString(c.getColumnIndex(KEY_RUE)));
        etudiant.setTelephone(c.getString(c.getColumnIndex(KEY_TELEPHONE)));
        etudiant.setVille(c.getInt(c.getColumnIndex(KEY_IDVILLE)));
        etudiant.setProvince(c.getInt(c.getColumnIndex(KEY_IDPROVINCE)));
        etudiant.setProgramme(c.getInt(c.getColumnIndex(KEY_IDPROGRAMME)));

        return etudiant;
    }

    public String[] getProvinces() {
        List<String> liste = new ArrayList<String>();

        String selectQuery = "SELECT " + KEY_NOM + " FROM " + TABLE_PROVINCE;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                liste.add(c.getString(c.getColumnIndex(KEY_NOM)));
            } while (c.moveToNext());
        }
        String[] stringArray = liste.toArray(new String[0]);
        return stringArray;
    }

    public String[] getVilles(String province) {
        List<String> liste = new ArrayList<String>();

        String selectQuery = "SELECT " + KEY_NOM + " FROM " + TABLE_VILLE + " WHERE "
                + KEY_IDPROVINCE + " = (SELECT " + KEY_ID + "FROM " + TABLE_PROVINCE + " WHERE " + KEY_NOM +  "=" + province + ")";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                liste.add(c.getString(c.getColumnIndex(KEY_NOM)));
            } while (c.moveToNext());
        }
        String[] stringArray = liste.toArray(new String[0]);
        return stringArray;
    }

    public String[] getProgrammes() {
        List<String> liste = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT " + KEY_NOM + " FROM " + TABLE_PROGRAMME;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                liste.add(c.getString(c.getColumnIndex(KEY_NOM)));
            } while (c.moveToNext());
        }
        String[] stringArray = liste.toArray(new String[0]);
        return stringArray;
    }
}
