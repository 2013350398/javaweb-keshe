package main.pojo;


public class Me_St {
    String me_id;
    String st_id;

    public Me_St() {}

    public Me_St(String me_id, String st_id) {
        this.me_id = me_id;
        this.st_id = st_id;
    }

    @Override
    public String toString() {
        return "Me_St{" +
                "me_id='" + me_id + '\'' +
                ", st_id='" + st_id + '\'' +
                '}';
    }

    public String getMe_id() {
        return me_id;
    }

    public void setMe_id(String me_id) {
        this.me_id = me_id;
    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }
}