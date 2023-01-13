package main.DAO;


import main.pojo.Patent;
import main.pojo.Textbook;
import main.pojo.Thesis;

import java.util.List;

public interface PatentDAO {

    public List<Patent> selectByNo(String achiev_no);
    public List<Patent> selectByNoMentor(String achiev_no, String me_id);
    public Integer insert(Patent patent);
}