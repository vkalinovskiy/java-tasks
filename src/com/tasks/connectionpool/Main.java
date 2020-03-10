package com.tasks.connectionpool;

import com.tasks.connectionpool.pools.FixedConnectionPool;

import java.sql.*;

public class Main {

      public static void main(String[] args) throws SQLException, ClassNotFoundException {
            ConnectionFactory factory = new ConnectionFactory.Builder()
                    .setUsername("root")
                    .setPassword("irresistible")
                    .setDatabaseUrl("jdbc:mysql://localhost:3306/jdbc_test")
                    .setDriverClassName("com.mysql.cj.jdbc.Driver")
                    .build();

            FixedConnectionPool connectionPool = new FixedConnectionPool(factory, 5);
            Connection connection = connectionPool.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM posts");

            while (resultSet.next()) {
                  String title = resultSet.getString("title");

                  System.out.println("\n================\n");
                  System.out.println("Title: " + title);
            }

            resultSet.close();
            statement.close();
            connection.close();
      }
}
