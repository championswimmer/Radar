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
            BROADCAST_SMS,

            CALL_PHONE,
            READ_CALL_LOG,
            PROCESS_OUTGOING_CALLS,
            READ_PHONE_STATE
    };
}
