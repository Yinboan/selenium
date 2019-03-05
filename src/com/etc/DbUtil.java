package com.etc;

import java.sql.*;

public class DbUtil {
    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;

    //获取连接connection
    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.214:1521:XE","scott","tiger");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //查询
    public static ResultSet exeQuery(String sql, Object... params) {
        //获取数据库联接
        conn = getConnection();
        try {
            //preparedStatement 预编译sql
            ps = conn.prepareStatement(sql);

            //ps 插入参数
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            //执行sql
            rs = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 增删改操作
     *
     * @param sql    操作语句
     * @param params 语句中的问号对应的实际值
     * @return 数据库中受影响的记录数
     */
    public static int exeUpdate(String sql, Object... params) {
        int rows = 0;
        //获取数据库联接
        conn = getConnection();
        try {
            //preparedStatement 预编译sql
            ps = conn.prepareStatement(sql);

            //ps 插入参数
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            //执行sql （insert update delete）
            rows = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭联接
            closeConnection();
        }

        //返回结果
        return rows;
    }

    //关闭资源
    public static void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
