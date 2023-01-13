package main.DAO;


import main.pojo.Award;
import main.pojo.Report;
import main.pojo.Textbook;

import java.util.List;

public interface ReportDAO {
    public List<Report> selectByNo(String achiev_no);
    public List<Report> selectByNoMentor(String achiev_no, String me_id);
    public Integer insert(Report report);
}
