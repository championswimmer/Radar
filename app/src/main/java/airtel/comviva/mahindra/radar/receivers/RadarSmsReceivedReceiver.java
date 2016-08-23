package airtel.comviva.mahindra.radar.receivers;

import android.content.Context;

import airtel.comviva.mahindra.radar.Constants;
import airtel.comviva.mahindra.radar.PrefUtils;
import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableSMSReport;
import airtel.comviva.mahindra.radar.models.SMSReportItem;
import in.championswimmer.phonytale.receiver.SmsReceivedReceiver;

/**
 * Created by championswimmer on 23/8/16.
 */
public class RadarSmsReceivedReceiver extends SmsReceivedReceiver {
    public static final String TAG = "SMSRecvd";

    @Override
    public void onSmsReceived(Context c, String sender, String msgBody) {

        TableSMSReport.updateStatus((new DbManager(c)).getWritableDatabase(),
                SMSReportItem.STATUS_RESP_RECVD,
                PrefUtils.getLastSmsId(c),
                msgBody,
                sender);
    }
}
