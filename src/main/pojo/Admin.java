package main.pojo;

public class Admin {
    String ad_id;
    String ad_name;
    String ad_pwd;

    @Override
    public String toString() {
        return "Admin{" +
                "ad_id='" + ad_id + '\'' +
                ", ad_name='" + ad_name + '\'' +
                ", ad_pwd='" + ad_pwd + '\'' +
                '}';
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_pwd() {
        return ad_pwd;
    }

    public void setAd_pwd(String ad_pwd) {
        this.ad_pwd = ad_pwd;
    }
}
