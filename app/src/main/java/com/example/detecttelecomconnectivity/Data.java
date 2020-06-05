package com.example.detecttelecomconnectivity;

public class Data {
    private  String opname;
    private long dospeed;
    private long upspeed;
    public Data() {


    }

    public long getUpspeed() {
        return upspeed;
    }

    public void setUpspeed(long upspeed) {
        this.upspeed = upspeed;
    }

    public String getOpname() {
        return opname;
    }

    public void setOpname(String opname) {
        this.opname = opname;
    }

    public long getDospeed() {
        return dospeed;
    }

    public void setDospeed(long dospeed) {
        this.dospeed = dospeed;
    }
}
