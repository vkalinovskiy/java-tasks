package com.tasks.connectionpool.pools;

import com.tasks.connectionpool.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public class CachedConnectionPool implements ConnectionPool {
    protected ConnectionFactory factory;
    protected final Integer INITIAL_SIZE_CONNECTIONS;
    protected final Integer MAX_SIZE_CONNECTIONS;
    protected final Integer UNUSED_CONNECTION_LIFETIME;

    CachedConnectionPool(ConnectionFactory factory, Integer initialSize, Integer maxSize, Integer lifeTime) {
        this.factory = factory;
        this.INITIAL_SIZE_CONNECTIONS = initialSize;
        this.MAX_SIZE_CONNECTIONS = maxSize;
        this.UNUSED_CONNECTION_LIFETIME = lifeTime;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return factory.getConnection();
    }

//
//    protected void createConnection() throws SQLException, ClassNotFoundException {
//        if(availableConnections.size() >= MAX_SIZE_CONNECTIONS) {
//            throw new RuntimeException("Max size of availableConnections exceeded!");
//        }
//
//        Connection connection = factory.getConnection();
//        FixedConnectionPool.AvailableConnection createdConnection = new FixedConnectionPool.AvailableConnection(connection, false);
//
//        availableConnections.add(createdConnection);
//    }
//
//    //TODO make recursive?
//    public Connection getConnection() throws SQLException, ClassNotFoundException {
//        Connection connection = getUnusedConnection();
//
//        if(connection == null) {
//            createConnection();
//        }
//
//        connection = getUnusedConnection();
//
//        if(connection == null) {
//            throw new RuntimeException("No available connection!");
//        }
//
//        return connection;
//    }
//
//    protected Connection getUnusedConnection() {
//        Optional<FixedConnectionPool.AvailableConnection> unusedAvailableConnection = availableConnections
//                .stream()
//                .filter(FixedConnectionPool.AvailableConnection::isUnused)
//                .findAny();
//
//        if(!unusedAvailableConnection.isPresent()) {
//            return null;
//        }
//
//        FixedConnectionPool.AvailableConnection createdConnection = unusedAvailableConnection.get();
//
//        createdConnection.setUsed(true);
//
//        return createdConnection.getConnection();
//    }


//    public boolean releaseConnection(Connection connection) {
//    }
//
//    public String getUrl() {
//    }
//    public <T> T execute(Function<Connection, T> f) {
//    }
}
