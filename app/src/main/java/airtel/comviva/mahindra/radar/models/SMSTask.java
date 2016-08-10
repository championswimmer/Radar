package airtel.comviva.mahindra.radar.models;

/**
 * Created by championswimmer on 10/8/16.
 */
public class SMSTask {
    int interval;
    String recipient;

    public SMSTask(int interval, String recipient) {
        this.interval = interval;
        this.recipient = recipient;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
