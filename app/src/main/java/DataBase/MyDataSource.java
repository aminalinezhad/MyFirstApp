package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataSource {
    private Context mContext;
    private SQLiteDatabase mDataBase;
    private SQLiteOpenHelper mDbHelper;

    public SQLiteDatabase getDB(){
        return mDataBase;
    }

    public MyDataSource(Context Context) {
        this.mContext = Context;
        mDbHelper = new MyDBHelper(mContext);
        mDataBase = mDbHelper.getWritableDatabase();
    }

    public void open(){
        mDataBase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDataBase.close();
    }
}

