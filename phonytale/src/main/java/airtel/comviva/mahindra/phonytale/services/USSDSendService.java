package airtel.comviva.mahindra.phonytale.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import airtel.comviva.mahindra.phonytale.PhonytaleUri;
import airtel.comviva.mahindra.phonytale.USSDSender;

public abstract class USSDSendService extends Service {

    public static final String TAG = "USSDSend";

    public USSDSendService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals(USSDSender.ACTION_SEND_USSD)) {
            Log.d(TAG, "onStartCommand: " + USSDSender.ACTION_SEND_USSD);

            String ussdCode = intent.getData().getQueryParameter(PhonytaleUri.QUERY_TO);

            USSDSender.sendUSSD(
                    ussdCode,
                    this
            );
            onUSSDSend(ussdCode);
        }


        return super.onStartCommand(intent, flags, startId);
    }

    public abstract void onUSSDSend(String ussdCode);
}
