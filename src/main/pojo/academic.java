package main.pojo;

public class academic {
    String ac_id;
    String ac_img;
    String ac_meetingName;
    String ac_time;
    String ac_location;
    String acName;
    String ac_remarks;
    //新增加的
    String st_id;

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public String getAc_id() {
        return ac_id;
    }

    public void setAc_id(String ac_id) {
        this.ac_id = ac_id;
    }

    public String getAc_img() {
        return ac_img;
    }

    public void setAc_img(String ac_img) {
        this.ac_img = ac_img;
    }

    public String getAc_meetingName() {
        return ac_meetingName;
    }

    public void setAc_meetingName(String ac_meetingName) {
        this.ac_meetingName = ac_meetingName;
    }

    public String getAc_time() {
        return ac_time;
    }

    public void setAc_time(String ac_time) {
        this.ac_time = ac_time;
    }

    public String getAc_location() {
        return ac_location;
    }

    public void setAc_location(String ac_location) {
        this.ac_location = ac_location;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAc_remarks() {
        return ac_remarks;
    }

    public void setAc_remarks(String ac_remarks) {
        this.ac_remarks = ac_remarks;
    }

    public String getMentorSign() {
        return mentorSign;
    }

    public void setMentorSign(String mentorSign) {
        this.mentorSign = mentorSign;
    }

    public String getChargeSign() {
        return chargeSign;
    }

    public void setChargeSign(String chargeSign) {
        this.chargeSign = chargeSign;
    }

    @Override
    public String toString() {
        return
                "学术交流id'" + ac_id + '\'' +
                        ", 图片='" + ac_img + '\'' +
                        ", 报告中英名称='" + ac_meetingName + '\'' +
                        ", 活动时间='" + ac_time + '\'' +
                        ", 活动地点='" + ac_location + '\'' +
                        ", 学术交流活动名称='" + acName + '\'' +
                        ", 备注='" + ac_remarks + '\'' +
                        ", 学生编号='" + st_id + '\'' +
                        ", 导师签字='" + mentorSign + '\'' +
                        ", 学科负责人签字='" + chargeSign + '\'' +
                        '\n';
    }

    String mentorSign;
    String chargeSign;
}