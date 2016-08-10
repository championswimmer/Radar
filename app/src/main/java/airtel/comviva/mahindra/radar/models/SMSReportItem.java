package airtel.comviva.mahindra.radar.models;

/**
 * Created by championswimmer on 11/8/16.
 */
public class SMSReportItem {

    public static final int STATUS_SENDING = 0;
    public static final int STATUS_SENT = 1;
    public static final int STATUS_DELIVERED = 2;


    String recipient;
    String message;
    int smsId;
    int status;

    public SMSReportItem(String recipient, String message, int smsId, int status) {
        this.recipient = recipient;
        this.message = message;
        this.smsId = smsId;
        this.status = status;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSmsId() {
        return smsId;
    }

    public void setSmsId(int smsId) {
        this.smsId = smsId;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusString() {
        switch (status) {
            case STATUS_DELIVERED:
                return "DELIVERED";
            case STATUS_SENT:
                return "SENT";
            case STATUS_SENDING:
                return "SENDING";
        }
        return "";
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
