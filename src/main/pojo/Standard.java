package main.pojo;


public class Standard {
    String achiev_no = null;
    String st_name = null;
    String st_level = null;
    String st_time = null;
    String st_evid = null;

    public Standard() {}

    public Standard(String achiev_no, String st_name, String st_level, String st_time, String st_evid) {
        this.achiev_no = achiev_no;
        this.st_name = st_name;
        this.st_level = st_level;
        this.st_time = st_time;
        this.st_evid = st_evid;
    }

    @Override
    public String toString() {
        return "Standard{" +
                "achiev_no='" + achiev_no + '\'' +
                ", st_name='" + st_name + '\'' +
                ", st_level='" + st_level + '\'' +
                ", st_time='" + st_time + '\'' +
                ", st_evid='" + st_evid + '\'' +
                '}';
    }

    public String getAchiev_no() {
        return achiev_no;
    }

    public void setAchiev_no(String achiev_no) {
        this.achiev_no = achiev_no;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getSt_level() {
        return st_level;
    }

    public void setSt_level(String st_level) {
        this.st_level = st_level;
    }

    public String getSt_time() {
        return st_time;
    }

    public void setSt_time(String st_time) {
        this.st_time = st_time;
    }

    public String getSt_evid() {
        return st_evid;
    }

    public void setSt_evid(String st_evid) {
        this.st_evid = st_evid;
    }
}