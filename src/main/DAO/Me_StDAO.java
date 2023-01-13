package main.DAO;


import main.pojo.Me_St;

import java.util.List;

public interface Me_StDAO {
    public List<Me_St> selectByMe(String me_id);
    public List<Me_St> selectBySt(String st_id);
    public List<Me_St> selectByAll(String me_id, String st_id);
    public Integer insert(Me_St me_st);

}