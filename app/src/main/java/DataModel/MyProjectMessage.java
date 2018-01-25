package DataModel;

import android.content.ContentValues;

import java.text.SimpleDateFormat;
import java.util.Date;

import DataBase.TSQLConst;

public class MyProjectMessage {
    private int ID;
    private String UserGoogleID;
    private Date createDate;
    private msgKind kind;
    private String resourcePath;
    private String text;

    public MyProjectMessage() {
    }

    public MyProjectMessage(Date createDate, msgKind kind, String resourcePath, String text) {
        this.createDate = createDate;
        this.kind = kind;
        this.resourcePath = resourcePath;
        this.text = text;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public msgKind getKind() {
        return kind;
    }

    public void setKind(msgKind kind) {
        this.kind = kind;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserGoogleID() {
        return UserGoogleID;
    }

    public void setUserGoogleID(String userGoogleID) {
        UserGoogleID = userGoogleID;
    }

    public String getCreateDateStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(createDate);
    }



    public ContentValues getContentValues(){
        ContentValues values = new ContentValues(5);
        values.put(TSQLConst.COLUMN_USER_GOOGLE_ID, UserGoogleID);
        values.put(TSQLConst.COLUMN_DATE, getCreateDateStr());
        values.put(TSQLConst.COLUMN_KIND, kind.ordinal());
        values.put(TSQLConst.COLUMN_RESOURCE_PATH, resourcePath);
        values.put(TSQLConst.COLUMN_TEXT, text);
        return values;
    }
}
