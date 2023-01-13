package main.DAO;


import main.pojo.Development;
import main.pojo.Patent;
import main.pojo.Textbook;

import java.util.List;

public interface DevelopmentDAO {
    public List<Development> selectByNo(String achiev_no);
    public List<Development> selectByNoMentor(String achiev_no, String me_id);
    public Integer insert(Development development);
}