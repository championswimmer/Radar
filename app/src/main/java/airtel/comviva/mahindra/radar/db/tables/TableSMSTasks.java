package airtel.comviva.mahindra.radar.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import airtel.comviva.mahindra.radar.models.SMSTask;

import static airtel.comviva.mahindra.radar.db.tables.DbConsts.*;

/**
 * Created by championswimmer on 10/8/16.
 */
public class TableSMSTasks {

    public static final String TABLE_NAME = "smstasks";

    public interface Columns {
        String ID = "id";
        String TYPE = "type";
        String INTERVAL = "interval";
        String RECIPIENT = "recipient";
    }

    public static String[] FULL_PROJECTION = {
            Columns.ID, Columns.TYPE, Columns.INTERVAL, Columns.RECIPIENT
    };

    public static final String CMD_CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + SPACE + LBR
            + Columns.ID + TYPE_INTEGER + TYPE_PK_AI + COMMA
            + Columns.TYPE + TYPE_TEXT + COMMA
            + Columns.INTERVAL + TYPE_INTEGER + COMMA
            + Columns.RECIPIENT + TYPE_TEXT
            + RBR + SEMICOLON;

    public static long addTask (SQLiteDatabase db, int interval, String recipient) {

        ContentValues cv = new ContentValues();
        cv.put(Columns.TYPE, "send");
        cv.put(Columns.INTERVAL, interval);
        cv.put(Columns.RECIPIENT, recipient);

        return db.insert(
                TABLE_NAME,
                null,
                cv
        );
    }

    public static ArrayList<SMSTask> getAllTasks (SQLiteDatabase db) {

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
                    c.getString(c.getColumnIndexOrThrow(Columns.RECIPIENT))
            ));
        }

        return smsTasks;
    }

}
