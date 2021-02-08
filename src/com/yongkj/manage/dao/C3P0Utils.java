package com.yongkj.manage.dao;

import java.sql.*;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {

    private static ComboPooledDataSource c3p0 = null;

    static {
        try {
            c3p0 = new ComboPooledDataSource();
            //通过setter方法配置
            c3p0.setDriverClass("com.mysql.jdbc.Driver");
            c3p0.setJdbcUrl("jdbc:mysql://localhost:3306/edu_chinasofti?useUnicode=true&characterEncoding=UTF8&useSSL=true");
            c3p0.setUser("root");
            c3p0.setPassword("root");
            c3p0.setInitialPoolSize(30);
            c3p0.setMaxPoolSize(40);
            c3p0.setMinPoolSize(10);
        }catch(Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return C3P0Utils.c3p0.getConnection();
    }

    public static void release(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        connection = null;
    }

    public static void release(Statement statement) {
        try {
            if(statement != null) {
                statement.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        statement = null;
    }

    public static void release(ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        resultSet = null;
    }

    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if(connection != null) {
                connection.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        connection = null;

        try {
            if(statement != null) {
                statement.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        statement = null;

        try {
            if(resultSet != null) {
                resultSet.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        resultSet = null;
    }

    public static void closeAll(Connection connection, PreparedStatement preparedStatement) {
        try {
            if(connection != null) {
                connection.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        connection = null;

        try {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        preparedStatement = null;
    }
}
