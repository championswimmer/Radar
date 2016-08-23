package airtel.comviva.mahindra.radar.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.support.annotation.Nullable;

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
        String SEND_TIMESTAMP = "send_timestamp";
        String SENT_TIMESTAMP = "sent_timestamp";
        String DELIVERED_TIMESTAMP = "delivered_timestamp";
        String RECEIVED_TIMESTAMP = "received_timestamp";
    }

    public static String[] FULL_PROJECTION = {
            Columns.ID, Columns.STATUS, Columns.RECIPIENT, Columns.MESSAGE,
            Columns.SEND_TIMESTAMP, Columns.SENT_TIMESTAMP, Columns.DELIVERED_TIMESTAMP,
            Columns.RECEIVED_TIMESTAMP
    };

    public static final String CMD_CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + SPACE + LBR
                    + Columns.ID + TYPE_INTEGER + TYPE_PK + COMMA
                    + Columns.STATUS + TYPE_INTEGER + COMMA
                    + Columns.MESSAGE + TYPE_TEXT + COMMA
                    + Columns.SEND_TIMESTAMP + TYPE_INTEGER + COMMA
                    + Columns.SENT_TIMESTAMP + TYPE_INTEGER + COMMA
                    + Columns.DELIVERED_TIMESTAMP + TYPE_INTEGER + COMMA
                    + Columns.RECEIVED_TIMESTAMP + TYPE_INTEGER + COMMA
                    + Columns.RECIPIENT + TYPE_TEXT
                    + RBR + SEMICOLON;

    public static int addNew(SQLiteDatabase db, int smsId, String recipient, String message, int status) {
        ContentValues cv = new ContentValues();
        cv.put(Columns.ID, smsId);
        cv.put(Columns.STATUS, status);
        cv.put(Columns.RECIPIENT, recipient);
        cv.put(Columns.MESSAGE, message);
        cv.put(Columns.SEND_TIMESTAMP, System.currentTimeMillis());

        return (int) db.insert(TABLE_NAME, null, cv);

    }

    public static void updateStatus(SQLiteDatabase db, int status, int id, @Nullable String respMsg) {
        ContentValues cv = new ContentValues();
        cv.put(Columns.STATUS, status);
        switch (status) {
            case SMSReportItem.STATUS_SENT:
                cv.put(Columns.SENT_TIMESTAMP, System.currentTimeMillis());
                break;
            case SMSReportItem.STATUS_DELIVERED:
                cv.put(Columns.DELIVERED_TIMESTAMP, System.currentTimeMillis());
                break;
            case SMSReportItem.STATUS_RESP_RECVD:
                cv.put(Columns.RECEIVED_TIMESTAMP, System.currentTimeMillis());
                break;
        }
        db.update(TABLE_NAME, cv, Columns.ID + " = ?", new String[]{String.valueOf(id)});
    }

    public static ArrayList<SMSReportItem> getAllByStatus(SQLiteDatabase db, int status) {
        Cursor c = db.query(TABLE_NAME,
                FULL_PROJECTION, Columns.STATUS + " = ?", new String[]{String.valueOf(status)},
                null, null, Columns.ID + " DESC");
        ArrayList<SMSReportItem> smsReports = new ArrayList<>();

        while (c.moveToNext()) {
            smsReports.add(new SMSReportItem(
                    c.getString(c.getColumnIndexOrThrow(Columns.RECIPIENT)),
                    c.getString(c.getColumnIndexOrThrow(Columns.MESSAGE)),
                    c.getInt(c.getColumnIndexOrThrow(Columns.ID)),
                    c.getInt(c.getColumnIndexOrThrow(Columns.STATUS)),
                    c.getLong(c.getColumnIndexOrThrow(Columns.SEND_TIMESTAMP)),
                    c.getLong(c.getColumnIndexOrThrow(Columns.SENT_TIMESTAMP)),
                    c.getLong(c.getColumnIndexOrThrow(Columns.DELIVERED_TIMESTAMP)),
                    c.getLong(c.getColumnIndexOrThrow(Columns.RECEIVED_TIMESTAMP))

            ));
        }

        return smsReports;
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
                    c.getInt(c.getColumnIndexOrThrow(Columns.STATUS)),
                    c.getLong(c.getColumnIndexOrThrow(Columns.SEND_TIMESTAMP)),
                    c.getLong(c.getColumnIndexOrThrow(Columns.SENT_TIMESTAMP)),
                    c.getLong(c.getColumnIndexOrThrow(Columns.DELIVERED_TIMESTAMP)),
                    c.getLong(c.getColumnIndexOrThrow(Columns.RECEIVED_TIMESTAMP))

            ));
        }

        return smsReports;
    }

}
