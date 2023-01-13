package main.pojo;


public class Patent {

    String achiev_no = null;
    String pa_name = null;
    String pa_type = null;
    String pa_no = null;
    String pa_time = null;
    String pa_status = null;
    Integer pa_contri = null;
    String pa_evid = null;

    public Patent() {}

    public Patent(String achiev_no, String pa_name, String pa_type, String pa_no, String pa_time, String pa_status, Integer pa_contri, String pa_evid) {
        this.achiev_no = achiev_no;
        this.pa_name = pa_name;
        this.pa_type = pa_type;
        this.pa_no = pa_no;
        this.pa_time = pa_time;
        this.pa_status = pa_status;
        this.pa_contri = pa_contri;
        this.pa_evid = pa_evid;
    }

    @Override
    public String toString() {
        return "Patent{" +
                "achiev_no='" + achiev_no + '\'' +
                ", pa_name='" + pa_name + '\'' +
                ", pa_type='" + pa_type + '\'' +
                ", pa_no='" + pa_no + '\'' +
                ", pa_time='" + pa_time + '\'' +
                ", pa_status='" + pa_status + '\'' +
                ", pa_contri=" + pa_contri +
                ", pa_evid='" + pa_evid + '\'' +
                '}';
    }

    public String getAchiev_no() {
        return achiev_no;
    }

    public void setAchiev_no(String achiev_no) {
        this.achiev_no = achiev_no;
    }

    public String getPa_name() {
        return pa_name;
    }

    public void setPa_name(String pa_name) {
        this.pa_name = pa_name;
    }

    public String getPa_type() {
        return pa_type;
    }

    public void setPa_type(String pa_type) {
        this.pa_type = pa_type;
    }

    public String getPa_no() {
        return pa_no;
    }

    public void setPa_no(String pa_no) {
        this.pa_no = pa_no;
    }

    public String getPa_time() {
        return pa_time;
    }

    public void setPa_time(String pa_time) {
        this.pa_time = pa_time;
    }

    public String getPa_status() {
        return pa_status;
    }

    public void setPa_status(String pa_status) {
        this.pa_status = pa_status;
    }

    public Integer getPa_contri() {
        return pa_contri;
    }

    public void setPa_contri(Integer pa_contri) {
        this.pa_contri = pa_contri;
    }

    public String getPa_evid() {
        return pa_evid;
    }

    public void setPa_evid(String pa_evid) {
        this.pa_evid = pa_evid;
    }
}