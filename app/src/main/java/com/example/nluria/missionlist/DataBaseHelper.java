package com.example.nluria.missionlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

/**
 * Created by nluria on 10/6/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME= "TasksList.db";

    //ID - of the group, in all tables.

    //table of groups.
    public static final String TableGroup= "Table_Of_Groups";
    public static final String gTcol1= "ID";
    public static final String gTcol2= "TITLE";

    //table of peoples.
    public static final String TablePeople= "Table_Of_People";
    public static final String pTcol1= "ID";
    public static final String pTcol2= "NAME";

    //table of tasks.
    public static final String TableTasks= "Table_Of_Tasks";
    public static final String tTcol1= "ID";
    public static final String tTcol2= "DATA";

    //SQL creation strings.
   /* private static final String SQL_CREATE_TABLE_PEOPLE = "CREATE TABLE " + TablePeople + "("
            + pTcol1 + "INTEGER PRIMARY KEY, "
            + pTcol2 + "TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_TASKS = "CREATE TABLE " + TableTasks + "("
            + tTcol1 + "INTEGER PRIMARY KEY, "
            + tTcol2 + "TEXT NOT NULL "
            +");";


*/


    public DataBaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create new tables.
        db.execSQL("Create table "+ TableGroup +" (ID INTEGER PRIMARY KEY AUTOINCREMENT , TITLE TEXT) ");
        db.execSQL("Create table "+ TablePeople +" (ID INTEGER PRIMARY KEY , NAME TEXT) ");
        db.execSQL("Create table "+ TableTasks +" (ID INTEGER PRIMARY KEY  , DATA TEXT) ");
        System.out.println("created clean database");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableGroup);
        db.execSQL("DROP TABLE IF EXISTS " + TablePeople);
        db.execSQL("DROP TABLE IF EXISTS " + TableTasks);

        onCreate(db);
    }


    public boolean insertNewList(String title)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();




        //check if the title of the group is already exist.
        String countQuery = ( "SELECT count(*) FROM " +TableGroup  );
        Cursor mcursor = db.rawQuery(countQuery, null);
        mcursor.moveToFirst();
        int count = mcursor.getInt(0);
        System.out.println("count of table is " +count);

      //  int count= db.("SELECT count(*) FROM " + TableGroup +"WHERE TITLE = '"+ title +"';" );

        if (TableGroup.contains(title))
        {
            System.out.println(title+ " is already exist");
            return false;
        }


        contentValues.put(gTcol2, title);
        long result = db.insert(TableGroup , null,contentValues );
        if (result== -1)
            return false;
        else
            return true;
    }


    public boolean deleteList(String title)
    {
        System.out.println(title +" to be deleted");
        SQLiteDatabase db = this.getWritableDatabase();
       // db.execSQL("DELETE FROM " + TableGroup + "WHERE " + gTcol2 + "='"+ title +"';"   );
        // + "WHERE" + gTcol2 + "=\'"+ title +"\';"

        if (db.delete(TableGroup, "TITLE = ?", new String[] {title})>0)
            return true;
        return false;
    }


    public boolean deleteAllGroups()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DELETE FROM " + TableGroup +";"   );
        // + "WHERE" + gTcol2 + "=\'"+ title +"\';"
        db.execSQL("DROP TABLE IF EXISTS " + TableGroup);

        //create a new clean table.
        db.execSQL("Create table "+ TableGroup +" (ID INTEGER PRIMARY KEY AUTOINCREMENT , TITLE TEXT) ");

    //    if (db.delete(TableGroup, "TITLE = ?", new String[] {title})>0)
      //      return true;
        return true;
    }


    public Cursor getGroups()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TableGroup, null);
        return res;
    }



}
