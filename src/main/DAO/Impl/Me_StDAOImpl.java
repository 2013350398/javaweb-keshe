package main.DAO.Impl;

import main.DAO.Me_StDAO;
import main.pojo.Me_St;
import main.Util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Me_StDAOImpl implements Me_StDAO {

    public static final String selectByMeSQL = "SELECT * FROM me_st WHERE me_id = ?;";
    public static final String selectByStSQL = "SELECT * FROM me_st WHERE st_id = ?;";
    public static final String selectByAllSQL = "SELECT * FROM me_st WHERE me_id = ? AND st_id = ?;";
    public static final String insertSQL = "INSERT INTO me_st VALUES(?, ?);";


    @Override
    public List<Me_St> selectByMe(String me_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Me_St> me_sts = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByMeSQL);
            pst.setString(1,me_id);
            rs = pst.executeQuery();

            while(rs.next()) {
                Me_St me_st = new Me_St(rs.getString("me_id"), rs.getString("st_id"));
                me_sts.add(me_st);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return me_sts;
    }

    @Override
    public List<Me_St> selectBySt(String st_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Me_St> me_sts = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByStSQL);
            pst.setString(1,st_id);
            rs = pst.executeQuery();

            while(rs.next()) {
                Me_St me_st = new Me_St(rs.getString("me_id"), rs.getString("st_id"));
                me_sts.add(me_st);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return me_sts;
    }

    @Override
    public List<Me_St> selectByAll(String me_id, String st_id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Me_St> me_sts = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(selectByAllSQL);
            pst.setString(1,me_id);
            pst.setString(2,st_id);
            rs = pst.executeQuery();

            while(rs.next()) {
                Me_St me_st = new Me_St(rs.getString("me_id"), rs.getString("st_id"));
                me_sts.add(me_st);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return me_sts;
    }

    @Override
    public Integer insert(Me_St me_st) {
        Connection conn = null;
        PreparedStatement pst = null;
        Integer rs = null;
        List<Me_St> me_sts = new ArrayList<>();

        try {
            conn = DruidUtil.getConnection();
            pst = conn.prepareStatement(insertSQL);
            pst.setString(1, me_st.getMe_id());
            pst.setString(2, me_st.getSt_id());
            rs = pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
}