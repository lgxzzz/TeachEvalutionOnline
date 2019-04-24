package com.teaching.evaluation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcMgr {

    private String url = "jdbc:mysql://localhost:3306/TeachingEva";

    private String username = "root";

    private String password = "lgx5824068";

    private String driver = "com.mysql.jdbc.Driver";

    //java与数据库链接
    Connection conn;

    PreparedStatement pstm;

    ResultSet rs;

    public JdbcMgr()
    {
        try{
            //注册链接
            Class.forName(driver).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //获取链接
    public void getConnection(){
        try {
            conn = DriverManager.getConnection(url,username,password);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //关闭链接
    public void closeConnection(){
        try{
            if (conn!=null){
                conn.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Map<String,Object> QueryDB(String sql, List<Object> params){
        Map<String,Object> map = null;
        try{
            pstm = conn.prepareStatement(sql);
            if (params!=null){
                for (int i =0;i<params.size();i++){
                    pstm.setObject(i+1,params.get(i));
                }
            }
            rs = pstm.executeQuery();
            ResultSetMetaData rsData = rs.getMetaData();
            while(rs.next()){
                map = new HashMap<>();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

}
