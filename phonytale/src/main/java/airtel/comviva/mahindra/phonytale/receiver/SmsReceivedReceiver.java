package airtel.comviva.mahindra.phonytale.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.util.Log;

/**
 * Created by championswimmer on 9/8/16.
 */
public class SmsReceivedReceiver extends BroadcastReceiver {

    public static final String TAG = "SmsReceived";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            Log.d(TAG, "onReceive: SMS Received");
        }
    }
}
