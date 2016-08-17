package airtel.comviva.mahindra.radar;

import android.app.Application;

import in.championswimmer.phonytale.SmsSender;
import in.championswimmer.phonytale.USSDSender;
import airtel.comviva.mahindra.radar.services.RadarSmsSendService;
import airtel.comviva.mahindra.radar.services.RadarUSSDSendService;

/**
 * Created by championswimmer on 10/8/16.
 */
public class RadarApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SmsSender.setSmsSendObserverClass(RadarSmsSendService.class);
        USSDSender.setUssdSendServiceClass(RadarUSSDSendService.class);

    }
}
