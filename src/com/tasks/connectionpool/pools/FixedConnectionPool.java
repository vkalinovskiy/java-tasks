package com.tasks.connectionpool.pools;

import com.tasks.connectionpool.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

public class FixedConnectionPool implements ConnectionPool {
    protected ConnectionFactory factory;
    protected ArrayList<ConnectionWithStatus> connections = new ArrayList<>();

    public FixedConnectionPool(ConnectionFactory factory, Integer connectionsSize) throws SQLException, ClassNotFoundException {
        this.factory = factory;
        createConnections(connectionsSize);
    }

    //TODO create a connection in getConnection method?
    protected void createConnections(Integer connectionsSize) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < connectionsSize; i++) {
            Connection connection = factory.getConnection();
            ConnectionWithStatus connectionWithStatus = new ConnectionWithStatus(connection, false);

            connections.add(connectionWithStatus);
        }
    }

    //TODO refactor
    public Connection getConnection() {
        Optional<ConnectionWithStatus> unusedConnection = connections
                .stream()
                .filter(ConnectionWithStatus::isUnused)
                .findFirst();

        if(unusedConnection.isEmpty()) {
            //TODO what an exception should use? or null?
            throw new RuntimeException("No Connections Available");
        }

        ConnectionWithStatus connectionWithStatus = unusedConnection.get();

        connectionWithStatus.setUsed(true);

        return connectionWithStatus.getConnection();
    }

//    boolean releaseConnection(Connection connection);
//    String getUrl();
//    <T> T execute(Function<Connection, T> f);

    protected static class ConnectionWithStatus {
        Connection connection;
        Boolean isUsed;

        ConnectionWithStatus(Connection connection, Boolean isUsed) {
            this.connection = connection;
            this.isUsed = isUsed;
        }

        public void setUsed(Boolean used) {
            isUsed = used;
        }

        public Boolean isUnused() {
            return !isUsed;
        }

        public Connection getConnection() {
            return connection;
        }
    }
}
