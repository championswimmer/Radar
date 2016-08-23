package airtel.comviva.mahindra.radar.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import in.championswimmer.phonytale.SmsSender;
import in.championswimmer.phonytale.services.SmsSendService;
import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableSMSReport;
import airtel.comviva.mahindra.radar.models.SMSReportItem;

/**
 * Created by championswimmer on 9/8/16.
 */
public class RadarSmsSendService extends SmsSendService {

    public static final String TAG = "RadSMSSend";

    SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = (new DbManager(this)).getWritableDatabase();
    }

    @Override
    public void onSmsSending(String recipient, String message, int smsId, int interval) {
        Log.d(TAG, "onSmsSending: ");
        TableSMSReport.addNew(db, smsId, recipient, message, SMSReportItem.STATUS_SENDING);
        Intent i = new Intent(this, SmsSlaInvalidatorService.class);
        i.putExtra(SmsSender.EXTRA_MSG_CODE, smsId);
        PendingIntent pi = PendingIntent.getService(this, 99, i, 0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.d(TAG, "onSmsSending: " + pi.toString() + i.toString());
            am.setExact(AlarmManager.RTC,
                    (System.currentTimeMillis() + (interval * 60 * 1000)),
                    pi);
        } else {
            Log.d(TAG, "onSmsSending: " + pi.toString()  + i.toString());
            am.set(AlarmManager.RTC,
                    (System.currentTimeMillis() + (interval * 60 * 1000)),
                    pi);
        }

    }

    @Override
    public void onSmsSent(String recipient, String message, int smsId) {
        TableSMSReport.updateStatus(db, SMSReportItem.STATUS_SENT, smsId, null);
    }

    @Override
    public void onSmsDelivered(String recipient, String message, int smsId) {
        TableSMSReport.updateStatus(db, SMSReportItem.STATUS_DELIVERED, smsId, null);

    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
