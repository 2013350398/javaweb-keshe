package main.DAO.Impl;

import main.CustomType.ApaTeacher;
import main.DAO.ApplicationDao;
import main.Util.DruidUtil;
import main.pojo.Application;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {
    @Override
    public void addApplication(Application application) {
        String sql="INSERT INTO application(st_id,co_id) values(?,?)";
        Connection con = null;
        try {
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, application.getSt_id());
            psmt.setString(2, application.getCo_id());
            psmt.executeUpdate();
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateApplication(Application application) {
        String sql = "update application set st_id=?,co_id=? where sa_id=?";
        Connection con = null;
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, application.getSt_id());
            psmt.setString(2, application.getCo_id());
            psmt.setInt(3, application.getSa_id());
            psmt.executeUpdate();
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void deleteApplication(String co_id) {
        String sql="delete from application where co_id=?";
        Connection con = null;
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, co_id);
            psmt.executeUpdate();
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Application getApplication(int sa_id) {
        String sql = "select * from application where sa_id=?";
        Connection con = null;
        Application application = new Application();
        try {
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setInt(1,sa_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                application.setSa_id(rs.getInt("sa_id"));
                application.setSt_id(rs.getString("st_id"));
                application.setCo_id(rs.getString("co_id"));
            }
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return application;
    }

    @Override
    public List<Application> APPLICATION_LIST() {
        String sql="select * from application";
        Connection con = null;
        List<Application> applicationList = new ArrayList<>();
        try {
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con = dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Application application = new Application();
                application.setSa_id(rs.getInt("sa_id"));
                application.setSt_id(rs.getString("st_id"));
                application.setCo_id(rs.getString("co_id"));
                applicationList.add(application);
            }
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return applicationList;
    }

    @Override
    public List<ApaTeacher> APA_TEACHER_LIST(String tid) {
        String sql = "select sa_id,student.st_id,st_name,course.co_id,co_name " +
                "from application,course,student " +
                "where application.co_id=course.co_id and application.st_id = student.st_id " +
                "and course.ct_id=?";
        Connection con = null;
        List<ApaTeacher> apaTeacherList = new ArrayList<>();
        try {
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con = dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,tid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                ApaTeacher apaTeacher = new ApaTeacher();
                apaTeacher.setSa_id(rs.getInt("sa_id"));
                apaTeacher.setSt_id(rs.getString("st_id"));
                apaTeacher.setSt_name(rs.getString("st_name"));
                apaTeacher.setCo_id(rs.getString("co_id"));
                apaTeacher.setCo_name("co_name");
                apaTeacherList.add(apaTeacher);
            }
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return apaTeacherList;
    }
}