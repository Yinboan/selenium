package com.etc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    public Admin userLogin(Admin admin)
    {
        String sql="select * from admin where id=? and pw=?";
        ResultSet rs=null;
        Admin rsad=null;
        try
        {
            rs=DbUtil.exeQuery(sql,admin.getName(),admin.getPw());
            while(rs.next())
            {
                rsad=new Admin(rs.getString("id"),rs.getString("pw"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsad;
    }
}
