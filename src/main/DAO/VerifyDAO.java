package main.DAO;

import main.pojo.Verify;
import main.searchcriteria.SearchCriteria;

import java.util.List;

public interface VerifyDAO {
    public List<Verify> selectAll();

    // 根据成果编号查询
    public List<Verify> selectByAchiev(String achiev_no);
    // 根据学生学号
    public List<Verify> selectBySNo(String st_id);
    // 根据初审状态
    public List<Verify> selectByFirstStatus(Integer status);
    // 根据终审状态
    public List<Verify> selectByLastStatus(Integer status);
    // 根据导师编号
    public List<Verify> selectByMeId(String me_id);
    // 根据研究生管理员编号
    public List<Verify> selectByAdId(String ad_id);
    // 根据提交时间
    public List<Verify> selectByTime1(String submit_time);
    // 根据初审时间
    public List<Verify> selectByTime2(String first_time);
    // 根据终审时间
    public List<Verify> selectByTime3(String last_time);

    // 多条件
    public List<Verify> selectByCriteria(SearchCriteria searchCriteria);

    public List<Verify> salectByMentorCriteria(String me_id, SearchCriteria searchCriteria);

    // 新增一条审核记录
    public Integer insert(Verify verify);
    // 修改审核状态
    public Integer updateStatus(Verify verify);
}