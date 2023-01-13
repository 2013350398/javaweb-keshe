package main.pojo;

public class Thesis {
    String achiev_no = null;
    String th_name = null;
    String th_public = null;
    String th_status = null;
    String th_time = null;
    String th_index = null;
    String th_lib = null;
    String th_evid = null;

    public Thesis() {}

    @Override
    public String toString() {
        return "Thesis{" +
                "achiev_no='" + achiev_no + '\'' +
                ", th_name='" + th_name + '\'' +
                ", th_public='" + th_public + '\'' +
                ", th_status='" + th_status + '\'' +
                ", th_time='" + th_time + '\'' +
                ", th_index='" + th_index + '\'' +
                ", th_lib='" + th_lib + '\'' +
                ", th_evid='" + th_evid + '\'' +
                '}';
    }

    public String getAchiev_no() {
        return achiev_no;
    }

    public void setAchiev_no(String achiev_no) {
        this.achiev_no = achiev_no;
    }

    public String getTh_name() {
        return th_name;
    }

    public void setTh_name(String th_name) {
        this.th_name = th_name;
    }

    public String getTh_public() {
        return th_public;
    }

    public void setTh_public(String th_public) {
        this.th_public = th_public;
    }

    public String getTh_status() {
        return th_status;
    }

    public void setTh_status(String th_status) {
        this.th_status = th_status;
    }

    public String getTh_time() {
        return th_time;
    }

    public void setTh_time(String th_time) {
        this.th_time = th_time;
    }

    public String getTh_index() {
        return th_index;
    }

    public void setTh_index(String th_index) {
        this.th_index = th_index;
    }

    public String getTh_lib() {
        return th_lib;
    }

    public void setTh_lib(String th_lib) {
        this.th_lib = th_lib;
    }

    public String getTh_evid() {
        return th_evid;
    }

    public void setTh_evid(String th_evid) {
        this.th_evid = th_evid;
    }

    public Thesis(String achiev_no, String th_name, String th_public, String th_status, String th_time, String th_index, String th_lib, String th_evid) {
        this.achiev_no = achiev_no;
        this.th_name = th_name;
        this.th_public = th_public;
        this.th_status = th_status;
        this.th_time = th_time;
        this.th_index = th_index;
        this.th_lib = th_lib;
        this.th_evid = th_evid;
    }
}