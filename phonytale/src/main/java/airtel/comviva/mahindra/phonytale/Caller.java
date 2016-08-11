package airtel.comviva.mahindra.phonytale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by championswimmer on 11/08/16.
 */
public class Caller {

    public static void makeCall(String phonenumber, Context ctx) {
        Intent dialIntent = new Intent();
        dialIntent.setAction(Intent.ACTION_CALL);
        dialIntent.setData(Uri.parse("tel:"+phonenumber));
        ctx.startActivity(dialIntent);
    }

}
