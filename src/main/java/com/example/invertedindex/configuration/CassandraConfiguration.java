package com.example.invertedindex.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration {
    @Value("localhost")
    private String contactPoints;
    @Value("9042")
    private int port;
    @Value("documents")
    private String keySpace;
    @Value("org.baeldung.spring.data.cassandra.repository")
    private String basePackages;
    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }
    @Override
    protected String getContactPoints() {
        return contactPoints;
    }
    @Override
    protected int getPort() {
        return port;
    }
    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }
    @Override
    public String[] getEntityBasePackages() {
        return new String[]{basePackages};
    }
}