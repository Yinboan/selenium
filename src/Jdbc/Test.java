package Jdbc;

import java.net.ConnectException;
import java.sql.*;

public class Test {


    public  static void selectdept()
    {
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.214:1521:XE","scott","tiger");
            stm=con.createStatement();
            String  sql="select  empno,ename,sal,dname from  emp e,dept d where e.deptno=d.deptno and sal>3000";
            rs=stm.executeQuery(sql);
             while(rs.next())
             {
                 System.out.println(rs.getInt(1)+" "+rs.getString(2)+","+rs.getString(3));
             }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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









    public static void pconn(int dn,String name,String loc)
    {
        Connection con=null;
        PreparedStatement stm=null;
        String sql ="insert into dept values(?,?,?)";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.214:1521:XE","scott","tiger");
            stm=con.prepareStatement(sql);
            stm.setInt(1,dn);
            stm.setString(2,name);
            stm.setString(3,loc);
            stm.executeUpdate(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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






    public static void conn( String  sql)
    {
        Connection con=null;
        Statement stm=null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.214:1521:XE","scott","tiger");
            stm=con.createStatement();
            stm.executeUpdate(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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

    public  static void drop(String name)
    {
          String sql=" delete dept where dname='"+name+"'";
          conn(sql);
    }

    public  static void updatedapt(int dn,String name)
    {
        Connection con=null;
        Statement stm=null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.214:1521:XE","scott","tiger");
            stm=con.createStatement();
            String  sql="update dept set dname='"+name+"' where deptno="+dn;
            stm.executeUpdate(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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






    public static void Insertdept(int deptno, String deptname, String loc) {
        Connection conn = null;
        Statement stmt = null;
        try {//加载驱动类
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.214:1521:XE", "scott", "tiger");

            stmt = conn.createStatement();

            String sql = "insert into dept values(" + deptno + ",'" + deptname + "','"+loc+"')";

            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    public static void main(String[] args) {
      //  Insertdept(22,"god","china");
        //updatedapt(22,"godfather");
      //  drop("godfather");
     //   pconn(22,"god","china");
        selectdept();
    }

}