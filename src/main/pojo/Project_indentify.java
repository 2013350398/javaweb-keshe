package main.pojo;

public class Project_indentify {
    String pi_id;
    String pr_id;
    String st_id;
    String pi_time;
    String pi_work;
    String pi_funding;
    String pi_plstate;
    String pi_mestate;

    @Override
    public String toString() {
        return "Project_indentify{" +
                "pi_id='" + pi_id + '\'' +
                ", pr_id='" + pr_id + '\'' +
                ", st_id='" + st_id + '\'' +
                ", pi_time='" + pi_time + '\'' +
                ", pi_work='" + pi_work + '\'' +
                ", pi_funding='" + pi_funding + '\'' +
                ", pi_plstate='" + pi_plstate + '\'' +
                ", pi_mestate='" + pi_mestate + '\'' +
                '}';
    }

    public String getPi_id() {
        return pi_id;
    }

    public void setPi_id(String pi_id) {
        this.pi_id = pi_id;
    }

    public String getPr_id() {
        return pr_id;
    }

    public void setPr_id(String pr_id) {
        this.pr_id = pr_id;
    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public String getPi_time() {
        return pi_time;
    }

    public void setPi_time(String pi_time) {
        this.pi_time = pi_time;
    }

    public String getPi_work() {
        return pi_work;
    }

    public void setPi_work(String pi_work) {
        this.pi_work = pi_work;
    }

    public String getPi_funding() {
        return pi_funding;
    }

    public void setPi_funding(String pi_funding) {
        this.pi_funding = pi_funding;
    }

    public String getPi_plstate() {
        return pi_plstate;
    }

    public void setPi_plstate(String pi_plstate) {
        this.pi_plstate = pi_plstate;
    }

    public String getPi_mestate() {
        return pi_mestate;
    }

    public void setPi_mestate(String pi_mestate) {
        this.pi_mestate = pi_mestate;
    }
}
