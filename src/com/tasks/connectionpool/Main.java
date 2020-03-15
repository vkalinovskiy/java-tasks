package com.tasks.connectionpool;

import com.tasks.connectionpool.pools.CachedConnectionPool;
import java.sql.*;

public class Main {

      public static void main(String[] args) throws SQLException, ClassNotFoundException {
            ConnectionFactory factory = new ConnectionFactory.Builder()
                    .setUsername("root")
                    .setPassword("irresistible")
                    .setDatabaseUrl("jdbc:mysql://localhost:3306/jdbc_test")
                    .setDriverClassName("com.mysql.cj.jdbc.Driver")
                    .build();

            CachedConnectionPool connectionPool = new CachedConnectionPool(factory, 5, 10);
            int result = connectionPool.execute(v -> {
                  int countRows = 0;

                  try {
                        Statement statement = v.createStatement();
                        ResultSet rs = statement.executeQuery("SELECT COUNT(*) as countRows FROM posts");
                        rs.next();
                        countRows = rs.getInt("countRows");
                        statement.close();
                        rs.close();
                        connectionPool.releaseConnection(v);
                  } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                  }

                  return countRows;
            });

            System.out.println("Rows in posts table = " + result);
      }
}
