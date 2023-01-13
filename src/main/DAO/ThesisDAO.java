package main.DAO;

import main.pojo.Thesis;

import java.util.List;

public interface ThesisDAO {
    public List<Thesis> selectByNo(String achiev_no);
    public List<Thesis> selectByNoMentor(String achiev_no, String me_id);
    public Integer insert(Thesis thesis);
}