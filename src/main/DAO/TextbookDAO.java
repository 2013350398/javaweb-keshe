package main.DAO;

import main.pojo.Patent;
import main.pojo.Textbook;
import main.pojo.Thesis;

import java.util.List;

public interface TextbookDAO {

    public List<Textbook> selectByNo(String achiev_no);
    public List<Textbook> selectByNoMentor(String achiev_no, String me_id);
    public Integer insert(Textbook textbook);
}