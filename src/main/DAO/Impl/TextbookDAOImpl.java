package main.DAO.Impl;

import main.DAO.TextbookDAO;
import main.pojo.Textbook;
import main.Util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TextbookDAOImpl implements TextbookDAO {

    private static final String selectByNoSQL = "SELECT * FROM textbook WHERE achiev_no LIKE ?;";
    private static final String selectByNoMentorSQL = "SELECT * FROM textbook WHERE achiev_no LIKE ? AND " +
            "achiev_no IN (SELECT achiev_no FROM verify, me_st WHERE verify.st_id = me_st.st_id AND me_id = ?);";
    private static final String insertSQL = "INSERT INTO textbook VALUES(?,?,?,?,?,?);";

    @Override
    public List<Textbook> selectByNo(String achiev_no) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Textbook> textbooks = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoSQL);
            pst.setString(1, achiev_no);
            rs = pst.executeQuery();

            while(rs.next()) {
                Textbook textbook = new Textbook();
                textbook.setAchiev_no(rs.getString("achiev_no"));
                textbook.setTe_name(rs.getString("te_name"));
                textbook.setTe_press(rs.getString("te_press"));
                textbook.setTe_presstime(rs.getString("te_presstime"));
                textbook.setTe_contri(rs.getInt("te_contri"));
                textbook.setTe_evid(rs.getString("te_evid"));
                textbooks.add(textbook);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return textbooks;
    }

    @Override
    public List<Textbook> selectByNoMentor(String achiev_no, String me_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Textbook> textbooks = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByNoMentorSQL);
            pst.setString(1, achiev_no);
            pst.setString(2, me_id);
            rs = pst.executeQuery();

            while(rs.next()) {
                Textbook textbook = new Textbook();
                textbook.setAchiev_no(rs.getString("achiev_no"));
                textbook.setTe_name(rs.getString("te_name"));
                textbook.setTe_press(rs.getString("te_press"));
                textbook.setTe_presstime(rs.getString("te_presstime"));
                textbook.setTe_contri(rs.getInt("te_contri"));
                textbook.setTe_evid(rs.getString("te_evid"));
                textbooks.add(textbook);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtil.closeResultSet(rs);
            DruidUtil.closePrepareStatement(pst);
            DruidUtil.closeConnection(conn);
        }

        return textbooks;
    }

    @Override
    public Integer insert(Textbook textbook) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Integer ans = 0;

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(insertSQL);
            pst.setString(1, textbook.getAchiev_no());
            pst.setString(2, textbook.getTe_name());
            pst.setString(3, textbook.getTe_press());
            pst.setString(4, textbook.getTe_presstime());
            pst.setInt(5, textbook.getTe_contri());
            pst.setString(6, textbook.getTe_evid());
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
