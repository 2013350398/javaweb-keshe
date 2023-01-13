package main.DAO.Impl;

import main.DAO.PatentDAO;
import main.pojo.Patent;
import main.pojo.Thesis;
import main.Util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatentDAOImpl implements PatentDAO {

    private static final String selectByNoSQL = "SELECT * FROM patent WHERE achiev_no LIKE ?;";
    private static final String selectByNoMentorSQL = "SELECT * FROM patent WHERE achiev_no LIKE ? AND " +
            "achiev_no IN (SELECT achiev_no FROM verify, me_st WHERE verify.st_id = me_st.st_id AND me_id = ?);";
    private static final String insertSQL = "INSERT INTO patent VALUES(?,?,?,?,?,?,?,?);";

    @Override
    public List<Patent> selectByNo(String achiev_no) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Patent> patents = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoSQL);
            pst.setString(1, achiev_no);
            rs = pst.executeQuery();

            while(rs.next()) {
                Patent patent = new Patent();
                patent.setAchiev_no(rs.getString("achiev_no"));
                patent.setPa_name(rs.getString("pa_name"));
                patent.setPa_type(rs.getString("pa_type"));
                patent.setPa_no(rs.getString("pa_no"));
                patent.setPa_time(rs.getString("pa_time"));
                patent.setPa_status(rs.getString("pa_status"));
                patent.setPa_contri(rs.getInt("pa_contri"));
                patent.setPa_evid(rs.getString("pa_evid"));
                patents.add(patent);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return patents;
    }

    @Override
    public List<Patent> selectByNoMentor(String achiev_no, String me_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Patent> patents = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoMentorSQL);
            pst.setString(1, achiev_no);
            pst.setString(2, me_id);
            rs = pst.executeQuery();

            while(rs.next()) {
                Patent patent = new Patent();
                patent.setAchiev_no(rs.getString("achiev_no"));
                patent.setPa_name(rs.getString("pa_name"));
                patent.setPa_type(rs.getString("pa_type"));
                patent.setPa_no(rs.getString("pa_no"));
                patent.setPa_time(rs.getString("pa_time"));
                patent.setPa_status(rs.getString("pa_status"));
                patent.setPa_contri(rs.getInt("pa_contri"));
                patent.setPa_evid(rs.getString("pa_evid"));
                patents.add(patent);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return patents;
    }

    @Override
    public Integer insert(Patent patent) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Integer ans = 0;

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(insertSQL);
            pst.setString(1, patent.getAchiev_no());
            pst.setString(2, patent.getPa_name());
            pst.setString(3, patent.getPa_type());
            pst.setString(4, patent.getPa_no());
            pst.setString(5, patent.getPa_time());
            pst.setString(6, patent.getPa_status());
            pst.setInt(7, patent.getPa_contri());
            pst.setString(8, patent.getPa_evid());
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