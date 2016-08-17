package airtel.comviva.mahindra.radar.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import airtel.comviva.mahindra.radar.models.SMSReportItem;
import airtel.comviva.mahindra.radar.models.USSDReportItem;

import static airtel.comviva.mahindra.radar.db.tables.DbConsts.COMMA;
import static airtel.comviva.mahindra.radar.db.tables.DbConsts.CREATE_TABLE_IF_NOT_EXISTS;
import static airtel.comviva.mahindra.radar.db.tables.DbConsts.LBR;
import static airtel.comviva.mahindra.radar.db.tables.DbConsts.RBR;
import static airtel.comviva.mahindra.radar.db.tables.DbConsts.SEMICOLON;
import static airtel.comviva.mahindra.radar.db.tables.DbConsts.SPACE;
import static airtel.comviva.mahindra.radar.db.tables.DbConsts.TYPE_INTEGER;
import static airtel.comviva.mahindra.radar.db.tables.DbConsts.TYPE_PK;
import static airtel.comviva.mahindra.radar.db.tables.DbConsts.TYPE_TEXT;

/**
 * Created by championswimmer on 10/8/16.
 */
public class TableUSSDReport {

    public static final String TABLE_NAME = "ussdreports";

    public interface Columns {
        String ID = "id";
        String RECIPIENT = "recipient";
        String TIMESTAMP = "timestamp";
    }

    public static String[] FULL_PROJECTION = {
            Columns.ID, Columns.RECIPIENT, Columns.TIMESTAMP
    };

    public static final String CMD_CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + SPACE + LBR
                    + Columns.ID + TYPE_INTEGER + TYPE_PK + COMMA
                    + Columns.RECIPIENT + TYPE_TEXT
                    + Columns.TIMESTAMP + TYPE_INTEGER
                    + RBR + SEMICOLON;

    public static int addNew(SQLiteDatabase db, int ussdId, String recipient, long timestamp) {
        ContentValues cv = new ContentValues();
        cv.put(Columns.ID, ussdId);
        cv.put(Columns.RECIPIENT, recipient);
        cv.put(Columns.TIMESTAMP, timestamp);

        return (int) db.insert(TABLE_NAME, null, cv);

    }

//    public static void updateStatus(SQLiteDatabase db, int status, int id) {
//        ContentValues cv = new ContentValues();
//        cv.put(Columns.STATUS, status);
//        db.update(TABLE_NAME, cv, Columns.ID + " = ?", new String[]{String.valueOf(id)});
//    }

    public static ArrayList<USSDReportItem> getAllReports(SQLiteDatabase db) {
        Cursor c = db.query(TABLE_NAME,
                FULL_PROJECTION, null, null, null, null, Columns.ID + " DESC");

        ArrayList<USSDReportItem> ussdReports = new ArrayList<>();

        while (c.moveToNext()) {
            ussdReports.add(new USSDReportItem(
                    c.getString(c.getColumnIndexOrThrow(Columns.RECIPIENT)),
                    c.getInt(c.getColumnIndexOrThrow(Columns.TIMESTAMP))
            ));
        }

        return ussdReports;
    }

}
