package main.DAO.Impl;

import main.DAO.ChargeDao;
import main.Util.DruidUtil;
import main.pojo.Mentor;
import main.pojo.charge;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChargeDaoImpl implements ChargeDao {
    @Override
    public List<charge> findCharges(charge s) {
        String STUDENT_INSERT_SQL = "select * from charge where c_name=? and c_pwd=? ";
        Connection con = null;
        List<charge> a=new ArrayList<>();
        try{
            DruidUtil druidUtil=null;
            DataSource dataSource=druidUtil.getDataSource();
            con=dataSource.getConnection();//获取连接池;
            PreparedStatement psmt = con.prepareStatement(STUDENT_INSERT_SQL);
            psmt.setString(1, s.getC_name());
            psmt.setString(2, s.getC_pwd());
            ResultSet rs=psmt.executeQuery();
            while(rs.next()){
                charge t=new charge();
                t.setC_id(rs.getString(1));
                a.add(t);
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
        return a;
    }
}