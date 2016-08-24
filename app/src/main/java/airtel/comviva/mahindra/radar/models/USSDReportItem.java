package airtel.comviva.mahindra.radar.models;

/**
 * Created by championswimmer on 16/8/16.
 */
public class USSDReportItem {

    int ussdId;
    String ussdCode;
    Long timeStamp;
    int[] recvdTimes;
    String recvdMsgs;


    public USSDReportItem(String ussdCode, long timeStamp) {
        this.ussdCode = ussdCode;
        this.timeStamp = timeStamp;
    }




    public int getUssdId() {
        return ussdId;
    }

    public void setUssdId(int ussdId) {
        this.ussdId = ussdId;
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
