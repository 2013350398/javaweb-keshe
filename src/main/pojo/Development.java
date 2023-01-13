package main.pojo;

public class Development {

    String achiev_no = null;
    String de_name = null;
    String de_unit = null;
    String de_time = null;
    Integer de_contri = null;
    String de_evid = null;

    public Development() {}

    public Development(String achiev_no, String de_name, String de_unit, String de_time, Integer de_contri, String de_evid) {
        this.achiev_no = achiev_no;
        this.de_name = de_name;
        this.de_unit = de_unit;
        this.de_time = de_time;
        this.de_contri = de_contri;
        this.de_evid = de_evid;
    }

    @Override
    public String toString() {
        return "Development{" +
                "achiev_no='" + achiev_no + '\'' +
                ", de_name='" + de_name + '\'' +
                ", de_unit='" + de_unit + '\'' +
                ", de_time='" + de_time + '\'' +
                ", de_contri=" + de_contri +
                ", de_evid='" + de_evid + '\'' +
                '}';
    }

    public String getAchiev_no() {
        return achiev_no;
    }

    public void setAchiev_no(String achiev_no) {
        this.achiev_no = achiev_no;
    }

    public String getDe_name() {
        return de_name;
    }

    public void setDe_name(String de_name) {
        this.de_name = de_name;
    }

    public String getDe_unit() {
        return de_unit;
    }

    public void setDe_unit(String de_unit) {
        this.de_unit = de_unit;
    }

    public String getDe_time() {
        return de_time;
    }

    public void setDe_time(String de_time) {
        this.de_time = de_time;
    }

    public Integer getDe_contri() {
        return de_contri;
    }

    public void setDe_contri(Integer de_contri) {
        this.de_contri = de_contri;
    }

    public String getDe_evid() {
        return de_evid;
    }

    public void setDe_evid(String de_evid) {
        this.de_evid = de_evid;
    }
}