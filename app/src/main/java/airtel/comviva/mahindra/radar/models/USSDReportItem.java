package airtel.comviva.mahindra.radar.models;

/**
 * Created by championswimmer on 16/8/16.
 */
public class USSDReportItem {

    String ussdCode;
    long timeStamp;


    public USSDReportItem(String ussdCode, long timeStamp) {
        this.ussdCode = ussdCode;
        this.timeStamp = timeStamp;
    }

    public String getUssdCode() {
        return ussdCode;
    }

    public void setUssdCode(String ussdCode) {
        this.ussdCode = ussdCode;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
