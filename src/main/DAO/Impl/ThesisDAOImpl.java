package main.DAO.Impl;


import main.DAO.ThesisDAO;
import main.pojo.Thesis;
import main.Util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThesisDAOImpl implements ThesisDAO {

    private static final String selectByNoSQL = "SELECT * FROM thesis WHERE achiev_no LIKE ?;";
    private static final String selectByNoMentorSQL = "SELECT * FROM thesis WHERE achiev_no LIKE ? AND " +
            "achiev_no IN (SELECT achiev_no FROM verify, me_st WHERE verify.st_id = me_st.st_id AND me_st.me_id = ?);";
    private static final String insertSQL = "INSERT INTO thesis VALUES(?,?,?,?,?,?,?,?);";


    @Override
    public List<Thesis> selectByNo(String achiev_no) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Thesis> theses = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoSQL);
            pst.setString(1, achiev_no);
            rs = pst.executeQuery();

            while(rs.next()) {
                Thesis thesis = new Thesis();
                thesis.setAchiev_no(rs.getString("achiev_no"));
                thesis.setTh_name(rs.getString("th_name"));
                thesis.setTh_public(rs.getString("th_public"));
                thesis.setTh_status(rs.getString("th_status"));
                thesis.setTh_time(rs.getString("th_time"));
                thesis.setTh_index(rs.getString("th_index"));
                thesis.setTh_lib(rs.getString("th_lib"));
                thesis.setTh_evid(rs.getString("th_evid"));
                theses.add(thesis);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return theses;
    }

    @Override
    public List<Thesis> selectByNoMentor(String achiev_no, String me_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Thesis> theses = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoMentorSQL);
            pst.setString(1, achiev_no);
            pst.setString(2, me_id);
            rs = pst.executeQuery();

            while(rs.next()) {
                Thesis thesis = new Thesis();
                thesis.setAchiev_no(rs.getString("achiev_no"));
                thesis.setTh_name(rs.getString("th_name"));
                thesis.setTh_public(rs.getString("th_public"));
                thesis.setTh_status(rs.getString("th_status"));
                thesis.setTh_time(rs.getString("th_time"));
                thesis.setTh_index(rs.getString("th_index"));
                thesis.setTh_lib(rs.getString("th_lib"));
                thesis.setTh_evid(rs.getString("th_evid"));
                theses.add(thesis);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return theses;
    }

    @Override
    public Integer insert(Thesis thesis) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Integer ans = 0;

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(insertSQL);
            pst.setString(1, thesis.getAchiev_no());
            pst.setString(2, thesis.getTh_name());
            pst.setString(3, thesis.getTh_public());
            pst.setString(4, thesis.getTh_status());
            pst.setString(5, thesis.getTh_time());
            pst.setString(6, thesis.getTh_index());
            pst.setString(7, thesis.getTh_lib());
            pst.setString(8, thesis.getTh_evid());
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