package airtel.comviva.mahindra.radar;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import airtel.comviva.mahindra.phonytale.SmsSender;

/**
 * Created by championswimmer on 8/8/16.
 */
public class MyService  extends Service {

    public static final String TAG = "MyService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand: ");

        if (intent == null) {
            return START_STICKY;
        }

        if (intent.getAction() == null) {
            SmsSender.sendSms("121", "Yo Hi !", MyService.class, this);
        } else {
            if (intent.getAction().equals(SmsSender.ACTION_SMS_DELIVERED)) {
                Log.d(TAG, "onStartCommand: delivered" + intent.getStringExtra(SmsSender.EXTRA_RECEPIENT));
            } else if (intent.getAction().equals(SmsSender.ACTION_SMS_SENT)) {
                Log.d(TAG, "onStartCommand: sent" + intent.getStringExtra(SmsSender.EXTRA_RECEPIENT));
            }
        }


        return START_STICKY;
    }
}
