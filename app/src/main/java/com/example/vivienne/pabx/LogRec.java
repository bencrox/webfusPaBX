package com.example.vivienne.pabx;

/**
 * Created by Vivienne on 12/23/2016.
 */
public class LogRec {

//    "id"\"exten\dest\" \"caller\"intime\":picktime\"endtime\":\"hangupcause\"voicefile_id\"caller_name\"\"notes\":\"dtmf\"remarks":""}
    private String id;
    private String exten;
    private String dest;
    private String Caller;
    private String initime;
    private String picktime;
    private String endtime;
    private String hangupcause;
    private String voicefile_id;
    private String caller_name;
    private String notes;
    private String dtmf;
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExten() {
        return exten;
    }

    public void setExten(String exten) {
        this.exten = exten;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getCaller() {
        return Caller;
    }

    public void setCaller(String caller) {
        Caller = caller;
    }

    public String getInitime() {
        return initime;
    }

    public void setInitime(String initime) {
        this.initime = initime;
    }

    public String getPicktime() {
        return picktime;
    }

    public void setPicktime(String picktime) {
        this.picktime = picktime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getHangupcause() {
        return hangupcause;
    }

    public void setHangupcause(String hangupcause) {
        this.hangupcause = hangupcause;
    }

    public String getVoicefile_id() {
        return voicefile_id;
    }

    public void setVoicefile_id(String voicefile_id) {
        this.voicefile_id = voicefile_id;
    }

    public String getCaller_name() {
        return caller_name;
    }

    public void setCaller_name(String caller_name) {
        this.caller_name = caller_name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDtmf() {
        return dtmf;
    }

    public void setDtmf(String dtmf) {
        this.dtmf = dtmf;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public LogRec() {

    }
}
