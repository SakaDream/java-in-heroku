package com.sakadream.test.model;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sakadream.test.bean.Employee;

public class Database {
    Connection conn;
    Statement stmt;

    private void connect() throws Exception {
        Class.forName("org.postgresql.Driver");
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        conn = DriverManager.getConnection(dbUrl, username, password);
    }

    public List<Employee> showAllEmployees() throws Exception {
        List<Employee> list = new ArrayList<>();
        if (conn == null)
            connect();
        if (stmt == null)
            stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"EMPLOYEES\"");
        while (rs.next()) {
            Employee e = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getInt(6));
            list.add(e);
        }
        return list;
    }

    public void add(Employee e) throws Exception {

    }

    public void edit(Employee e) throws Exception {

    }

    public void cleanConnection() throws Exception {
        if (conn != null)
            conn.close();
        if (stmt != null)
            stmt.close();
    }

    public void echoQuery(String query) {
        System.out.println(query);
    }
}