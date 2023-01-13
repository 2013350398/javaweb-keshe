package main.pojo;

public class Other {
    String achiev_no = null;
    String ot_name = null;
    String ot_time = null;
    String ot_desc = null;
    String ot_evid = null;

    public Other() {}

    public Other(String achiev_no, String ot_name, String ot_time, String ot_desc, String ot_evid) {
        this.achiev_no = achiev_no;
        this.ot_name = ot_name;
        this.ot_time = ot_time;
        this.ot_desc = ot_desc;
        this.ot_evid = ot_evid;
    }

    @Override
    public String toString() {
        return "Other{" +
                "achiev_no='" + achiev_no + '\'' +
                ", ot_name='" + ot_name + '\'' +
                ", ot_time='" + ot_time + '\'' +
                ", ot_time='" + ot_desc + '\'' +
                ", ot_evid='" + ot_evid + '\'' +
                '}';
    }

    public String getAchiev_no() {
        return achiev_no;
    }

    public void setAchiev_no(String achiev_no) {
        this.achiev_no = achiev_no;
    }

    public String getOt_name() {
        return ot_name;
    }

    public void setOt_name(String ot_name) {
        this.ot_name = ot_name;
    }

    public String getOt_time() {
        return ot_time;
    }

    public void setOt_time(String ot_time) {
        this.ot_time = ot_time;
    }

    public String getOt_desc() {
        return ot_desc;
    }

    public void setOt_desc(String ot_desc) {
        this.ot_desc = ot_desc;
    }

    public String getOt_evid() {
        return ot_evid;
    }

    public void setOt_evid(String ot_evid) {
        this.ot_evid = ot_evid;
    }
}
