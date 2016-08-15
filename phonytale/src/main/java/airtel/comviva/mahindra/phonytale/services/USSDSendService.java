package airtel.comviva.mahindra.phonytale.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import airtel.comviva.mahindra.phonytale.USSDSender;

public class USSDSendService extends Service {
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

        }


        return super.onStartCommand(intent, flags, startId);
    }
}
