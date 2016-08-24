package airtel.comviva.mahindra.radar.services;

import android.util.Log;

import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableUSSDReport;
import in.championswimmer.phonytale.services.USSDReceiveService;

/**
 * Created by championswimmer on 24/08/16.
 */
public class RadarUSSDReceiveService extends USSDReceiveService {
    public static final String TAG = "RdrUssdRcv";

    @Override
    public void onUSSDReceived(String message) {
        Log.d(TAG, "onUSSDReceived: ");
        TableUSSDReport.addResponse((new DbManager(this)).getWritableDatabase(), message);
    }
}
