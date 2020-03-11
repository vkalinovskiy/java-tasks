package com.tasks.connectionpool.pools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public interface ConnectionPool {
    Connection getConnection() throws SQLException, ClassNotFoundException;
    void releaseConnection(Connection connection) throws SQLException, ClassNotFoundException;
    String getUrl();
//    <T> T execute(Function<Connection, T> f);
}
