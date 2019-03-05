package Jdbc;

import java.sql.*;

public class DB {
    public static Connection con;
    public static Statement stm;
    public static PreparedStatement ps;
    public static ResultSet rs;

    public static Connection getconn() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.214:1521:XE", "scott", "tiger");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet execute(String sql,Object... params)
     {
        Connection con=getconn();

         try {
             ps=con.prepareStatement(sql);

             for(int i=0;i<params.length;i++)
             {
                 ps.setObject(i+1,params[i]);
             }
             rs=ps.executeQuery();

         } catch (SQLException e) {
             e.printStackTrace();
         }


          return rs;
     }

     public static int exeupdate(String sql,Object... params)
     {

         int row = 0;
         con=getconn();
         try {
             ps=con.prepareStatement(sql);
             for(int i=0;i<params.length;i++)
             {
                 ps.setObject(i+1,params[i]);
             }
             row=ps.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         }
        return row;
     }

}
