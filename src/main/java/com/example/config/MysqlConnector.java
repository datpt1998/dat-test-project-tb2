package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class MysqlConnector {
    @Value("${mysql.host}")
    private String host;

    @Value("${mysql.port}")
    private String port;

    @Value("${mysql.database}")
    private String database;

    @Value("${mysql.username}")
    private String username;

    @Value("${mysql.password}")
    private String password;

    public Connection getMySQLConnection() {
        Connection conn=null;
        try {
            String templateConnectionString="jdbc:mysql://%s:%s/%s";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(String.format(templateConnectionString, host, port, database, username, password));
            System.out.println("connect successfully!");
        }catch(Exception e) {
            System.out.println("connect failure!");
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(resultSet!=null&&!resultSet.isClosed()){
                resultSet.close();
            }
            if(statement!=null&&!statement.isClosed()){
                resultSet.close();
            }
            if(connection!=null&&!connection.isClosed()){
                connection.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
