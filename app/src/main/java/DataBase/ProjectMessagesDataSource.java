package DataBase;

import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataModel.MyProjectMessage;
import DataModel.MyProjectMessageList;
import DataModel.msgKind;

public class ProjectMessagesDataSource extends MyDataSource {

    public ProjectMessagesDataSource(Context Context) {
        super(Context);
    }

    public void insertMessage(MyProjectMessage msg) {
        getDB().insert(TSQLConst.TABLE_NAME_MyProjectMessages, null, msg.getContentValues());
    }

    private Date ConverStrToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public MyProjectMessageList getAllMessages(String googleID) {
        MyProjectMessageList list = new MyProjectMessageList();
        Cursor cursor = null;
        if (googleID == null) {
            cursor = getDB().query(TSQLConst.TABLE_NAME_MyProjectMessages,
                    TSQLConst.ALL_COLUMN_MyProjectMessages, null, null, null, null,
                    TSQLConst.COLUMN_DATE + " DESC");
        } else {
            String[] args = {googleID};
            cursor = getDB().query(TSQLConst.TABLE_NAME_MyProjectMessages,
                    TSQLConst.ALL_COLUMN_MyProjectMessages, TSQLConst.COLUMN_USER_GOOGLE_ID + "=?", args,
                    null, null, TSQLConst.COLUMN_DATE);

        }
        while (cursor.moveToNext()) {
            MyProjectMessage msg = new MyProjectMessage();
            msg.setID(cursor.getInt(cursor.getColumnIndexOrThrow(TSQLConst.COLUMN_ID)));
            msg.setUserGoogleID(cursor.getString(cursor.getColumnIndexOrThrow(TSQLConst.COLUMN_USER_GOOGLE_ID)));
            msg.setCreateDate(ConverStrToDate(cursor.getString(cursor.getColumnIndexOrThrow(TSQLConst.COLUMN_DATE))));
            msg.setKind(msgKind.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(TSQLConst.COLUMN_KIND))));
            msg.setResourcePath(cursor.getString(cursor.getColumnIndexOrThrow(TSQLConst.COLUMN_RESOURCE_PATH)));
            msg.setText(cursor.getString(cursor.getColumnIndexOrThrow(TSQLConst.COLUMN_TEXT)));
            list.addItem(msg);
        }
        cursor.close();
        return list;
    }
}
