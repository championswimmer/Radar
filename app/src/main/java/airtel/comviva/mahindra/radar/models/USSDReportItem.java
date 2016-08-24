package airtel.comviva.mahindra.radar.models;

import java.util.List;

import airtel.comviva.mahindra.radar.db.DbUtils;
import airtel.comviva.mahindra.radar.db.tables.TableUSSDReport;

/**
 * Created by championswimmer on 16/8/16.
 */
public class USSDReportItem {

    int ussdId;
    String ussdCode;
    Long timeStamp;
    List<Integer> recvdTimes;
    List<String> recvdMsgs;
    int status;


    public USSDReportItem(int id, String ussdCode, long timeStamp) {
        this.ussdId = id;
        this.ussdCode = ussdCode;
        this.timeStamp = timeStamp;
    }

    public USSDReportItem(int id, String ussdCode, long timeStamp, int status, String rcvdTimes, String rcvdMsgs) {
        this.ussdId = id;
        this.ussdCode = ussdCode;
        this.timeStamp = timeStamp;
        this.status = status;
        this.recvdMsgs = DbUtils.convertStringToStrList(rcvdMsgs);
        this.recvdTimes = DbUtils.convertStringToIntList(rcvdTimes);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusString() {
        switch (status) {
            case (TableUSSDReport.STATUS_SENT):
                return "SENT";
            case (TableUSSDReport.STATUS_SLA_FAILED):
                return "SLA FAILED";
            case (TableUSSDReport.STATUS_SLA_PASSED):
                return "SLA PASSED";
        }
        return "INVALID";
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Integer> getRecvdTimes() {
        return recvdTimes;
    }

    public void setRecvdTimes(List<Integer> recvdTimes) {
        this.recvdTimes = recvdTimes;
    }
    public void setRecvdTimes(String recvdTimes) {
        this.recvdTimes = DbUtils.convertStringToIntList(recvdTimes);
    }

    public List<String> getRecvdMsgs() {
        return recvdMsgs;
    }

    public void setRecvdMsgs(List<String> recvdMsgs) {
        this.recvdMsgs = recvdMsgs;
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
}
