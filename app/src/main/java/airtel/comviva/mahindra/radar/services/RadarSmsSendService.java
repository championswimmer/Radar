package airtel.comviva.mahindra.radar.services;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;

import airtel.comviva.mahindra.phonytale.services.SmsSendService;
import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableSMSReport;
import airtel.comviva.mahindra.radar.models.SMSReportItem;

/**
 * Created by championswimmer on 9/8/16.
 */
public class RadarSmsSendService extends SmsSendService {

    SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = (new DbManager(this)).getWritableDatabase();
    }

    @Override
    public void onSmsSending(String recipient, String message, int smsId) {
        TableSMSReport.addNew(db, smsId, recipient, message, SMSReportItem.STATUS_SENDING);
    }

    @Override
    public void onSmsSent(String recipient, String message, int smsId) {
        TableSMSReport.updateStatus(db, SMSReportItem.STATUS_SENT, smsId);
    }

    @Override
    public void onSmsDelivered(String recipient, String message, int smsId) {
        TableSMSReport.updateStatus(db, SMSReportItem.STATUS_DELIVERED, smsId);

    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
