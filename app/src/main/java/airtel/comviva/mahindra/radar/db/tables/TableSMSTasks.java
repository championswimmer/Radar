package airtel.comviva.mahindra.radar.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import airtel.comviva.mahindra.radar.models.SMSTask;

import static airtel.comviva.mahindra.radar.db.tables.DbConsts.*;

/**
 * Created by championswimmer on 10/8/16.
 */
public class TableSMSTasks {
    public static final String TAG = "SmsTaskTable";

    public static final String TABLE_NAME = "smstasks";

    public interface Columns {
        String ID = "id";
        String TYPE = "type";
        String INTERVAL = "interval";
        String RECIPIENT = "recipient";
        String MESSAGE = "message";
    }

    public static String[] FULL_PROJECTION = {
            Columns.ID, Columns.TYPE, Columns.INTERVAL, Columns.RECIPIENT, Columns.MESSAGE
    };

    public static final String CMD_CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + SPACE + LBR
            + Columns.ID + TYPE_INTEGER + TYPE_PK_AI + COMMA
            + Columns.TYPE + TYPE_TEXT + COMMA
            + Columns.INTERVAL + TYPE_INTEGER + COMMA
            + Columns.MESSAGE + TYPE_TEXT + COMMA
            + Columns.RECIPIENT + TYPE_TEXT
            + RBR + SEMICOLON;

    public static long addTask (SQLiteDatabase db, int interval, String recipient, String message) {
        Log.d(TAG, "addTask: ");
        ContentValues cv = new ContentValues();
        cv.put(Columns.TYPE, "send");
        cv.put(Columns.INTERVAL, interval);
        cv.put(Columns.RECIPIENT, recipient);
        cv.put(Columns.MESSAGE, message);

        long id =  db.insert(
                TABLE_NAME,
                null,
                cv
        );
        return id;
    }

    public static void deleteTask (SQLiteDatabase db, int id) {
        Log.d(TAG, "deleteTask: ");
        db.delete(TABLE_NAME,
                Columns.ID + " = ?",
                new String[]{String.valueOf(id)});
    }



    public static ArrayList<SMSTask> getAllTasks (SQLiteDatabase db) {
        Log.d(TAG, "getAllTasks: ");

        ArrayList<SMSTask> smsTasks = new ArrayList<>();

        Cursor c = db.query(
                TABLE_NAME,
                FULL_PROJECTION,
                null,
                null,
                null,
                null,
                null
        );

        while (c.moveToNext()) {
            smsTasks.add(new SMSTask(
                    c.getInt(c.getColumnIndexOrThrow(Columns.INTERVAL)),
                    c.getString(c.getColumnIndexOrThrow(Columns.RECIPIENT)),
                    c.getString(c.getColumnIndexOrThrow(Columns.MESSAGE)),
                    c.getInt(c.getColumnIndexOrThrow(Columns.ID))
            ));
        }
        c.close();

        return smsTasks;
    }

}
