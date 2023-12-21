import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
  private static final String TABLE_NAME = "table_histories";
  public static final String COL_ID = "ID";
  public static final String COL_NAME = "USERNAME";
  public static final String COL_CONSULT = "CONSULTATION";
  private static final String CREATE_TAB = "CREATE TABLE " + TABLE_NAME +
" (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
+ COL_NAME + " TEXT NOT NULL, "
+ COL_CONSULT + " TEXT NOT NULL);";

  public ChapitreBaseSQLite(Context context) {
    super(context, "mydatabase.db", null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TAB);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
  }
} 