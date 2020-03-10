package com.tasks.connectionpool.pools;

import com.tasks.connectionpool.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public class CachedConnectionPool implements ConnectionPool {
    protected ConnectionFactory factory;
    protected Integer initialConnectionsSize;
    protected Integer maxConnectionSize;
    protected Integer unusedConnectionLifetime;

    CachedConnectionPool(ConnectionFactory factory) {
        this.factory = factory;
    }

    public Connection getConnection() throws SQLException {

    }



//
//    public boolean releaseConnection(Connection connection) {
//    }
//
//    public String getUrl() {
//    }
//    public <T> T execute(Function<Connection, T> f) {
//    }
}
