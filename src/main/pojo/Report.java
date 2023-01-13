package main.pojo;


public class Report {

    String achiev_no = null;
    String re_name = null;
    String re_type = null;
    String re_unit = null;
    String re_time = null;
    Integer re_contri = null;
    String re_evid = null;

    public Report() {}

    public Report(String achiev_no, String re_name, String re_type, String re_unit, String re_time, Integer re_contri, String re_evid) {
        this.achiev_no = achiev_no;
        this.re_name = re_name;
        this.re_type = re_type;
        this.re_unit = re_unit;
        this.re_time = re_time;
        this.re_contri = re_contri;
        this.re_evid = re_evid;
    }

    @Override
    public String toString() {
        return "Report{" +
                "achiev_no='" + achiev_no + '\'' +
                ", re_name='" + re_name + '\'' +
                ", re_type='" + re_type + '\'' +
                ", re_unit='" + re_unit + '\'' +
                ", re_time='" + re_time + '\'' +
                ", re_contri=" + re_contri +
                ", re_evid='" + re_evid + '\'' +
                '}';
    }

    public String getAchiev_no() {
        return achiev_no;
    }

    public void setAchiev_no(String achiev_no) {
        this.achiev_no = achiev_no;
    }

    public String getRe_name() {
        return re_name;
    }

    public void setRe_name(String re_name) {
        this.re_name = re_name;
    }

    public String getRe_type() {
        return re_type;
    }

    public void setRe_type(String re_type) {
        this.re_type = re_type;
    }

    public String getRe_unit() {
        return re_unit;
    }

    public void setRe_unit(String re_unit) {
        this.re_unit = re_unit;
    }

    public String getRe_time() {
        return re_time;
    }

    public void setRe_time(String re_time) {
        this.re_time = re_time;
    }

    public Integer getRe_contri() {
        return re_contri;
    }

    public void setRe_contri(Integer re_contri) {
        this.re_contri = re_contri;
    }

    public String getRe_evid() {
        return re_evid;
    }

    public void setRe_evid(String re_evid) {
        this.re_evid = re_evid;
    }
}