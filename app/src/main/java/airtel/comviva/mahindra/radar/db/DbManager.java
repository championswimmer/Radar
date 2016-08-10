package airtel.comviva.mahindra.radar.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import airtel.comviva.mahindra.radar.db.tables.TableSMSReport;
import airtel.comviva.mahindra.radar.db.tables.TableSMSTasks;

/**
 * Created by championswimmer on 10/8/16.
 */
public class DbManager  extends SQLiteOpenHelper{

    public static final int DB_VER = 1;
    public static final String DB_NAME = "Radar.db";


    public DbManager(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public DbManager(Context context, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        super(context, DB_NAME, factory, DB_VER, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TableSMSTasks.CMD_CREATE_TABLE);
        db.execSQL(TableSMSReport.CMD_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
