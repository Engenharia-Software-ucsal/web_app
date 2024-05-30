package com.rpEmpresa.web_app.database;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Component
public class PgConnection {

    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/rp_app_db";
        String user = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(url, user, password);

    }



}
