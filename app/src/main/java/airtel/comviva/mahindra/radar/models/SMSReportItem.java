package airtel.comviva.mahindra.radar.models;

import java.util.List;

/**
 * Created by championswimmer on 11/8/16.
 */
public class SMSReportItem {

    public static final int STATUS_SENDING = 0;
    public static final int STATUS_SENT = 1;
    public static final int STATUS_DELIVERED = 2;
    public static final int STATUS_RESP_RECVD = 3;
    public static final int STATUS_RESP_FAILED = 4;


    String recipient;
    String message;

    public List<String> getResponseMessages() {
        return responseMessages;
    }

    public void setResponseMessages(List<String> responseMessages) {
        this.responseMessages = responseMessages;
    }

    List<String> responseMessages;
    int smsId;
    int status;

    long sendTime;
    long sentTime;
    long deliveredTime;

    public SMSReportItem(String recipient, String message, int smsId, int status) {
        this.recipient = recipient;
        this.message = message;
        this.smsId = smsId;
        this.status = status;
    }

    public SMSReportItem(String recipient, String message, int smsId, int status, long sendTime, long sentTime, long deliveredTime) {
        this.recipient = recipient;
        this.message = message;
        this.smsId = smsId;
        this.status = status;
        this.sendTime = sendTime;
        this.sentTime = sentTime;
        this.deliveredTime = deliveredTime;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public long getSentTime() {
        return sentTime;
    }

    public void setSentTime(long sentTime) {
        this.sentTime = sentTime;
    }

    public long getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(long deliveredTime) {
        this.deliveredTime = deliveredTime;
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
            case STATUS_RESP_RECVD:
                return "SLA_PASSED";
            case STATUS_RESP_FAILED:
                return "SLA_FAILED";
        }
        return "";
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
