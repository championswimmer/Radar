package airtel.comviva.mahindra.radar.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import airtel.comviva.mahindra.radar.db.tables.TableCallTasks;
import airtel.comviva.mahindra.radar.db.tables.TableSMSReport;
import airtel.comviva.mahindra.radar.db.tables.TableSMSTasks;
import airtel.comviva.mahindra.radar.db.tables.TableUSSDReport;
import airtel.comviva.mahindra.radar.db.tables.TableUSSDTasks;

/**
 * Created by championswimmer on 10/8/16.
 */
public class DbManager  extends SQLiteOpenHelper{

    public static final String TAG = "DbManager";

    public static final int DB_VER = 1;
    public static final String DB_NAME = "Radar.db";

    private static SQLiteDatabase writeableDb;
    private static SQLiteDatabase readableDb;

    @Override
    public SQLiteDatabase getWritableDatabase() {
        if (writeableDb == null) {
            writeableDb = super.getWritableDatabase();
        }

        return writeableDb;
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        if (readableDb == null) {
            readableDb = super.getReadableDatabase();
        }

        return readableDb;
    }



    public DbManager(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public DbManager(Context context, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        super(context, DB_NAME, factory, DB_VER, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        execSQL(TableSMSTasks.CMD_CREATE_TABLE, db);
        execSQL(TableSMSReport.CMD_CREATE_TABLE, db);

        execSQL(TableCallTasks.CMD_CREATE_TABLE, db);

        execSQL(TableUSSDTasks.CMD_CREATE_TABLE, db);
        execSQL(TableUSSDReport.CMD_CREATE_TABLE, db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void execSQL(String cmd, SQLiteDatabase db){
        Log.d(TAG, "execSQL: " + cmd);
        db.execSQL(cmd);
    }
}
