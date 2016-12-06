package lodeinfo.model;

public class CdrEntity {

    private Long recordId;

    private String calldate;

    private String clid;

    private String src;

    private String dst;

    private String dcontext;

    private String channel;

    private String dstchannel;

    private String lastapp;

    private String lastdata;

    private int duration;

    private int billsec;

    private String disposition;

    private String uniqueid;

    public CdrEntity() {
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getCalldate() {
        return calldate;
    }

    public void setCalldate(String calldate) {
        this.calldate = calldate;
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getDcontext() {
        return dcontext;
    }

    public void setDcontext(String dcontext) {
        this.dcontext = dcontext;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDstchannel() {
        return dstchannel;
    }

    public void setDstchannel(String dstchannel) {
        this.dstchannel = dstchannel;
    }

    public String getLastapp() {
        return lastapp;
    }

    public void setLastapp(String lastapp) {
        this.lastapp = lastapp;
    }

    public String getLastdata() {
        return lastdata;
    }

    public void setLastdata(String lastdata) {
        this.lastdata = lastdata;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBillsec() {
        return billsec;
    }

    public void setBillsec(int billsec) {
        this.billsec = billsec;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }
}
