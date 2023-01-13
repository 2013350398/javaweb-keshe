package main.DAO.Impl;

import main.DAO.TeacherDao;
import main.Util.DruidUtil;
import main.pojo.Teacher;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public void addTeacher(Teacher teacher) {
        String sql = "insert into teacher(te_id,te_pwd,te_name,te_tel,te_email) values(?,?,?,?,?)";
        Connection con = null;
        try {
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con = dataSource.getConnection();
            PreparedStatement psmt=con.prepareStatement(sql);
            psmt.setString(1, teacher.getTe_id());
            psmt.setString(2, teacher.getTe_pwd());
            psmt.setString(3,teacher.getTe_name());
            psmt.setString(4,teacher.getTe_tel());
            psmt.setString(5, teacher.getTe_email());
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
    public void updateTeacher(Teacher teacher) {
        String sql = "update teacher set te_pwd=?,te_name=?,te_tel=?,te_email=? where te_id=?";
        Connection con = null;
        try {
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con = dataSource.getConnection();
            PreparedStatement psmt=con.prepareStatement(sql);
            psmt.setString(1, teacher.getTe_pwd());
            psmt.setString(2,teacher.getTe_name());
            psmt.setString(3,teacher.getTe_tel());
            psmt.setString(4, teacher.getTe_email());
            psmt.setString(5, teacher.getTe_id());
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
    public void deleteTeacher(String te_id) {
        String sql="delete from teacher where te_id=?";
        Connection con = null;
        try {
            DruidUtil druidUtil = null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,te_id);
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
    public Teacher getTeacher(String te_id) {
        String sql = "select * from teacher where te_id=?";
        Connection con = null;
        Teacher teacher = new Teacher();
        try {
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con= dataSource.getConnection();
            PreparedStatement psmt= con.prepareStatement(sql);
            psmt.setString(1,te_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                teacher.setTe_id(rs.getString("te_id"));
                teacher.setTe_pwd(rs.getString("te_pwd"));
                teacher.setTe_name(rs.getString("te_name"));
                teacher.setTe_tel(rs.getString("te_tel"));
                teacher.setTe_email(rs.getString("te_email"));
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
        return teacher;
    }

    @Override
    public List<Teacher> TEACHER_LIST(Teacher teacher) {
        String sql = "select * from teacher where te_name=? and te_pwd=?";
        Connection con = null;
        List<Teacher> teacherList = new ArrayList<>();
        try {
            DruidUtil druidUtil = null;
            DataSource dataSource = druidUtil.getDataSource();
            con = dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,teacher.getTe_name());
            psmt.setString(2,teacher.getTe_pwd());
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                Teacher teacher1 = new Teacher();
                teacher1.setTe_id(rs.getString("te_id"));
                teacher1.setTe_pwd(rs.getString("te_pwd"));
                teacher1.setTe_name(rs.getString("te_name"));
                teacher1.setTe_tel(rs.getString("te_tel"));
                teacher1.setTe_email(rs.getString("te_email"));
                teacherList.add(teacher1);
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
        return teacherList;
    }
}