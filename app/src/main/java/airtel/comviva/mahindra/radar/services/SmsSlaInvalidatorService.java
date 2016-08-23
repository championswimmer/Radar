package airtel.comviva.mahindra.radar.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableSMSReport;
import airtel.comviva.mahindra.radar.models.SMSReportItem;
import in.championswimmer.phonytale.SmsSender;

/**
 * Created by championswimmer on 23/8/16.
 */
public class SmsSlaInvalidatorService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {
            int msgId = intent.getIntExtra(SmsSender.EXTRA_MSG_CODE, 0);
            TableSMSReport.updateStatus((new DbManager(this)).getWritableDatabase(),
                    SMSReportItem.STATUS_RESP_FAILED,
                    msgId,
                    null);
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
