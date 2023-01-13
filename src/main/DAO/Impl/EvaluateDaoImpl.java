package main.DAO.Impl;

import main.DAO.EvaluateDao;
import main.Util.DruidUtil;
import main.pojo.Course;
import main.pojo.Evaluate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class EvaluateDaoImpl implements EvaluateDao {
    @Override
    public void addEvaluate(Evaluate evaluate) {
        String sql = "insert into evaluate(st_id,co_id,self_eva,sub_time,te_eva,te_idea,te_time) values(?,?,?,?,?,?,?)";
        Connection con = null;
        try {
            DruidUtil druidUtil = null;
            DataSource dataSource = druidUtil.getDataSource();
            con=dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,evaluate.getSt_id());
            psmt.setString(2,evaluate.getCo_id());
            psmt.setString(3,evaluate.getSelf_eva());
            psmt.setString(4,  evaluate.getSub_time());
            psmt.setString(5,evaluate.getTe_eva());
            psmt.setString(6,evaluate.getTe_idea());
            psmt.setString(7,evaluate.getTe_time());
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
    public void updateEvaluate(Evaluate evaluate) {
        String sql="update evaluate set st_id=?,co_id=?,self_eva=?,sub_time=?,te_eva=?,te_idea=?,te_time=? where ev_id=?";
        Connection con = null;
        try {
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con = dataSource.getConnection();
            PreparedStatement psmt= con.prepareStatement(sql);
            psmt.setString(1,evaluate.getSt_id());
            psmt.setString(2,evaluate.getCo_id());
            psmt.setString(3,evaluate.getSelf_eva());
            psmt.setString(4, evaluate.getSub_time());
            psmt.setString(5,evaluate.getTe_eva());
            psmt.setString(6,evaluate.getTe_idea());
            psmt.setString(7, evaluate.getTe_time());
            psmt.setInt(8,evaluate.getEv_id());
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
    public void deleteEvaluate(String ev_id) {
        String sql = "delete from evaluate where ev_id=?";
        Connection con = null;
        try {
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,ev_id);
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
    public Evaluate getEvaluate(String ev_id) {
        String sql="select * from application where ev_id=?";
        Connection con = null;
        Evaluate evaluate = new Evaluate();
        try {
            DruidUtil druidUtil= null;
            DataSource dataSource=druidUtil.getDataSource();
            con = dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,ev_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                evaluate.setEv_id(rs.getInt("ev_id"));
                evaluate.setSt_id(rs.getString("st_id"));
                evaluate.setCo_id(rs.getString("co_id"));
                evaluate.setSelf_eva(rs.getString("self_eva"));
                evaluate.setSub_time(rs.getString("sub_time"));
                evaluate.setTe_eva(rs.getString("te_eva"));
                evaluate.setTe_idea(rs.getString("te_idea"));
                evaluate.setTe_time(rs.getString("te_time"));
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
        return evaluate;
    }

    @Override
    public List<Evaluate> EVALUATE_LIST(String st_id) {
        String sql="select * from evaluate where st_id=?";
        Connection con = null;
        List<Evaluate> evaluateList=new ArrayList<>();
        try {
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con= dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,st_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Evaluate evaluate=new Evaluate();
                evaluate.setEv_id(rs.getInt("ev_id"));
                evaluate.setSt_id(rs.getString("st_id"));
                evaluate.setCo_id(rs.getString("co_id"));
                evaluate.setSelf_eva(rs.getString("self_eva"));
                evaluate.setSub_time(rs.getString("sub_time"));
                evaluate.setTe_eva(rs.getString("te_eva"));
                evaluate.setTe_idea(rs.getString("te_idea"));
                evaluate.setTe_time(rs.getString("te_time"));
                evaluateList.add(evaluate);
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
        return evaluateList;
    }

    @Override
    public List<Evaluate> TEVALUATE_LIST(String te_id) {
        String sql="select ev_id,st_id,evaluate.co_id,self_eva,sub_time,te_eva,te_idea,te_time " +
                "from evaluate,course " +
                "where evaluate.co_id = course.co_id and course.ct_id=?";
        Connection con = null;
        List<Evaluate> evaluateList=new ArrayList<>();
        try {
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con= dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,te_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Evaluate evaluate=new Evaluate();
                evaluate.setEv_id(rs.getInt("ev_id"));
                evaluate.setSt_id(rs.getString("st_id"));
                evaluate.setCo_id(rs.getString("co_id"));
                evaluate.setSelf_eva(rs.getString("self_eva"));
                evaluate.setSub_time(rs.getString("sub_time"));
                evaluate.setTe_eva(rs.getString("te_eva"));
                evaluate.setTe_idea(rs.getString("te_idea"));
                evaluate.setTe_time(rs.getString("te_time"));
                evaluateList.add(evaluate);
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
        return evaluateList;
    }


}