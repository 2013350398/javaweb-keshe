package main.DAO.Impl;


import main.DAO.AwardDAO;
import main.pojo.Award;
import main.pojo.Thesis;
import main.Util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AwardDAOImpl implements AwardDAO {

    private static final String selectByNoSQL = "SELECT * FROM award WHERE achiev_no LIKE ?;";
    private static final String selectByNoMentorSQL = "SELECT * FROM award WHERE achiev_no LIKE ? AND " +
            "achiev_no IN (SELECT achiev_no FROM verify, me_st WHERE verify.st_id = me_st.st_id AND me_id = ?);";
    private static final String insertSQL = "INSERT INTO award VALUES(?,?,?,?,?,?,?);";


    @Override
    public List<Award> selectByNo(String achiev_no) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Award> awards = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoSQL);
            pst.setString(1, achiev_no);
            rs = pst.executeQuery();

            while(rs.next()) {
                Award award = new Award();
                award.setAchiev_no(rs.getString("achiev_no"));
                award.setAw_name(rs.getString("aw_name"));
                award.setAw_level(rs.getString("aw_level"));
                award.setAw_grade(rs.getString("aw_grade"));
                award.setAw_rank(rs.getInt("aw_rank"));
                award.setAw_time(rs.getString("aw_time"));
                award.setAw_evid(rs.getString("aw_evid"));
                awards.add(award);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return awards;
    }

    @Override
    public List<Award> selectByNoMentor(String achiev_no, String me_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Award> awards = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoMentorSQL);
            pst.setString(1, achiev_no);
            pst.setString(2, me_id);
            rs = pst.executeQuery();

            while(rs.next()) {
                Award award = new Award();
                award.setAchiev_no(rs.getString("achiev_no"));
                award.setAw_name(rs.getString("aw_name"));
                award.setAw_level(rs.getString("aw_level"));
                award.setAw_grade(rs.getString("aw_grade"));
                award.setAw_rank(rs.getInt("aw_rank"));
                award.setAw_time(rs.getString("aw_time"));
                award.setAw_evid(rs.getString("aw_evid"));
                awards.add(award);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return awards;
    }

    @Override
    public Integer insert(Award award) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Integer ans = 0;

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(insertSQL);
            pst.setString(1, award.getAchiev_no());
            pst.setString(2, award.getAw_name());
            pst.setString(3, award.getAw_level());
            pst.setString(4, award.getAw_grade());
            pst.setInt(5, award.getAw_rank());
            pst.setString(6, award.getAw_time());
            pst.setString(7, award.getAw_evid());
            ans = pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return ans;
    }
}