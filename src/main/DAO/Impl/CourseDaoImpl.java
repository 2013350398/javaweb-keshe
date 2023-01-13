package main.DAO.Impl;

import main.ApplyAssistance.CourseTeacher;
import main.DAO.CourseDao;
import main.Util.DruidUtil;
import main.pojo.Course;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    @Override
    public void addCourse(Course course) {
        String sql="INSERT INTO course(co_id,ct_id,co_name,co_nature,co_time,co_object,co_hours,co_num,co_credit,assistance_id) values(?,?,?,?,?,?,?,?,?,?)";
        Connection con = null;
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con= dataSource.getConnection();//获取连接池
            PreparedStatement psmt=con.prepareStatement(sql);
            psmt.setString(1, course.getCo_id());
            psmt.setString(2, course.getCt_id());
            psmt.setString(3, course.getCo_name());
            psmt.setString(4, course.getCo_nature());
            psmt.setDate(5, (Date) course.getCo_time());
            psmt.setString(6,course.getCo_object());
            psmt.setInt(7,course.getCo_hours());
            psmt.setInt(8,course.getCo_num());
            psmt.setInt(9,course.getCo_credit());
            psmt.setString(10, course.getAssistance_id());
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
    public void updateCourse(Course course) {
        String sql = "update course set ct_id=?,co_name=?,co_nature=?,co_time=?,co_object=?,co_hours=?," +
                " co_num=?,co_credit=?,assistance_id=? where co_id=?";
        Connection con = null;
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con= dataSource.getConnection();//获取连接池
            PreparedStatement psmt=con.prepareStatement(sql);
            psmt.setString(1, course.getCt_id());
            psmt.setString(2, course.getCo_name());
            psmt.setString(3, course.getCo_nature());
            psmt.setDate(4, (Date) course.getCo_time());
            psmt.setString(5,course.getCo_object());
            psmt.setInt(6,course.getCo_hours());
            psmt.setInt(7,course.getCo_num());
            psmt.setInt(8,course.getCo_credit());
            psmt.setString(9, course.getAssistance_id());
            psmt.setString(10, course.getCo_id());
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
    public void deleteCourse(String co_id) {
        String sql = "delete from course where co_id=?";
        Connection con = null;
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, co_id);
            psmt.executeUpdate();
            psmt.close();
        }catch(Exception e){
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
    public Course getCourse(String co_id) {
        String sql="select * from course where co_id=?";
        Connection con = null;
        Course course = new Course();
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, co_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                course.setCo_id(rs.getString("co_id"));
                course.setCt_id(rs.getString("ct_id"));
                course.setCo_name(rs.getString("co_name"));
                course.setCo_nature(rs.getString("co_nature"));
                course.setCo_time(rs.getDate("co_time"));
                course.setCo_object(rs.getString("co_object"));
                course.setCo_hours(rs.getInt("co_hours"));
                course.setCo_num(rs.getInt("co_num"));
                course.setCo_credit(rs.getInt("co_credit"));
                course.setAssistance_id(rs.getString("assistance_id"));
            }
            psmt.close();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return course;
    }

    @Override
    public List<CourseTeacher> COURSE_LIST() {
        String sql="select co_id,co_name,ct_id,te_name,co_nature,co_num,co_object,co_hours" +
                " from course,teacher " +
                "where assistance_id=? and course.ct_id=teacher.te_id";
        Connection con = null;
        List<CourseTeacher> courseList=new ArrayList<>();
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);//
            psmt.setString(1,"00");
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                CourseTeacher courseTeacher=new CourseTeacher();//每次都要new新的对象 list遍历不然会重复同一个
                courseTeacher.setCo_id(rs.getString("co_id"));
                courseTeacher.setCt_id(rs.getString("ct_id"));
                courseTeacher.setTe_name(rs.getString("te_name"));
                courseTeacher.setCo_name(rs.getString("co_name"));
                courseTeacher.setCo_nature(rs.getString("co_nature"));
                courseTeacher.setCo_object(rs.getString("co_object"));
                courseTeacher.setCo_hours(rs.getInt("co_hours"));
                courseTeacher.setCo_num(rs.getInt("co_num"));
                courseList.add(courseTeacher);
            }
            psmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return courseList;
    }

    @Override
    public List<Course> getCourses() {
        String sql="select * from course where assistance_id=?";
        Connection con = null;
        List<Course> courseList=new ArrayList<>();
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);//
            psmt.setString(1,"00");
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Course course=new Course();//每次都要new新的对象 list遍历不然会重复同一个
                course.setCo_id(rs.getString("co_id"));
                course.setCt_id(rs.getString("ct_id"));
                course.setCo_name(rs.getString("co_name"));
                course.setCo_nature(rs.getString("co_nature"));
                course.setCo_time(rs.getDate("co_time"));
                course.setCo_object(rs.getString("co_object"));
                course.setCo_hours(rs.getInt("co_hours"));
                course.setCo_num(rs.getInt("co_num"));
                course.setCo_credit(rs.getInt("co_credit"));
                course.setAssistance_id(rs.getString("assistance_id"));
                courseList.add(course);
            }
            psmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return courseList;
    }
}