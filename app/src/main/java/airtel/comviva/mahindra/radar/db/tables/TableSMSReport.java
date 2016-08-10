package airtel.comviva.mahindra.radar.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import airtel.comviva.mahindra.radar.models.SMSReportItem;

import static airtel.comviva.mahindra.radar.db.tables.DbConsts.*;

/**
 * Created by championswimmer on 10/8/16.
 */
public class TableSMSReport {

    public static final String TABLE_NAME = "smsreports";

    public interface Columns {
        String ID = "id";
        String STATUS = "status";
        String RECIPIENT = "recipient";
        String MESSAGE = "message";
    }

    public static String[] FULL_PROJECTION = {
            Columns.ID, Columns.STATUS, Columns.RECIPIENT, Columns.MESSAGE
    };

    public static final String CMD_CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + SPACE + LBR
                    + Columns.ID + TYPE_INTEGER + TYPE_PK + COMMA
                    + Columns.STATUS + TYPE_INTEGER + COMMA
                    + Columns.MESSAGE + TYPE_TEXT + COMMA
                    + Columns.RECIPIENT + TYPE_TEXT
                    + RBR + SEMICOLON;

    public static int addNew(SQLiteDatabase db, int smsId, String recipient, String message, int status) {
        ContentValues cv = new ContentValues();
        cv.put(Columns.ID, smsId);
        cv.put(Columns.STATUS, status);
        cv.put(Columns.RECIPIENT, recipient);
        cv.put(Columns.MESSAGE, message);

        return (int) db.insert(TABLE_NAME, null, cv);

    }

    public static void updateStatus(SQLiteDatabase db, int status, int id) {
        ContentValues cv = new ContentValues();
        cv.put(Columns.STATUS, status);
        db.update(TABLE_NAME, cv, Columns.ID + " = ?", new String[]{String.valueOf(id)});
    }

    public static ArrayList<SMSReportItem> getAllReports(SQLiteDatabase db) {
        Cursor c = db.query(TABLE_NAME,
                FULL_PROJECTION, null, null, null, null, Columns.ID + " DESC");

        ArrayList<SMSReportItem> smsReports = new ArrayList<>();

        while (c.moveToNext()) {
            smsReports.add(new SMSReportItem(
                    c.getString(c.getColumnIndexOrThrow(Columns.RECIPIENT)),
                    c.getString(c.getColumnIndexOrThrow(Columns.MESSAGE)),
                    c.getInt(c.getColumnIndexOrThrow(Columns.ID)),
                    c.getInt(c.getColumnIndexOrThrow(Columns.STATUS))
            ));
        }

        return smsReports;
    }

}
