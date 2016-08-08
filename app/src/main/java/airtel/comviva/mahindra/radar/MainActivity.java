package airtel.comviva.mahindra.radar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import airtel.comviva.mahindra.phonytale.SmsSender;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmsSender.initialise();

        //SmsSender.sendSms("+919868058844", "Yo Hi !");

        startService(new Intent(this, MyService.class));

    }


}
