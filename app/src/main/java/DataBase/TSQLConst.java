package DataBase;

public class TSQLConst {
    public static final String TABLE_NAME_MyProjectMessages = "_MyProjectMessages";

    public static final String COLUMN_ID = "mpm_ID";
    public static final String COLUMN_USER_GOOGLE_ID = "mpm_UserGoogleID";
    public static final String COLUMN_DATE = "mpm_Date";
    public static final String COLUMN_KIND = "mpm_Kind";
    public static final String COLUMN_RESOURCE_PATH = "mpm_ResourcePath";
    public static final String COLUMN_TEXT = "mpm_Text";

    public static final String[] ALL_COLUMN_MyProjectMessages = {COLUMN_ID,COLUMN_USER_GOOGLE_ID,
            COLUMN_DATE, COLUMN_KIND,COLUMN_RESOURCE_PATH,COLUMN_TEXT};

    public static final String CREATE_TABLE_MyProjectMessage =
            "CREATE TABLE " + TABLE_NAME_MyProjectMessages + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_USER_GOOGLE_ID + " TEXT," +
                    COLUMN_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    COLUMN_KIND + " INTEGER NOT NULL," +
                    COLUMN_RESOURCE_PATH + " TEXT," +
                    COLUMN_TEXT + " TEXT" +
                    ");";

    public static final String DROP_TABLE_MyProjectMessages =
            "DROP TABLE " + TABLE_NAME_MyProjectMessages;
}
