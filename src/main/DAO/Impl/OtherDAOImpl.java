package main.DAO.Impl;

import main.DAO.OtherDAO;
import main.pojo.Other;
import main.pojo.Patent;
import main.Util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OtherDAOImpl implements OtherDAO {

    private static final String selectByNoSQL = "SELECT * FROM other WHERE achiev_no LIKE ?;";
    private static final String selectByNoMentorSQL = "SELECT * FROM other WHERE achiev_no LIKE ? AND " +
            "achiev_no IN (SELECT achiev_no FROM verify, me_st WHERE verify.st_id = me_st.st_id AND me_id = ?);";
    private static final String insertSQL = "INSERT INTO other VALUES(?,?,?,?,?);";

    @Override
    public List<Other> selectByNo(String achiev_no) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Other> others = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoSQL);
            pst.setString(1, achiev_no);
            rs = pst.executeQuery();

            while(rs.next()) {
                Other other = new Other();
                other.setAchiev_no(rs.getString("achiev_no"));
                other.setOt_name(rs.getString("ot_name"));
                other.setOt_time(rs.getString("ot_time"));
                other.setOt_desc(rs.getString("ot_desc"));
                other.setOt_evid(rs.getString("ot_evid"));
                others.add(other);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return others;
    }

    @Override
    public List<Other> selectByNoMentor(String achiev_no, String me_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Other> others = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoMentorSQL);
            pst.setString(1, achiev_no);
            pst.setString(2, me_id);
            rs = pst.executeQuery();

            while(rs.next()) {
                Other other = new Other();
                other.setAchiev_no(rs.getString("achiev_no"));
                other.setOt_name(rs.getString("ot_name"));
                other.setOt_time(rs.getString("ot_time"));
                other.setOt_desc(rs.getString("ot_desc"));
                other.setOt_evid(rs.getString("ot_evid"));
                others.add(other);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return others;
    }

    @Override
    public Integer insert(Other other) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Integer ans = 0;

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(insertSQL);
            pst.setString(1, other.getAchiev_no());
            pst.setString(2, other.getOt_name());
            pst.setString(3, other.getOt_time());
            pst.setString(4, other.getOt_desc());
            pst.setString(5, other.getOt_evid());
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