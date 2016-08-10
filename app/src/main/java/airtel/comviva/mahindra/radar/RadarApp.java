package airtel.comviva.mahindra.radar;

import android.app.Application;

import airtel.comviva.mahindra.phonytale.SmsSender;
import airtel.comviva.mahindra.radar.services.RadarSmsSendService;

/**
 * Created by championswimmer on 10/8/16.
 */
public class RadarApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SmsSender.setSmsSendObserverClass(RadarSmsSendService.class);

    }
}
