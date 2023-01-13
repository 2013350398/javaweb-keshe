package main.DAO;

import main.pojo.Standard;
import main.pojo.Textbook;
import main.pojo.Thesis;

import java.util.List;

public interface StandardDAO {
    public List<Standard> selectByNo(String achiev_no);

    public List<Standard> selectByNoMentor(String achiev_no, String me_id);
    public Integer insert(Standard standard);
}