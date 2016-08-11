package airtel.comviva.mahindra.phonytale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by championswimmer on 12/08/16.
 */
public class USSDSender {

    public static final String TAG = "USSDSender";

    public static final String ACTION_SEND_USSD = "airtel.comviva.mahindra.phonytale.ACTION_SEND_USSD";

    public static void sendUSSD (String ussdCode, Context ctx) {

        Uri ussdUri = Uri.parse("tel:" + Uri.encode(ussdCode));
        Intent ussdIntent = new Intent();
        ussdIntent.setAction(Intent.ACTION_CALL);
        ussdIntent.setData(ussdUri);
        ussdIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(ussdIntent);
    }

}
