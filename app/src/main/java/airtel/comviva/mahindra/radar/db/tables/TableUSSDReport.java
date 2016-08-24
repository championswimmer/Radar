package airtel.comviva.mahindra.radar.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import airtel.comviva.mahindra.radar.db.DbUtils;
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

    public static final int STATUS_SENT = 0;
    public static final int STATUS_SLA_PASSED = 1;
    public static final int STATUS_SLA_FAILED = 2;

    public interface Columns {
        String ID = "id";
        String RECIPIENT = "recipient";
        String TIMESTAMP = "timestamp";
        String STATUS = "status";
        String COUNT_RESP = "count_resp";
        String RECEIVE_TIMESTAMPS = "received_timestamps";
        String RECEIVED_USSD_MSGS = "received_ussd_msgs";
    }

    public static String[] FULL_PROJECTION = {
            Columns.ID, Columns.RECIPIENT, Columns.TIMESTAMP,
            Columns.STATUS, Columns.COUNT_RESP,
            Columns.RECEIVE_TIMESTAMPS, Columns.RECEIVED_USSD_MSGS
    };

    public static final String CMD_CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + SPACE + LBR
                    + Columns.ID + TYPE_INTEGER + TYPE_PK + COMMA
                    + Columns.RECIPIENT + TYPE_TEXT + COMMA
                    + Columns.STATUS + TYPE_INTEGER + COMMA
                    + Columns.COUNT_RESP + TYPE_INTEGER + COMMA
                    + Columns.RECEIVE_TIMESTAMPS + TYPE_TEXT + COMMA
                    + Columns.RECEIVED_USSD_MSGS + TYPE_TEXT + COMMA
                    + Columns.TIMESTAMP + TYPE_INTEGER
                    + RBR + SEMICOLON;

    public static int addNew(SQLiteDatabase db, int ussdId, String recipient,  Long timestamp) {
        ContentValues cv = new ContentValues();
        cv.put(Columns.ID, ussdId);
        cv.put(Columns.RECIPIENT, recipient);
        cv.put(Columns.TIMESTAMP, timestamp);
        cv.put(Columns.STATUS, STATUS_SENT);

        return (int) db.insert(TABLE_NAME, null, cv);

    }

    public static int failSla(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(Columns.STATUS, STATUS_SLA_FAILED);
        return db.update(TABLE_NAME, cv, Columns.STATUS + " = ?",
                new String[]{
                        String.valueOf(STATUS_SENT)
                });
    }

    public static int addResponse(SQLiteDatabase db, String message) {
        USSDReportItem latestReport = getLatestSent(db);
        if (latestReport == null) {
            return 0;
        }
        ArrayList<String> rcvdMsgs = new ArrayList<>(latestReport.getRecvdMsgs()) ;
        ArrayList<Long> rcvdTimes = new ArrayList<>(latestReport.getRecvdTimes()) ;

        rcvdMsgs.add(message);
        rcvdTimes.add((System.currentTimeMillis()));

        ContentValues cv = new ContentValues();
        cv.put(Columns.RECEIVE_TIMESTAMPS, DbUtils.convertLongListToString(rcvdTimes));
        cv.put(Columns.RECEIVED_USSD_MSGS, DbUtils.convertStrListToString(rcvdMsgs));
        cv.put(Columns.STATUS, STATUS_SLA_PASSED);

        return db.update(TABLE_NAME, cv, Columns.ID + " = ? AND " + Columns.STATUS + " = ?",
                new String[]{
                        String.valueOf(latestReport.getUssdId()),
                        String.valueOf(STATUS_SENT)
                });
    }

    public static USSDReportItem getLatestSent(SQLiteDatabase db) {
        Cursor c = db.query(TABLE_NAME, FULL_PROJECTION, Columns.STATUS + " = ?",
                new String[]{String.valueOf(STATUS_SENT)}, null, null, Columns.ID + " DESC", "1");
        USSDReportItem ussdReportItem = null;
        if (c != null && c.moveToFirst()) {
            ussdReportItem = new USSDReportItem(
                    c.getInt(c.getColumnIndexOrThrow(Columns.ID)),
                    c.getString(c.getColumnIndexOrThrow(Columns.RECIPIENT)),
                    c.getLong(c.getColumnIndexOrThrow(Columns.TIMESTAMP)),
                    c.getInt(c.getColumnIndexOrThrow(Columns.STATUS)),
                    c.getString(c.getColumnIndexOrThrow(Columns.RECEIVE_TIMESTAMPS)),
                    c.getString(c.getColumnIndexOrThrow(Columns.RECEIVED_USSD_MSGS))
            );
        }
        c.close();
        return ussdReportItem;
    }

    public static ArrayList<USSDReportItem> getAllReports(SQLiteDatabase db) {
        Cursor c = db.query(TABLE_NAME,
                FULL_PROJECTION, null, null, null, null, Columns.ID + " DESC");

        ArrayList<USSDReportItem> ussdReports = new ArrayList<>();

        while (c.moveToNext()) {
            ussdReports.add(new USSDReportItem(
                    c.getInt(c.getColumnIndexOrThrow(Columns.ID)),
                    c.getString(c.getColumnIndexOrThrow(Columns.RECIPIENT)),
                    c.getLong(c.getColumnIndexOrThrow(Columns.TIMESTAMP)),
                    c.getInt(c.getColumnIndexOrThrow(Columns.STATUS)),
                    c.getString(c.getColumnIndexOrThrow(Columns.RECEIVE_TIMESTAMPS)),
                    c.getString(c.getColumnIndexOrThrow(Columns.RECEIVED_USSD_MSGS))
            ));
        }
        c.close();
        return ussdReports;
    }

}
