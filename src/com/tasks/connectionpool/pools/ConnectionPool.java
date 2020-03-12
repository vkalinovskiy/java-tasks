package com.tasks.connectionpool.pools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public interface ConnectionPool {
    Connection getConnection() throws SQLException, ClassNotFoundException;
    void releaseConnection(Connection connection) throws SQLException, ClassNotFoundException;
    String getUrl();
    default  <T> T execute(Function<Connection, T> f) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        if(f == null) {
            f = v -> {
                try {
                    v.close();
                    releaseConnection(v);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                return null;
            };
        }

        return f.apply(connection);
    }
}
