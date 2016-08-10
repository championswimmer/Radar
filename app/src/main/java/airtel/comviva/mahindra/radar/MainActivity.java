package airtel.comviva.mahindra.radar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import airtel.comviva.mahindra.phonytale.SmsSender;
import airtel.comviva.mahindra.radar.activities.ConfigureTaskActivity;
import airtel.comviva.mahindra.radar.activities.ReportActivity;
import airtel.comviva.mahindra.radar.activities.ShowTaskActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Radar";

    private static int PERM_REQ_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SmsSender.initialise();
//
//        //SmsSender.sendSms("+919868058844", "Yo Hi !");
//
//        startService(new Intent(this, MyService.class));

        int permissions = 0;
        for (String permission : Constants.requiredPermissions) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED ) {
                Log.d(TAG, "onCreate: " + permission + " DENIED");
                permissions++;
            } else {
                Log.d(TAG, "onCreate: " + permission + " GRANTED");

            }
        }
        if (permissions != 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(Constants.requiredPermissions, PERM_REQ_CODE);
            }
        }

        ListView mainList = (ListView) findViewById(R.id.main_list);
        mainList.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new String[] {
                        "Configure Tasks",
                        "Show Tasks",
                        "Show Reports"
                }
        ));

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position) {
                    case 2:
                        i = new Intent(MainActivity.this, ReportActivity.class);
                        break;
                    case 1:
                        i = new Intent(MainActivity.this, ShowTaskActivity.class);
                        break;
                    case 0: default:
                        i = new Intent(MainActivity.this, ConfigureTaskActivity.class);
                        break;
                }

                startActivity(i);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERM_REQ_CODE) {
            int results = 0;
            for (int grant : grantResults) {
                if (grant == PackageManager.PERMISSION_DENIED) {
                    results++;
                }
            }

            if (results != 0) {
                AlertDialog.Builder ad = new AlertDialog.Builder(this)
                        .setMessage("Permissions have not been granted, cannot use this app")
                        .setTitle("Permission Error")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

            }
        }

    }
}
