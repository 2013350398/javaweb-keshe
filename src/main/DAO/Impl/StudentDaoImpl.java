package main.DAO.Impl;

import main.DAO.StudentDao;
import main.Util.DBUtil;
import main.Util.DruidUtil;
import main.pojo.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//学生
public class StudentDaoImpl implements StudentDao {
    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO student(st_id,st_name,st_pwd,st_sex,st_tel,st_email,st_type,su_id,is_assistance) VALUES(?,?,?,?,?,?,?,?,?) ";
        Connection con = null;
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, student.getSt_id());
            psmt.setString(2, student.getSt_name());
            psmt.setString(3, student.getSt_pwd());
            psmt.setString(4, student.getSt_sex());
            psmt.setString(5, student.getSt_tel());
            psmt.setString(6, student.getSt_email());
            psmt.setString(7, student.getSt_type());
            psmt.setString(8, student.getSu_id());
            psmt.setInt(9,student.getIs_assistance());
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
    public void updateStudent(Student student) {
        String sql = "update student set st_name=?,st_pwd=?,st_sex=?,st_email=?,st_tel=?,st_type=?,su_id=?,is_assistance=? where st_id=? ";
        Connection con = null;
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, student.getSt_name());
            psmt.setString(2, student.getSt_pwd());
            psmt.setString(3, student.getSt_sex());
            psmt.setString(4, student.getSt_email());
            psmt.setString(5, student.getSt_tel());
            psmt.setString(6, student.getSt_type());
            psmt.setString(7, student.getSu_id());
            psmt.setInt(8,student.getIs_assistance());
            psmt.setString(9, student.getSt_id());
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
    public void deleteStudent(String st_id) {
        String sql = "delete from student where st_id=?";
        Connection con = null;
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, st_id);
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
    public Student getStudent(String st_id) {
        String sql = "SELECT * FROM student WHERE st_id=?";
        Connection con = null;
        Student student = new Student();
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, st_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                student.setSt_id(rs.getString("st_id"));
                student.setSt_name(rs.getString("st_name"));
                student.setSt_pwd(rs.getString("st_pwd"));
                student.setSt_sex(rs.getString("st_sex"));
                student.setSt_tel(rs.getString("st_tel"));
                student.setSt_email(rs.getString("st_email"));
                student.setSt_type(rs.getString("st_type"));
                student.setSu_id(rs.getString("su_id"));
                student.setIs_assistance(rs.getInt("is_assistance"));
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
        return student;
    }

    @Override
    public List<Student> findStudents(Student student) {
        String sql = "SELECT * FROM student where st_name=? and st_pwd=?";
        Connection con = null;
        List<Student>st=new ArrayList<>();
        //保存字符串
//        StringBuilder suf = new StringBuilder(512);
//        suf.append(sql);
//        //模糊处理
//        if(student.getSt_id() != null){ suf.append("st_id LIKE '%"+ DBUtil.fixSqlFieldValue(student.getSt_id())+ "%' AND "); }
//        if(student.getSt_name() != null){ suf.append("st_name LIKE '%"+ DBUtil.fixSqlFieldValue(student.getSt_name())+ "%' AND "); }
//        if(student.getSt_pwd() != null){ suf.append("st_pwd LIKE '%"+ DBUtil.fixSqlFieldValue(student.getSt_pwd())+ "%' AND "); }
//        if(student.getSt_sex() != null){ suf.append("st_sex LIKE '%"+ DBUtil.fixSqlFieldValue(student.getSt_sex())+ "%' AND "); }
//        if(student.getSt_tel() != null){ suf.append("st_tel LIKE '%"+ DBUtil.fixSqlFieldValue(student.getSt_tel())+ "%' AND "); }
//        if(student.getSt_email() != null){ suf.append("st_email LIKE '%"+ DBUtil.fixSqlFieldValue(student.getSt_email())+ "%' AND "); }
//        //删除无用and where 前后空格也要删除
//        if(suf.substring(suf.length()-5).equals(" AND ")){suf.delete(suf.length()-5,suf.length()-1);}
//        if(suf.substring(suf.length()-7).equals(" WHERE ")){suf.delete(suf.length()-7,suf.length()-1);}
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);//
            psmt.setString(1,student.getSt_name());
            psmt.setString(2,student.getSt_pwd());
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Student studenttemp = new Student();//每次都要new新的对象 list遍历不然会重复同一个
                studenttemp.setSt_id(rs.getString("st_id"));
                studenttemp.setSt_name(rs.getString("st_name"));
                studenttemp.setSt_pwd(rs.getString("st_pwd"));
                studenttemp.setSt_sex(rs.getString("st_sex"));
                studenttemp.setSt_tel(rs.getString("st_tel"));;
                studenttemp.setSt_email(rs.getString("st_email"));
                studenttemp.setSt_type(rs.getString("st_type"));
                studenttemp.setSu_id(rs.getString("su_id"));
                studenttemp.setIs_assistance(rs.getInt("is_assistance"));
                st.add(studenttemp);
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
        return st;
    }

    @Override
    public List<Student> ISNOTAssistance() {
        String sql = "select * from student where is_assistance=?";
        Connection con = null;
        List<Student>st=new ArrayList<>();
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);//
            psmt.setInt(1,0);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Student studenttemp = new Student();//每次都要new新的对象 list遍历不然会重复同一个
                studenttemp.setSt_id(rs.getString("st_id"));
                studenttemp.setSt_name(rs.getString("st_name"));
                studenttemp.setSt_pwd(rs.getString("st_pwd"));
                studenttemp.setSt_sex(rs.getString("st_sex"));
                studenttemp.setSt_tel(rs.getString("st_tel"));;
                studenttemp.setSt_email(rs.getString("st_email"));
                studenttemp.setSt_type(rs.getString("st_type"));
                studenttemp.setSu_id(rs.getString("su_id"));
                studenttemp.setIs_assistance(rs.getInt("is_assistance"));
                st.add(studenttemp);
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
        return st;
    }
}