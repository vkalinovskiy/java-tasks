package com.tasks.connectionpool.pools;

import com.tasks.connectionpool.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CachedConnectionPool implements ConnectionPool {
    protected ConnectionFactory factory;
    protected ConcurrentLinkedQueue<Connection> connectionsQueue = new ConcurrentLinkedQueue<>();

    protected Integer initialConnectionsSize;
    protected Integer maxConnectionsSize;
    protected AtomicInteger currentConnectionsSize = new AtomicInteger(0);

    CachedConnectionPool(ConnectionFactory factory, Integer initialSize, Integer maxSize) throws SQLException, ClassNotFoundException {
        this.factory = factory;
        this.initialConnectionsSize = initialSize;
        this.maxConnectionsSize = maxSize;

        while (currentConnectionsSize.get() < initialSize) {
            createConnection();
        }
    }

    protected Connection createConnection() throws SQLException, ClassNotFoundException {
        Connection connection = factory.getConnection();
        connectionsQueue.add(connection);
        currentConnectionsSize.incrementAndGet();
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = getConnectionRecursive();

        if(connection == null) {
            throw new RuntimeException("No available connections!");
        }

        return connection;
    }

    /**
     * Gets the connection from queue.
     * Creating it if it does not exist.
     * Return null if limit of connections exceeded.
     *
     * @return Connection, null if limit exceeded
     * @throws SQLException
     * @throws ClassNotFoundException
     */
//    protected Connection getConnectionRecursive() throws SQLException, ClassNotFoundException {
//        Connection connection = connectionsQueue.poll();
//
//        if(connection == null && currentConnectionsSize.get() < maxConnectionsSize) {
//            createConnection();
//            connection = getConnectionRecursive();
//        }
//
//        return connection;
//    }

    public void releaseConnection(Connection connection) throws SQLException, ClassNotFoundException {
        if(connection == null) {
            throw new UnsupportedOperationException("Connection cannot be null");
        }

        //TODO reduce to initial
        if(currentConnectionsSize.get() >= initialConnectionsSize) {
            connection.close();
            currentConnectionsSize.decrementAndGet();
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
}
