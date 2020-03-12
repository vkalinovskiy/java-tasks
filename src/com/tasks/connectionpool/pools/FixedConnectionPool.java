package com.tasks.connectionpool.pools;

import com.tasks.connectionpool.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FixedConnectionPool implements ConnectionPool {
    protected ConnectionFactory factory;
    protected ArrayDeque<Connection> connectionsQueue = new ArrayDeque<>();
//    protected ConcurrentLinkedQueue<Connection> connectionsQueue;
    protected Integer limitSize;

    public FixedConnectionPool(ConnectionFactory factory, Integer limitConnections) throws SQLException, ClassNotFoundException {
        this.factory = factory;
        limitSize = limitConnections;
        createConnections(limitConnections);
    }

    protected void createConnections(Integer limit) throws SQLException, ClassNotFoundException {
        while (connectionsQueue.size() < limit) {
            Connection connection = factory.getConnection();
            connectionsQueue.add(connection);
        }
    }

    public Connection getConnection() {
        Connection connection = connectionsQueue.pollFirst();
        
        if(connection == null) {
            throw new RuntimeException("No available connections!");
        }

        return connection;
    }

    public void releaseConnection(Connection connection) throws SQLException, ClassNotFoundException {
        if(connection == null || connectionsQueue.size() >= limitSize) {
            return;
        }

        if(connection.isClosed()) {
            connection = factory.getConnection();
        }

        connectionsQueue.add(connection);
    }

    public String getUrl() {
        return factory.getUrl();
    }

//    <T> T execute(Function<Connection, T> f);
}
