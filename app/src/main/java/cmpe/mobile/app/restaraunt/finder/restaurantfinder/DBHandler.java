package cmpe.mobile.app.restaraunt.finder.restaurantfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by savani on 3/23/16.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final String db_name = "Test2";
    private static final int db_version = 1;
    public static final String ID = "_id";
  /*  public static final String NAME = "name";
   public static final String LATITUDE = "latitude";
    //public static final String LONGITUDE = "longitude";
    public static final String CITY = "city";
    public static final String ADDRESS = "displayaddress";
    public static final String RATING = "rating";
    public static final String MOBILEURL = "mobileurl";
    public static final String PHONE = "phone";
    public static final String CATEGORIES = "categories";
    public static final String RATINGIMGURL = "ratingimgurl";
    public static final String REVIEWCOUNT = "reviewcount";
    public static final String RATINGIMGURLSMALL = "ratingimgurlsmall";
    public static final String URL = "url";
    public static final String SNIPPETTEXT = "snippettext";
    public static final String IMAGEURL = "imageurl";
    public static final String SNIPPETIMAGEURL = "snippetimageurl";
    public static final String DISPLAYPHONE = "displayphone";
    public static final String RATINGIMGURLLARGE = "ratingimgurllarge";
    public static final String ISCLAIMED = "isclaimed";
    public static final String ISCLOSED = "isclosed";
    public static final String GEOACCURACY = "geoaccuracy";*/




    DBHandler(Context context) {
        super(context, db_name, null, db_version);
    }

//int flag = (boolValue)? 1 : 0;
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("In create");
        final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + db_name + " (" +
                        "_id" +" TEXT PRIMARY KEY,"+"yelp_response TEXT )";
        db.execSQL(SQL_CREATE_ENTRIES);
    }



    public void addEntry(String yelp_response, String id){
        System.out.println("In add");
        String query = "select * FROM " + db_name + " WHERE _id" + " =  \"" + id+ "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            query = "update "+ db_name +" set yelp_response = "+ yelp_response + "where _id = " + id;
            db.rawQuery(query, null);
            db.close();
            return;

        }
        db.close();
        ContentValues values = new ContentValues();
        values.put("_id", id);
        values.put("yelp_response", yelp_response);
        SQLiteDatabase db1 = this.getWritableDatabase();
        db1.insert(db_name, null, values);
        db1.close();
    }

    public int checkFavorite(String id){  //to be called during search for checking existing favorite
        int ret = 0;
        String query = "select * FROM " + db_name + " WHERE _id" + " =  \"" + id+ "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            ret = 1; //is a favorite
          //  cursor.moveToFirst();
          //  ret = Integer.parseInt(cursor.getString(2));
          //  cursor.close();
        } else {
            //product = null;
        }
        System.out.println("fav " + ret);
        db.close();
        return ret;
    }
    public String readAll(){
       String allEntries = null;
        String query = "select * FROM " + db_name;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            allEntries = (cursor.getString(1));
            cursor.close();
        } else {
            //product = null;
        }

        db.close();
        return allEntries;
    }
    public boolean deleteEntry(String id){
        Boolean result = false;
        String query = "select * FROM " + db_name + " WHERE _id" + " =  \"" + id+ "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {

             db.delete(db_name, "_id " + " = ?", new String[]{(id)});
            cursor.close();
            result = true;

        }
        db.close();
        System.out.println("Result " + result);
        return result;
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +db_name );
        onCreate(db);
    }

    public void deleteTable(){
       /* SQLiteDatabase db = new SQLiteDatabase();
        Test1.execSQL("DROP TABLE IF EXISTS " +db_name );
        onCreate(db);*/
    }


}
