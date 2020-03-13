package com.tasks.connectionpool.pools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public interface ConnectionPool {
    Connection getConnection() throws SQLException, ClassNotFoundException;
    void releaseConnection(Connection connection) throws SQLException, ClassNotFoundException;
    String getUrl();
    default  <T> T execute(Function<Connection, T> f) throws SQLException, ClassNotFoundException {
        if (f == null) {
            return null;
        }

        Connection connection = getConnection();
        var result = f.apply(connection);
        releaseConnection(connection);

        return result;
    }
}
