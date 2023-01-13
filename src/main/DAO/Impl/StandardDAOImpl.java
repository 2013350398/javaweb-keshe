package main.DAO.Impl;

import main.DAO.StandardDAO;
import main.pojo.Award;
import main.pojo.Standard;
import main.Util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StandardDAOImpl implements StandardDAO {

    private static final String selectByNoSQL = "SELECT * FROM standard WHERE achiev_no LIKE ?;";
    private static final String selectByNoMentorSQL = "SELECT * FROM standard WHERE achiev_no LIKE ? AND " +
            "achiev_no IN (SELECT achiev_no FROM verify, me_st WHERE verify.st_id = me_st.st_id AND me_id = ?);";
    private static final String insertSQL = "INSERT INTO standard VALUES(?,?,?,?,?);";


    @Override
    public List<Standard> selectByNo(String achiev_no) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Standard> standards = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoSQL);
            pst.setString(1, achiev_no);
            rs = pst.executeQuery();

            while(rs.next()) {
                Standard standard = new Standard();
                standard.setAchiev_no(rs.getString("achiev_no"));
                standard.setSt_name(rs.getString("st_name"));
                standard.setSt_level(rs.getString("st_level"));
                standard.setSt_time(rs.getString("st_time"));
                standard.setSt_evid(rs.getString("st_evid"));
                standards.add(standard);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return standards;
    }

    @Override
    public List<Standard> selectByNoMentor(String achiev_no, String me_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Standard> standards = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoMentorSQL);
            pst.setString(1, achiev_no);
            pst.setString(2, me_id);
            rs = pst.executeQuery();

            while(rs.next()) {
                Standard standard = new Standard();
                standard.setAchiev_no(rs.getString("achiev_no"));
                standard.setSt_name(rs.getString("st_name"));
                standard.setSt_level(rs.getString("st_level"));
                standard.setSt_time(rs.getString("st_time"));
                standard.setSt_evid(rs.getString("st_evid"));
                standards.add(standard);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return standards;
    }

    @Override
    public Integer insert(Standard standard) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Integer ans = 0;

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(insertSQL);
            pst.setString(1, standard.getAchiev_no());
            pst.setString(2, standard.getSt_name());
            pst.setString(3, standard.getSt_level());
            pst.setString(4, standard.getSt_time());
            pst.setString(5, standard.getSt_evid());
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