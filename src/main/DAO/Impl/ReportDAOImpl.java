package main.DAO.Impl;

import main.DAO.ReportDAO;
import main.pojo.Report;
import main.pojo.Thesis;
import main.Util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDAOImpl implements ReportDAO {

    private static final String selectByNoSQL = "SELECT * FROM report WHERE achiev_no LIKE ?;";
    private static final String selectByNoMentorSQL = "SELECT * FROM report WHERE achiev_no LIKE ? AND " +
            "achiev_no IN (SELECT achiev_no FROM verify, me_st WHERE verify.st_id = me_st.st_id AND me_id = ?);";
    private static final String insertSQL = "INSERT INTO report VALUES(?,?,?,?,?,?,?);";


    @Override
    public List<Report> selectByNo(String achiev_no) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Report> reports = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoSQL);
            pst.setString(1, achiev_no);
            rs = pst.executeQuery();

            while(rs.next()) {
                Report report = new Report();
                report.setAchiev_no(rs.getString("achiev_no"));
                report.setRe_name(rs.getString("re_name"));
                report.setRe_type(rs.getString("re_type"));
                report.setRe_unit(rs.getString("re_unit"));
                report.setRe_time(rs.getString("re_time"));
                report.setRe_contri(rs.getInt("re_contri"));
                report.setRe_evid(rs.getString("re_evid"));
                reports.add(report);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return reports;
    }

    @Override
    public List<Report> selectByNoMentor(String achiev_no, String me_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Report> reports = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoMentorSQL);
            pst.setString(1, achiev_no);
            pst.setString(2, me_id);
            rs = pst.executeQuery();

            while(rs.next()) {
                Report report = new Report();
                report.setAchiev_no(rs.getString("achiev_no"));
                report.setRe_name(rs.getString("re_name"));
                report.setRe_type(rs.getString("re_type"));
                report.setRe_unit(rs.getString("re_unit"));
                report.setRe_time(rs.getString("re_time"));
                report.setRe_contri(rs.getInt("re_contri"));
                report.setRe_evid(rs.getString("re_evid"));
                reports.add(report);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return reports;
    }

    @Override
    public Integer insert(Report report) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Integer ans = 0;

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(insertSQL);
            pst.setString(1, report.getAchiev_no());
            pst.setString(2, report.getRe_name());
            pst.setString(3, report.getRe_type());
            pst.setString(4, report.getRe_unit());
            pst.setString(5, report.getRe_time());
            pst.setInt(6, report.getRe_contri());
            pst.setString(7, report.getRe_evid());
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