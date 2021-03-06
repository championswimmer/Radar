package airtel.comviva.mahindra.radar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

//        USSDSender.sendUSSD("*121#", this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERM_REQ_CODE) {
            int results = 0;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    Log.d(TAG, "onRequestPermissionsResult: denied = " + permissions[i]);
                    results++;
                }
            }

            Log.d(TAG, "onRequestPermissionsResult: results = " + results);

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
                ad.create().show();

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case (R.id.action_dbdump):
                {
                    Db2JsonDumpTask jsonDumpTask = new Db2JsonDumpTask(this);
                    jsonDumpTask.execute();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
