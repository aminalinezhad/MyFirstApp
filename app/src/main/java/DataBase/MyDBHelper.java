package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyFirstApp.db";
    public static final int DATABASE_VERSION = 1;

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TSQLConst.CREATE_TABLE_MyProjectMessage);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(TSQLConst.DROP_TABLE_MyProjectMessages);
        onCreate(sqLiteDatabase);
    }
}
