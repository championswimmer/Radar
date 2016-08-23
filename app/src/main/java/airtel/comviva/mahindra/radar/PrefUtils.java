package airtel.comviva.mahindra.radar;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by championswimmer on 24/8/16.
 */
public class PrefUtils {

    public static void setLastSmsId (Context ctx, int smsId) {
        SharedPreferences sp = ctx.getSharedPreferences(Constants.PREFS_SMS, Context.MODE_PRIVATE);
        sp.edit().putInt(Constants.PrefKeys.LAST_SMS_ID, smsId).apply();
    }

    public static int getLastSmsId(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(Constants.PREFS_SMS, Context.MODE_PRIVATE);
        return sp.getInt(Constants.PrefKeys.LAST_SMS_ID, 0);
    }
}
