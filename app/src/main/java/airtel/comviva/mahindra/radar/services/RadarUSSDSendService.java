package airtel.comviva.mahindra.radar.services;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import in.championswimmer.phonytale.services.USSDSendService;
import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableUSSDReport;

/**
 * Created by championswimmer on 16/8/16.
 */
public class RadarUSSDSendService extends USSDSendService {

    SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = (new DbManager(this)).getWritableDatabase();
    }


    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    public void onUSSDSend(String ussdCode) {
        Long timestamp = System.currentTimeMillis();
        int ussdId = (int) (timestamp >> 8);

        TableUSSDReport.addNew(db, ussdId, ussdCode, timestamp);
    }
}
