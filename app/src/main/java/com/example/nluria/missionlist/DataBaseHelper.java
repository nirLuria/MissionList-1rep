package com.example.nluria.missionlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by nluria on 10/6/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME= "TasksList.db";

    //ID - of the group, in all tables.

    //table of groups.
    public static final String TableGroup= "Table_Of_Groups";
    public static final String gTcol1= "NAME";
    public static final String gTcol2= "TITLE";
/*
    //table of peoples.
    public static final String TablePeople= "Table_Of_People";
    public static final String pTcol1= "ID";
    public static final String pTcol2= "NAME";
*/
    //table of tasks.
    public static final String TableTasks= "Table_Of_Tasks";
    public static final String tTcol1= "BELONGS_TO_GROUP";
    public static final String tTcol2= "DATA";


    public DataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create new tables.
        db.execSQL("Create table "+ TableGroup +" (NAME TEXT PRIMARY KEY AUTOINCREMENT , TITLE TEXT) ");
        db.execSQL("Create table "+ TableTasks +" (BELONGS_TO_GROUP TEXT , DATA TEXT, COUNT INTEGER PRIMARY KEY AUTOINCREMENT) ");
        System.out.println("created clean database");
     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableGroup);
  //      db.execSQL("DROP TABLE IF EXISTS " + TablePeople);
        db.execSQL("DROP TABLE IF EXISTS " + TableTasks);

        onCreate(db);
    }


    public boolean insertNewList(String title)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //check if the title of the group is already exists.
        String countQuery = ( "select count(*) from "+TableGroup+" where TITLE='" + title  + "'; " );
        Cursor mcursor = db.rawQuery(countQuery, null);
        mcursor.moveToFirst();
        int countOfCurrentTitle = mcursor.getInt(0);

        if (countOfCurrentTitle>0)
        {
            System.out.println(title+ " is already exist");
            return false;
        }

        //add the new group.
        contentValues.put(gTcol2, title);
        long result = db.insert(TableGroup , null,contentValues );

        if (result== -1)
            return false;
        else
            return true;
    }


    public boolean insertNewTask(String data, String group)
    {
        System.out.println("GROUP IS: " + group);
        System.out.println("DATA IS: " + data);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //check if the task of the group is already exists.
        String countQuery = ( "select count(*) from "+TableTasks+" where BELONGS_TO_GROUP='" + group + "' and DATA='" + data + "'; " );

        Cursor mcursor = db.rawQuery(countQuery, null);
        mcursor.moveToFirst();
        int countOfCurrentTitle = mcursor.getInt(0);

        if (countOfCurrentTitle>0)
        {
            System.out.println(data+ " is already exist");
            return false;
        }

        //add the new task.
        contentValues.put(tTcol1, group);
        contentValues.put(tTcol2, data);
        long result = db.insert(TableTasks , null,contentValues );

        if (result== -1)
            return false;
        else
            return true;
    }


    public boolean deleteAllGroups()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TableGroup);

        //create a new clean table.
        db.execSQL("Create table "+ TableGroup +" (ID INTEGER PRIMARY KEY AUTOINCREMENT , TITLE TEXT) ");

        //delete also all the tasks.
        deleteAllTasks();

        return true;
    }

    public boolean deleteAllTasks()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TableTasks);

        //create a new clean table.
        db.execSQL("Create table "+ TableTasks +" (BELONGS_TO_GROUP TEXT , DATA TEXT, COUNT INTEGER PRIMARY KEY AUTOINCREMENT) ");

        return true;
    }


    public boolean deleteAllTasksOfGroup(String group)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TableTasks + "  where BELONGS_TO_GROUP='" + group + "'; " );

        return true;
    }


    public boolean deleteOneGroup(String group)
    {
        deleteAllTasksOfGroup(group);

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TableGroup + "  where TITLE='" + group + "'; " );

        return true;
    }



    public boolean deleteOneTask(String group, String data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TableTasks + "  where BELONGS_TO_GROUP='" + group + "' and DATA='" + data + "'; " );

        return true;
    }


    public Cursor getGroups()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TableGroup, null);
        return res;
    }


    public Cursor getTasks(String group)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TableTasks+" where BELONGS_TO_GROUP='" + group + "'; ", null);
        return res;
    }


}
