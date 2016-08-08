package airtel.comviva.mahindra.phonytale;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

/**
 * Created by championswimmer on 8/8/16.
 */
public class SmsSender {

    private static SmsManager sManager;

    public static final String ACTION_SMS_SENT = "airtel.comviva.mahindra.phonytale.SmsSender.ACTION_SMS_SENT";
    public static final String ACTION_SMS_DELIVERED = "airtel.comviva.mahindra.phonytale.SmsSender.ACTION_SMS_DELIVERED";

    public static final String EXTRA_RECEPIENT = "recepient";
    public static final String EXTRA_MSG_CODE = "msg_code";

    public static void initialise () {
        if (sManager == null) {
            sManager = SmsManager.getDefault();
        }
    }


    /**
     * Method to send sms (without observing when it will get sent or delivered)
     *
     * @param destination Phone number of recepient
     * @param message Text to send
     */
    public static void  sendSms (String destination, String message) {
        initialise();

        sManager.sendTextMessage(
                destination,
                null,
                message,
                null,
                null
        );
    }

    /**
     * Method to send SMS if you want to observe for when the sms gets send and delivered
     *
     * @param destination Phone number of recepient
     * @param message Text to send
     * @param serviceClass The service to which an intent will be sent when message is sent and delivered
     * @param ctx Context using which the target service will be started
     */
    public static void  sendSms (String destination, String message, Class<? extends Service> serviceClass, Context ctx) {
        initialise();

        int reqCode = (int) System.currentTimeMillis();

        Intent sentIntent = new Intent(ctx, serviceClass);
        sentIntent.setAction(ACTION_SMS_SENT);
        sentIntent.putExtra(EXTRA_RECEPIENT, destination);
        sentIntent.putExtra(EXTRA_MSG_CODE, reqCode);
        Intent deliveredIntent = new Intent(ctx, serviceClass);
        deliveredIntent.setAction(ACTION_SMS_DELIVERED);
        deliveredIntent.putExtra(EXTRA_RECEPIENT, destination);
        deliveredIntent.putExtra(EXTRA_MSG_CODE, reqCode);


        sManager.sendTextMessage(
                destination,
                null,
                message,
                PendingIntent.getService(ctx, reqCode, sentIntent, 0),
                PendingIntent.getService(ctx, reqCode, deliveredIntent, 0)
        );
    }


}
