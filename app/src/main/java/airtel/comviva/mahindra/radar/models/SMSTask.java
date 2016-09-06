package airtel.comviva.mahindra.radar.models;

/**
 * Created by championswimmer on 10/8/16.
 */
public class SMSTask {
    int interval;
    int timeout;
    int expectedResponse;
    String recipient;
    String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public SMSTask(int interval, String recipient, String message, int id) {
        this.interval = interval;
        this.recipient = recipient;
        this.message = message;
        this.id = id;
    }

    public SMSTask(int interval, String recipient, String message) {
        this.interval = interval;
        this.recipient = recipient;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
