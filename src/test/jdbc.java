package mysql.test;

import javax.servlet.*;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
import java.io.IOException;
import java.util.*;


public class jdbc{
    public static void main(String[] args) {
        Connection con;
        String driver =getServletContext().getInitParameter("driver");
        String url = getServletContext().getInitParameter("url");
        String user = getServletContext().getInitParameter("username");
        String password = getServletContext().getInitParameter("password");
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);

            if(!con.isClosed()) {
                System.out.println("succeeded connecting to the Database!");
            }
            Statement statement = con.createStatement();
            String sql = "select * from info";
            ResultSet rs = statement.executeQuery(sql);

            String Sno = null;
            String Sname = null;
            while(rs.next()) {
                Sno = rs.getString("Rno");
                Sname = rs.getString("Rname");
                System.out.println(Sno + "  " + Sname);
            }
            rs.close();
            con.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        }catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }
}

