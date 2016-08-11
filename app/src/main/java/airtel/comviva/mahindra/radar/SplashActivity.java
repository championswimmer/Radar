package airtel.comviva.mahindra.radar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * Created by championswimmer on 11/8/16.
 */
public class SplashActivity extends Activity {

    public static final String TAG = "Splash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri = Uri.parse("radar://sms/send?to=121&message=help me here&interval=1");
        Log.d(TAG, "onCreate:getQuery " + uri.getQuery());
        Log.d(TAG, "onCreate:getQueryParameter " + uri.getQueryParameter("to"));
        Log.d(TAG, "onCreate:getQueryParameter " + uri.getQueryParameter("message"));
        Log.d(TAG, "onCreate:getHost " + uri.getHost());
        Log.d(TAG, "onCreate:getPath " + uri.getPath());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 1500);


    }
}
