package Jdbc;

import com.etc.DbUtil;

import java.io.RandomAccessFile;
import java.sql.*;
import java.util.Scanner;

public class Login {
    public static Connection con;
    public static PreparedStatement ps;

    public static void login() {
        Connection con = DB.getconn();
        Scanner in = new Scanner(System.in);
        String sql = null;
        System.out.println("*******login");
        Statement stm = null;
        ResultSet rs = null;
        System.out.println("please input id:");
        String id = in.next();
        System.out.println("please input pw:");
        String pw = in.next();
        sql = "select pw from users where id='" + id + "'";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs.next()) {
//                System.out.println(rs.getString(1));
//                System.out.println(pw);
                if (rs.getString(1).equals(pw)) {
                    System.out.println("successful login!");
                    SelfView(id);
                } else {
                    System.out.println("password error!");
                }
            } else {
                System.out.println("no user!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void SelfView(String id) {
        Scanner in=new Scanner(System.in);
        System.out.println("hello  "+id);
        System.out.println("*****update_self************(Press 1)");
        System.out.println("*****delete_self*********(Press 2)");
        System.out.println("*****EXIT*********(Press 3)");
        String sympo = in.next();
        if (sympo.equals("1")) {
            update_self(id);
        } else if (sympo.equals("2")) {
            delete_self(id);
        } else if (sympo.equals("3")) {
           return;
        } else {
            System.out.println("other option!");
        }
    }
    private static void update_self(String id) {
        Scanner in=new Scanner(System.in);
        System.out.println("please input newid:");
        String newname = in.next();
        System.out.println("please input newpw:");
        String newpw = in.next();
        ResultSet rs=DB.execute("select * from users where id=?",newname);
            try {
            if (rs.next())
            {
                System.out.println("name have same!can not modify!");

            }
            else
            {
                String  sql="update users set id=?,pw=? where id=?";
                int row=DB.exeupdate(sql,newname,newpw,id);
                System.out.println("successful modify!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void delete_self(String id) {
        Scanner in =new Scanner(System.in);
        System.out.println("Are you really want to delete the infomation of"+id+"Y or N");
        String option=in.next();
        if (option.equals("Y"))
        {
            String sql="delete users where id=?";
            if (DB.exeupdate(sql,id)>0)
            {
                System.out.println("successful delete"+DB.exeupdate(sql,id)+"record ");
            }

        }
        else if(option.equals("N"))
        {
            System.out.println("Fail delete");
        }
        else
        {
            System.out.println("unknow char");
        }
    }




    public static void register2() {
        Scanner in = new Scanner(System.in);
        System.out.println("*******register");
        System.out.println("please input id:");
        String id = in.next();
        System.out.println("please input pw:");
        String pw = in.next();
        String sql = "select * from users where id=?";
        ResultSet rs = DB.execute(sql, id);
        try {
            if (rs.next()) {
                System.out.println("same userid!!!");
                register2();
                //Sreturn;
            } else {
                System.out.println("no record");
                sql = "insert into users values(?,?)";
                int result = register(sql, id, pw);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close();
        }

    }

    public static int register(String sql, Object... params) {

        int result = DB.exeupdate(sql, params[0], params[1]);

        if (result > 0) {
            System.out.println("insert   " + result + "  recod");
        } else {
            System.out.println("insert error");
        }
        return result;
    }


    public static void delete() {
        Scanner in = new Scanner(System.in);
        System.out.println("*******delete");
        System.out.println("please input id:");
        String id = in.next();
        String sql = "select * from users where id=?";
        ResultSet rs = DB.execute(sql, id);
        try {
            if (rs.next())
            {
                System.out.println("Start Delete");
                sql="delete from users where id=?";
                 realdelete(sql,id);
            }
            else
            {
                System.out.println("no user");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.close();
        }

    }

    private static void realdelete(String sql,Object... params) {
             con=DB.getconn();
             int row=-1;
             try
             {
                  row=DB.exeupdate(sql,params[0]);
             }
             catch(Exception e)
             {
                 e.getStackTrace();
             }
           if (row>0)
           {
               System.out.println("Already delete "+params[0]);
           }
           else {
               System.out.println("delete fail!");
           }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("*************Welcome To The infomation System of shenyan University of techonology******************");
        System.out.println("*****login************(Press 1)");
        System.out.println("*****register*********(Press 2)");
        System.out.println("*****delete*********(Press 3)");
        String sympo = in.next();
        if (sympo.equals("1")) {
            login();
        } else if (sympo.equals("2")) {
            register2();
        } else if (sympo.equals("3")) {
            delete();
        } else {
            System.out.println("other option!");
        }


    }


}
