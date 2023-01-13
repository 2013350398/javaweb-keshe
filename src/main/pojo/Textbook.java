package main.pojo;


public class Textbook {
    String achiev_no = null;
    String te_name = null;
    String te_press = null;
    String te_presstime = null;
    Integer te_contri = null;
    String te_evid = null;

    public Textbook() {}

    public Textbook(String achiev_no, String te_name, String te_press, String te_presstime, Integer te_contri, String te_evid) {
        this.achiev_no = achiev_no;
        this.te_name = te_name;
        this.te_press = te_press;
        this.te_presstime = te_presstime;
        this.te_contri = te_contri;
        this.te_evid = te_evid;
    }

    @Override
    public String toString() {
        return "Textbook{" +
                "achiev_no='" + achiev_no + '\'' +
                ", te_name='" + te_name + '\'' +
                ", te_press='" + te_press + '\'' +
                ", te_presstime='" + te_presstime + '\'' +
                ", contri='" + te_contri + '\'' +
                ", te_evid='" + te_evid + '\'' +
                '}';
    }

    public String getAchiev_no() {
        return achiev_no;
    }

    public void setAchiev_no(String achiev_no) {
        this.achiev_no = achiev_no;
    }

    public String getTe_name() {
        return te_name;
    }

    public void setTe_name(String te_name) {
        this.te_name = te_name;
    }

    public String getTe_press() {
        return te_press;
    }

    public void setTe_press(String te_press) {
        this.te_press = te_press;
    }

    public String getTe_presstime() {
        return te_presstime;
    }

    public void setTe_presstime(String te_presstime) {
        this.te_presstime = te_presstime;
    }

    public Integer getTe_contri() {
        return te_contri;
    }

    public void setTe_contri(Integer te_contri) {
        this.te_contri = te_contri;
    }

    public String getTe_evid() {
        return te_evid;
    }

    public void setTe_evid(String te_evid) {
        this.te_evid = te_evid;
    }
}
