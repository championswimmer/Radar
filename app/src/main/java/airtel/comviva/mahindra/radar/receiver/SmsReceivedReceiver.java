package airtel.comviva.mahindra.radar.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;

/**
 * Created by championswimmer on 9/8/16.
 */
public class SmsReceivedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {

        }
    }
}
