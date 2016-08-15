package airtel.comviva.mahindra.radar.models;

/**
 * Created by championswimmer on 10/8/16.
 */
public class USSDTask {
    int interval;
    String recipient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public USSDTask(int interval, String recipient, int id) {
        this.interval = interval;
        this.recipient = recipient;
        this.id = id;
    }

    public USSDTask(int interval, String recipient) {
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
