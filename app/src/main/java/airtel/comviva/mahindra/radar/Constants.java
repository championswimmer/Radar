package airtel.comviva.mahindra.radar;

import android.Manifest;
import static android.Manifest.permission.*;

/**
 * Created by championswimmer on 10/8/16.
 */
public class Constants {

    public static String[] requiredPermissions = {
            READ_SMS,
            RECEIVE_SMS,
            SEND_SMS,

            CALL_PHONE,
            READ_CALL_LOG,
            READ_PHONE_STATE,
            PROCESS_OUTGOING_CALLS
    };

    public static final String PREFS_SMS = "sms";


    public interface PrefKeys {
        String LAST_SMS_ID = "last_sms_id";
    }
}
