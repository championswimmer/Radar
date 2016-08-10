package airtel.comviva.mahindra.radar.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import airtel.comviva.mahindra.phonytale.services.SmsSendService;

/**
 * Created by championswimmer on 9/8/16.
 */
public class RadarSmsSendService extends SmsSendService {

    @Override
    public void onSmsSent(String recipient, String message, int sendTime) {

    }

    @Override
    public void onSmsDelivered(String recipient, String message, int sendTime) {

    }
}
