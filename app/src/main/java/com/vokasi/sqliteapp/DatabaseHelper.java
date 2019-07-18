package com.vokasi.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME="student_database";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_STUDENTS="students";
    private static final String KEY_ID="id";
    private static final String KEY_FIRSTNAME="name";
    private static final String KEY_ALAMAT="alamat";
    private static final String CREATE_TABLE_STUDENTS=
            "CREATE TABLE "
            +TABLE_STUDENTS
            +"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_FIRSTNAME+" TEXT,"
            +KEY_ALAMAT+" TEXT)"
            ;
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,
                DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENTS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '"
                +TABLE_STUDENTS+"'");
        onCreate(sqLiteDatabase);

    }

    public long addStudentDetail(String student
            ,String alamat){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_FIRSTNAME,student);
        values.put(KEY_ALAMAT,alamat);
        long insert=db.insert(TABLE_STUDENTS,
                null,values);
        return insert;
    }

    public ArrayList<Map<String, Object>> getAllStudentsList(){
        ArrayList<Map<String, Object>> studentArrayList
                =new ArrayList<>();
        String nama="";
        String alamat="";
        int id=0;
        String selectQuery="SELECT * FROM "+TABLE_STUDENTS;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do{
                id=c.getInt(c.getColumnIndex(
                        KEY_ID));
                nama=c.getString(c.getColumnIndex(
                        KEY_FIRSTNAME));
                alamat=c.getString(c.getColumnIndex(
                        KEY_ALAMAT));
                Map<String, Object> listItemMap = new HashMap<>();
                listItemMap.put("id", id);
                listItemMap.put("nama", nama);
                listItemMap.put("alamat", alamat);
                studentArrayList.add(listItemMap);
            }while (c.moveToNext());
        }
        return studentArrayList;
    }


    public void update(int id, String nama,String alamat){
        SQLiteDatabase db =this.getWritableDatabase();
        String updateQuery="UPDATE "+TABLE_STUDENTS+" SET "
                +KEY_FIRSTNAME+ "='"+nama+"',"
                +KEY_ALAMAT+"='"+alamat+"' WHERE "
                +KEY_ID+ "='"+id+"'";
        db.execSQL(updateQuery);
        db.close();
    }
    public void delete(int id){
        SQLiteDatabase db =this.getWritableDatabase();
        String updateQuery="DELETE FROM "+TABLE_STUDENTS
                +" WHERE " +KEY_ID+ "='"+id+"'";
        db.execSQL(updateQuery);
        db.close();
    }


}
