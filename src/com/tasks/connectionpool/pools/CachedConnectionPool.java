package com.tasks.connectionpool.pools;

import com.tasks.connectionpool.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CachedConnectionPool implements ConnectionPool {
    protected ConnectionFactory factory;
    protected List<InstantConnection> instantConnectionsList = new ArrayList<>();
    protected final Integer INITIAL_SIZE_CONNECTIONS;
    protected final Integer MAX_SIZE_CONNECTIONS;
    protected final Integer UNUSED_CONNECTION_LIFETIME;

    CachedConnectionPool(ConnectionFactory factory, Integer initialSize, Integer maxSize, Integer lifeTime) throws SQLException, ClassNotFoundException {
        this.factory = factory;
        this.INITIAL_SIZE_CONNECTIONS = initialSize;
        this.MAX_SIZE_CONNECTIONS = maxSize;
        this.UNUSED_CONNECTION_LIFETIME = lifeTime;

        createInitialConnections();
    }

    protected void createInitialConnections() throws SQLException, ClassNotFoundException {
        while (instantConnectionsList.size() < INITIAL_SIZE_CONNECTIONS) {
            createConnection();
        }
    }

    protected void createConnection() throws SQLException, ClassNotFoundException {
        if(instantConnectionsList.size() >= MAX_SIZE_CONNECTIONS) {
            throw new RuntimeException("Max size of instantConnectionsList exceeded!");
        }

        Connection connection = factory.getConnection();
        InstantConnection instantConnection = new InstantConnection(connection, false, UNUSED_CONNECTION_LIFETIME);

        instantConnectionsList.add(instantConnection);
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        filterInstantConnections();
        createInitialConnections();

        InstantConnection instantConnection = getUnusedInstantConnection();

        if(instantConnection == null) {
            createConnection();
        }

        instantConnection = getUnusedInstantConnection();

        if(instantConnection == null) {
            throw new RuntimeException("No available connection!");
        }

        instantConnection.setUsed(true);

        return instantConnection.getConnection();
    }

    public InstantConnection getUnusedInstantConnection() {
        Optional<InstantConnection> unusedInstantConnection = instantConnectionsList
                .stream()
                .filter(InstantConnection::isUnused)
                .findFirst();

        if(unusedInstantConnection.isEmpty()) {
            //TODO what an exception should use? or null?
            throw new RuntimeException("No Connections Available");
        }

        return unusedInstantConnection.get();
    }

    public void releaseConnection(Connection connection) throws SQLException, ClassNotFoundException {
        if(instantConnectionsList.size() < MAX_SIZE_CONNECTIONS) {
            if(connection.isClosed()) {
                connection = factory.getConnection();
            }

            InstantConnection instantConnection = new InstantConnection(connection, false, UNUSED_CONNECTION_LIFETIME);

            instantConnectionsList.add(instantConnection);
        }
    }

    protected void filterInstantConnections() {
        instantConnectionsList = instantConnectionsList.stream()
                .filter(i -> !i.isUnusedAndExpired())
                .collect(Collectors.toList());
    }

    public String getUrl() {
        return factory.getUrl();
    }

//    public <T> T execute(Function<Connection, T> f) {
//    }

    protected static class InstantConnection {
        Connection connection;
        Boolean isUsed;
        LocalDateTime lifeTime;

        InstantConnection(Connection connection, Boolean isUsed, Integer lifeTimeInSeconds) {
            this.connection = connection;
            this.isUsed = isUsed;
            this.lifeTime = LocalDateTime.now().plusSeconds(lifeTimeInSeconds);
        }

        public void setUsed(Boolean used) {
            isUsed = used;
        }

        public boolean isUnused() {
            return !isUsed;
        }

        public boolean isUnusedAndExpired() {
            return !isUsed && LocalDateTime.now().isBefore(this.lifeTime);
        }

        public Connection getConnection() {
            return connection;
        }
    }
}
