package main.pojo;


public class Verify {

    private String achiev_no = null;
    private String st_id = null;
    private String submit_time = null;
    private String me_id = null;
    private Integer first_verify = null;
    private String first_time = null;
    private String ad_id = null;
    private Integer last_verify = null;
    private String last_time = null;

    public Verify() {};

    public Verify(String achiev_no, String st_id, String submit_time, String me_id, Integer first_verify, String first_time, String ad_id, Integer last_verify, String last_time) {
        this.achiev_no = achiev_no;
        this.st_id = st_id;
        this.submit_time = submit_time;
        this.me_id = me_id;
        this.first_verify = first_verify;
        this.first_time = first_time;
        this.ad_id = ad_id;
        this.last_verify = last_verify;
        this.last_time = last_time;
    }

    @Override
    public String toString() {
        return "Verify{" +
                "achiev_no='" + achiev_no + '\'' +
                ", st_id='" + st_id + '\'' +
                ", submit_time='" + submit_time + '\'' +
                ", me_id='" + me_id + '\'' +
                ", first_verify=" + first_verify +
                ", first_time='" + first_time + '\'' +
                ", ad_id='" + ad_id + '\'' +
                ", last_verify=" + last_verify +
                ", last_time='" + last_time + '\'' +
                '}';
    }

    public String getAchiev_no() {
        return achiev_no;
    }

    public void setAchiev_no(String achiev_no) {
        this.achiev_no = achiev_no;
    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

    public String getMe_id() {
        return me_id;
    }

    public void setMe_id(String me_id) {
        this.me_id = me_id;
    }

    public Integer getFirst_verify() {
        return first_verify;
    }

    public void setFirst_verify(Integer first_verify) {
        this.first_verify = first_verify;
    }

    public String getFirst_time() {
        return first_time;
    }

    public void setFirst_time(String first_time) {
        this.first_time = first_time;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public Integer getLast_verify() {
        return last_verify;
    }

    public void setLast_verify(Integer last_verify) {
        this.last_verify = last_verify;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }
}
