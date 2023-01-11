package main.DAO.Impl;

import main.DAO.Project_indentifyDao;
import main.Util.DBUtil;
import main.Util.DruidUtil;
import main.pojo.Project;
import main.pojo.Project_indentify;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Project_indentifyDaoImpl implements Project_indentifyDao {
    @Override
    public void addProject_indentify(Project_indentify project_indentify) {
        String sql = "INSERT INTO project_indentify(pi_id,pr_id,st_id,pi_time,pi_funding,pi_work,pi_plstate,pi_mestate) VALUES(?,?,?,?,?,?,?,?) ";
        Connection con = null;
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, project_indentify.getPi_id());
            psmt.setString(2, project_indentify.getPr_id());
            psmt.setString(3, project_indentify.getSt_id());
            psmt.setString(4, project_indentify.getPi_time());
            psmt.setString(5, project_indentify.getPi_funding());
            psmt.setString(6, project_indentify.getPi_work());
            psmt.setString(7, project_indentify.getPi_plstate());
            psmt.setString(8, project_indentify.getPi_mestate());
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
    public void updateProject_indentify(Project_indentify project_indentify) {
        String sql = "update project_indentify set pr_id=?,st_id=?,pi_time=?,pi_work=?,pi_funding=?,pi_plstate=?,pi_mestate=? where pi_id=? ";
        Connection con = null;
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, project_indentify.getPr_id());
            psmt.setString(2, project_indentify.getSt_id());
            psmt.setString(3, project_indentify.getPi_time());
            psmt.setString(4, project_indentify.getPi_work());
            psmt.setString(5, project_indentify.getPi_funding());
            psmt.setString(6, project_indentify.getPi_plstate());
            psmt.setString(7, project_indentify.getPi_mestate());
            psmt.setString(8, project_indentify.getPi_id());
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
    public void deleteProject_indentify(String pi_id) {
        String sql = "delete from project_indentify where pi_id=?";
        Connection con = null;
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, pi_id);
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
    public Project_indentify getProject_indentify(String pi_id) {
        String sql = "SELECT * FROM project_indentify WHERE pi_id=?";
        Connection con = null;
        Project_indentify project_indentify = new Project_indentify();
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, pi_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                project_indentify.setPi_id(rs.getString("pi_id"));
                project_indentify.setPr_id(rs.getString("pr_id"));
                project_indentify.setSt_id(rs.getString("st_id"));
                project_indentify.setPi_time(rs.getString("pi_time"));
                project_indentify.setPi_funding(rs.getString("pi_funding"));
                project_indentify.setPi_work(rs.getString("pi_work"));
                project_indentify.setPi_plstate(rs.getString("pi_plstate"));
                project_indentify.setPi_mestate(rs.getString("pi_mestate"));
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
        return project_indentify;
    }

    @Override
    public List<Project_indentify> findProject_indentifys(Project_indentify project_indentify) {
        String sql = "SELECT * FROM project_indentify where ";
        Connection con = null;
        List<Project_indentify>pi=new ArrayList<>();
        //保存字符串
        StringBuilder suf = new StringBuilder(512);
        suf.append(sql);
        //模糊处理
        if(project_indentify.getPi_id() != null){ suf.append("pi_id LIKE '%"+ DBUtil.fixSqlFieldValue(project_indentify.getPi_id())+ "%' AND "); }
        if(project_indentify.getPr_id() != null){ suf.append("pr_id LIKE '%"+ DBUtil.fixSqlFieldValue(project_indentify.getPr_id())+ "%' AND "); }
        if(project_indentify.getSt_id() != null){ suf.append("st_id LIKE '%"+ DBUtil.fixSqlFieldValue(project_indentify.getSt_id())+ "%' AND "); }
        if(project_indentify.getPi_time() != null){ suf.append("pi_time LIKE '%"+ DBUtil.fixSqlFieldValue(project_indentify.getPi_time())+ "%' AND "); }
        if(project_indentify.getPi_funding() != null){ suf.append("pi_funding LIKE '%"+ DBUtil.fixSqlFieldValue(project_indentify.getPi_funding())+ "%' AND "); }
        if(project_indentify.getPi_work() != null){ suf.append("pi_work LIKE '%"+ DBUtil.fixSqlFieldValue(project_indentify.getPi_work())+ "%' AND "); }
        if(project_indentify.getPi_plstate() != null){ suf.append("pi_plstate LIKE '%"+ DBUtil.fixSqlFieldValue(project_indentify.getPi_plstate())+ "%' AND "); }
        if(project_indentify.getPi_mestate() != null){ suf.append("pi_mestate LIKE '%"+ DBUtil.fixSqlFieldValue(project_indentify.getPi_mestate())+ "%' AND "); }
        //删除无用and where 前后空格也要删除
        if(suf.substring(suf.length()-5).equals(" AND ")){suf.delete(suf.length()-5,suf.length()-1);}
        if(suf.substring(suf.length()-7).equals(" WHERE ")){suf.delete(suf.length()-7,suf.length()-1);}
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(suf.toString());//
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Project_indentify pitemp = new Project_indentify();//每次都要new新的对象 list遍历不然会重复同一个
                pitemp.setPi_id(rs.getString("pi_id"));
                pitemp.setPr_id(rs.getString("pr_id"));
                pitemp.setSt_id(rs.getString("st_id"));
                pitemp.setPi_time(rs.getString("pi_time"));
                pitemp.setPi_funding(rs.getString("pi_funding"));;
                pitemp.setPi_work(rs.getString("pi_work"));
                pitemp.setPi_plstate(rs.getString("pi_plstate"));
                pitemp.setPi_mestate(rs.getString("pi_mestate"));
                pi.add(pitemp);
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
        return pi;
    }

    @Override
    public List<Project_indentify> findProject_indentifysBYMe_id(String me_id) {
        String sql = "SELECT * FROM project_indentify WHERE me_id=?";
        Connection con = null;
        List<Project_indentify>project_indentifyList=new ArrayList<>();
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, me_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Project_indentify project_indentify = new Project_indentify();
                project_indentify.setPi_id(rs.getString("pi_id"));
                project_indentify.setPr_id(rs.getString("pr_id"));
                project_indentify.setSt_id(rs.getString("st_id"));
                project_indentify.setPi_time(rs.getString("pi_time"));
                project_indentify.setPi_funding(rs.getString("pi_funding"));
                project_indentify.setPi_work(rs.getString("pi_work"));
                project_indentify.setPi_plstate(rs.getString("pi_plstate"));
                project_indentify.setPi_mestate(rs.getString("pi_mestate"));
                project_indentifyList.add(project_indentify);
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
        return project_indentifyList;
    }

    @Override
    public List<Project_indentify> findProject_indentifysBYPr_id(String pr_id) {
        String sql = "SELECT * FROM project_indentify WHERE pr_id=?";
        Connection con = null;
        List<Project_indentify>project_indentifyList=new ArrayList<>();
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, pr_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Project_indentify project_indentify = new Project_indentify();
                project_indentify.setPi_id(rs.getString("pi_id"));
                project_indentify.setPr_id(rs.getString("pr_id"));
                project_indentify.setSt_id(rs.getString("st_id"));
                project_indentify.setPi_time(rs.getString("pi_time"));
                project_indentify.setPi_funding(rs.getString("pi_funding"));
                project_indentify.setPi_work(rs.getString("pi_work"));
                project_indentify.setPi_plstate(rs.getString("pi_plstate"));
                project_indentify.setPi_mestate(rs.getString("pi_mestate"));
                project_indentifyList.add(project_indentify);
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
        return project_indentifyList;
    }

    @Override
    public List<Project_indentify> findProject_indentifysBYSt_id(String st_id) {
        String sql = "SELECT * FROM project_indentify WHERE st_id=?";
        Connection con = null;
        List<Project_indentify>project_indentifyList=new ArrayList<>();
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, st_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Project_indentify project_indentify = new Project_indentify();
                project_indentify.setPi_id(rs.getString("pi_id"));
                project_indentify.setPr_id(rs.getString("pr_id"));
                project_indentify.setSt_id(rs.getString("st_id"));
                project_indentify.setPi_time(rs.getString("pi_time"));
                project_indentify.setPi_funding(rs.getString("pi_funding"));
                project_indentify.setPi_work(rs.getString("pi_work"));
                project_indentify.setPi_plstate(rs.getString("pi_plstate"));
                project_indentify.setPi_mestate(rs.getString("pi_mestate"));
                project_indentifyList.add(project_indentify);
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
        return project_indentifyList;
    }

    @Override
    public List<Project_indentify> getAllProjectProject_indentifys() {
        String sql = "SELECT * FROM project_indentify";
        Connection con = null;
        List<Project_indentify>project_indentifyList=new ArrayList<>();
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Project_indentify project_indentify = new Project_indentify();
                project_indentify.setPi_id(rs.getString("pi_id"));
                project_indentify.setPr_id(rs.getString("pr_id"));
                project_indentify.setSt_id(rs.getString("st_id"));
                project_indentify.setPi_time(rs.getString("pi_time"));
                project_indentify.setPi_funding(rs.getString("pi_funding"));
                project_indentify.setPi_work(rs.getString("pi_work"));
                project_indentify.setPi_plstate(rs.getString("pi_plstate"));
                project_indentify.setPi_mestate(rs.getString("pi_mestate"));
                project_indentifyList.add(project_indentify);
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
        return project_indentifyList;
    }
}
