package airtel.comviva.mahindra.radar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableSMSReport;
import airtel.comviva.mahindra.radar.db.tables.TableUSSDReport;
import airtel.comviva.mahindra.radar.models.SMSReportItem;
import airtel.comviva.mahindra.radar.models.USSDReportItem;

/**
 * Created by championswimmer on 25/8/16.
 */
public class Db2JsonDumpTask extends AsyncTask<Void, Void, Void> {

    public static final String TAG = "Db2Json";

    private SQLiteDatabase db;
    private Context ctx;

    public Db2JsonDumpTask(Context ctx) {
        this.db = ((new DbManager(ctx)).getReadableDatabase());
        this.ctx = ctx;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Gson gson = new Gson();
        ArrayList<SMSReportItem> smsReports = TableSMSReport.getAllReports(db);
        ArrayList<USSDReportItem> ussdReports = TableUSSDReport.getAllReports(db);

        String smsReportJson = gson.toJson(smsReports);
        String ussdReportJson = gson.toJson(ussdReports);

        try {
            writeToFile(smsReportJson, "sms_reports.json");
            writeToFile(ussdReportJson, "ussd_reports.json");
        } catch (IOException ioe) {
            Log.e(TAG, "doInBackground: ", ioe);
        }

        return null;
    }


    private void writeToFile(String json, String fileName) throws IOException {
        File fileDir = null;
        File[] fileDirs = ContextCompat.getExternalFilesDirs(ctx, null);
        if (fileDirs.length > 1) {
            fileDir = fileDirs[1];
        } else {
            fileDir = fileDirs[0];
        }
        Log.d(TAG, "writeToFile: " + fileDir.getAbsolutePath() + "/" + fileName);
        File saveFile = new File(fileDir, fileName);
        saveFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(saveFile);
        fos.write(json.getBytes());
        fos.close();

    }
}
