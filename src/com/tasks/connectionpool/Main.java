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
            int result = connectionPool.execute(v -> {
                  int countRows = 0;

                  try {
                        Statement statement = v.createStatement();
                        ResultSet rs = statement.executeQuery("SELECT COUNT(*) as countRows FROM posts");
                        rs.next();
                        countRows = rs.getInt("countRows");
                        statement.close();
                        rs.close();
                  } catch (SQLException e) {
                        e.printStackTrace();
                  }

                  return countRows;
            });

            System.out.println("Rows in posts table = " + result);
      }
}
