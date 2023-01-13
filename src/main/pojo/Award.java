package main.pojo;

public class Award {
    String achiev_no = null;
    String aw_name = null;
    String aw_level = null;
    String aw_grade = null;
    Integer aw_rank = null;
    String aw_time = null;
    String aw_evid = null;

    public Award(){}

    public Award(String achiev_no, String aw_name, String aw_level, String aw_grade, Integer aw_rank, String aw_time, String aw_evid) {
        this.achiev_no = achiev_no;
        this.aw_name = aw_name;
        this.aw_level = aw_level;
        this.aw_grade = aw_grade;
        this.aw_rank = aw_rank;
        this.aw_time = aw_time;
        this.aw_evid = aw_evid;
    }

    @Override
    public String toString() {
        return "Award{" +
                "achiev_no='" + achiev_no + '\'' +
                ", aw_name='" + aw_name + '\'' +
                ", aw_level='" + aw_level + '\'' +
                ", aw_grade='" + aw_grade + '\'' +
                ", aw_rank=" + aw_rank +
                ", aw_time='" + aw_time + '\'' +
                ", aw_evid='" + aw_evid + '\'' +
                '}';
    }

    public String getAchiev_no() {
        return achiev_no;
    }

    public void setAchiev_no(String achiev_no) {
        this.achiev_no = achiev_no;
    }

    public String getAw_name() {
        return aw_name;
    }

    public void setAw_name(String aw_name) {
        this.aw_name = aw_name;
    }

    public String getAw_level() {
        return aw_level;
    }

    public void setAw_level(String aw_level) {
        this.aw_level = aw_level;
    }

    public String getAw_grade() {
        return aw_grade;
    }

    public void setAw_grade(String aw_grade) {
        this.aw_grade = aw_grade;
    }

    public Integer getAw_rank() {
        return aw_rank;
    }

    public void setAw_rank(Integer aw_rank) {
        this.aw_rank = aw_rank;
    }

    public String getAw_time() {
        return aw_time;
    }

    public void setAw_time(String aw_time) {
        this.aw_time = aw_time;
    }

    public String getAw_evid() {
        return aw_evid;
    }

    public void setAw_evid(String aw_evid) {
        this.aw_evid = aw_evid;
    }
}