package com.tasks.connectionpool.pools;
import com.tasks.connectionpool.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CachedConnectionPool implements ConnectionPool {
    protected ConnectionFactory factory;
    protected ConcurrentLinkedQueue<Connection> connectionsQueue = new ConcurrentLinkedQueue<>();
    protected Integer initialConnectionsSize;
    protected Integer maxConnectionsSize;
    protected AtomicInteger currentConnectionsSize = new AtomicInteger(0);

    public CachedConnectionPool(ConnectionFactory factory, Integer initialSize, Integer maxSize)
            throws SQLException, ClassNotFoundException {
        this.factory = factory;
        this.initialConnectionsSize = initialSize;
        this.maxConnectionsSize = maxSize;

        while (currentConnectionsSize.get() < initialSize) {
            Connection connection = createConnection();
            connectionsQueue.add(connection);
        }
    }

    protected Connection createConnection() throws SQLException, ClassNotFoundException {
        Connection connection = factory.getConnection();
        currentConnectionsSize.incrementAndGet();

        return connection;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = connectionsQueue.poll();

        if (connection == null) {
            if (currentConnectionsSize.get() < maxConnectionsSize) {
                connection = createConnection();
            } else {
                throw new RuntimeException("No available connections!");
            }
        }

        return connection;
    }

    public void releaseConnection(Connection connection) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            throw new UnsupportedOperationException("Connection cannot be null");
        }

        if (connectionsQueue.size() >= initialConnectionsSize) {
            connection.close();
            currentConnectionsSize.decrementAndGet();
            return;
        }

        if (connection.isClosed()) {
            connection = factory.getConnection();
        }

        connectionsQueue.add(connection);
    }

    public String getUrl() {
        return factory.getUrl();
    }
}
