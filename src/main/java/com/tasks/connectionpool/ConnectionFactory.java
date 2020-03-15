package com.tasks.connectionpool;

import java.sql.*;

public class ConnectionFactory {
    protected String username;
    protected String password;
    protected String databaseUrl;
    protected String driver;

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(this.driver);

        return DriverManager.getConnection(databaseUrl, username, password);
    }

    public String getUrl() {
        return this.databaseUrl;
    }

    public static class Builder {
        private ConnectionFactory factory;

        public Builder() {
            factory = new ConnectionFactory();
        }

        public Builder setUsername(String username) {
            factory.username = username;

            return this;
        }

        public Builder setPassword(String password) {
            factory.password = password;

            return this;
        }

        public Builder setDatabaseUrl(String url) {
            factory.databaseUrl = url;

            return this;
        }

        public Builder setDriverClassName(String driver) {
            factory.driver = driver;

            return this;
        }

        public ConnectionFactory build() {
            return factory;
        }
    }
}
