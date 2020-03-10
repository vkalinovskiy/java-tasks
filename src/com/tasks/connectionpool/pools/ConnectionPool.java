package com.tasks.connectionpool.pools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public interface ConnectionPool {
    Connection getConnection() throws SQLException;
//    boolean releaseConnection(Connection connection);
//    String getUrl();
//    <T> T execute(Function<Connection, T> f);
}
