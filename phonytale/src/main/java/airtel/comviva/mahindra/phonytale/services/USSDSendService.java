package airtel.comviva.mahindra.phonytale.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import airtel.comviva.mahindra.phonytale.PhonytaleUri;
import airtel.comviva.mahindra.phonytale.USSDSender;

public class USSDSendService extends Service {

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

            USSDSender.sendUSSD(
                    intent.getData().getQueryParameter(PhonytaleUri.QUERY_TO),
                    this
            );
        }


        return super.onStartCommand(intent, flags, startId);
    }
}
