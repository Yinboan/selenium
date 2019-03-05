package Jdbc;

import java.sql.*;

public class DButil {
     private static Connection conn=null;
    private static PreparedStatement ps;
    private static ResultSet rs=null;


    public static void close()
    {
        try {
            if(rs!=null)
            {
                rs.close();
            }
            if(ps!=null)
            {
                ps.close();
            }
            if(conn!=null)
            {
                conn.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



     public static  Connection getConn() {

         try {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.214:1521:XE", "scott", "tiger");

         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         }
     return conn;
     }

    public  static void selectdept(String sql)
    {
        Connection con=getConn();
        Statement stm=null;
        ResultSet rs=null;

        try {
           stm=con.createStatement();
           rs=stm.executeQuery(sql);
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+","+rs.getString(3));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(stm!=null)
            {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(con!=null)
            {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

//
//    public static void main(String[] args) {
//        selectdept("select  empno,ename,sal,dname from  emp e,dept d where e.deptno=d.deptno and sal>3000");
//        excute();
//    }


}
