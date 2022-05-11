package fr.epf.min1.deryne.myvelib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.EnumMap;

public class FavDB extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "Station";
    private static String TABLE_NAME = "favoriteTable";
    public static String KEY_ID = "id";
    public static String ITEM_TITLE = "itemTitle";
    public static String ITEM_IMAGE ="itemImage";
    public static String FAVORITE_STATUS = "fStatus";
    //ne pas oublier de marquer du texte dans l'espace
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + "TEXT," + ITEM_TITLE+ "TEXT,"
            + ITEM_IMAGE + "TEXT," + FAVORITE_STATUS+ "TEXT)";

    public FavDB(Context context){ super(context, DATABASE_NAME, null, DB_VERSION);}



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //créer une table vide
    public void insertEmpty(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //entrer la valeur
        for (int x = 1; x <11; x++) {
            cv.put(KEY_ID, x);
            cv.put(FAVORITE_STATUS,"0");
            db.insert(TABLE_NAME,null,cv);

        }
    }
    //insert data into database
    public void insertIntoTheDatabase(String item_title, int item_image, String id, String fav_status){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ITEM_TITLE, item_title);
        cv.put(ITEM_IMAGE, item_image);
        cv.put(KEY_ID, id);
        cv.put(FAVORITE_STATUS, fav_status);
        db.insert(TABLE_NAME, null, cv);
        Log.d("favDB Status", item_title + ", favstatus -"+fav_status+"-,"+cv);
    }
    //lire toutes les données

    public Cursor read_all_data(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select + from" + TABLE_NAME + "where" + KEY_ID + "="+ id+"";
        return db.rawQuery(sql, null, null);

    }
    //enlever des lignes de la base de données
    public void remove_fav(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE" + TABLE_NAME + "SET "+FAVORITE_STATUS+"='0' WHERE"+KEY_ID+"="+id+"";
        db.execSQL(sql);
        Log.d("remove",id.toString());
    }
    //sélectionner tous les favoris de la liste

    public Cursor select_all_favorites_list(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+FAVORITE_STATUS+" ='1'";
        return  db.rawQuery(sql, null, null);



    }
}
