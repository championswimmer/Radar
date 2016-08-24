package airtel.comviva.mahindra.radar.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import in.championswimmer.phonytale.services.USSDSendService;
import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableUSSDReport;

/**
 * Created by championswimmer on 16/8/16.
 */
public class RadarUSSDSendService extends USSDSendService {

    SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = (new DbManager(this)).getWritableDatabase();
    }


    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    public void onUSSDSend(String ussdCode) {
        Long timestamp = System.currentTimeMillis();
        int ussdId = (int) (timestamp >> 8);

        TableUSSDReport.addNew(db, ussdId, ussdCode, timestamp);

        Intent i = new Intent(this, UssdSlaInvalidatorService.class);
        PendingIntent pi = PendingIntent.getService(this, 88, i, 0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setExact(AlarmManager.RTC, (System.currentTimeMillis() + (3 * 60 * 1000)), pi);
        } else {
            am.set(AlarmManager.RTC, (System.currentTimeMillis() + (3 * 60 * 1000)), pi);
        }
    }
}
