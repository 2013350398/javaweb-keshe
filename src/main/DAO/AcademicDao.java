package main.DAO;

import main.pojo.academic;

import java.util.List;

public interface AcademicDao {
    //签字
    void updateByCharge(String st_id,String sign,int ac_id);
    void updateByMentor(String st_id,String sign,int ac_id);
    //审核所有
    List<academic> verify();
    //学生填写
    void insertByStudent(academic a);
    //学科负责人查看每个学生次数
    int countByCharge(String st_id);



}