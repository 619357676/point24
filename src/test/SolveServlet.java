package test;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Arrays;
import java.sql.*;

@WebServlet("/solve")
public class SolveServlet extends HttpServlet {
    private String diver;
    private String userName;
    private String password;
    private String url;
    private Connection conn;

    @Override
    public void init() throws ServletException {
        diver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://git.webturing.com:3306/ahstu";
        userName = "ahstu";
        password = "123456";


        try {
            Class.forName(diver);
            conn = DriverManager.getConnection(url, userName, password);
            System.err.println("写入数据库成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        try {
            a = Integer.parseInt(request.getParameter("A"));
            b = Integer.parseInt(request.getParameter("B"));
            c = Integer.parseInt(request.getParameter("C"));
            d = Integer.parseInt(request.getParameter("D"));

        } catch (Exception e) {

        }
        int[] arr = new int[]{a, b, c, d};
        Arrays.sort(arr);
        String key = Arrays.toString(arr);
        String result = query(key);
        if (result == null) {
            System.err.println("重新计算结果并写入数据库");
            result = test.Suanf.solve(a, b, c, d);
            save(key, result);
        }

        request.getSession().setAttribute("result", result);
        response.sendRedirect("index.jsp");

    }

    private boolean save(String key, String result) {
        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("insert into point24(numbers,solution) value(?,?)");

            ps.setString(1, key);
            ps.setString(2, result);
            int x = ps.executeUpdate();
            System.err.println("写入数据库成功");
            return x > 0;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("数据库链接失败");
            return false;
        }
    }

    private String query(String key) {
        String ans = null;

        try {
            Statement stmt = conn.createStatement();//key
            PreparedStatement ps = conn.prepareStatement("select solution from point24 where numbers=? LIMIT 1");
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ans = rs.getString(1);
                System.err.println("数据已经存在，直接读取数据库");

            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("数据库链接失败");
        }
        return ans;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
