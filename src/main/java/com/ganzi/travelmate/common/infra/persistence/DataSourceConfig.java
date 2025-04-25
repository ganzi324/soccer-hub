package com.ganzi.travelmate.common.infra.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class DataSourceConfig {

    private final String MASTER_SOURCE = "master";
    private final String SLAVE_SOURCE = "slave";

    @Bean
    @Qualifier(MASTER_SOURCE)
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        log.info("MASTER database source register");
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier(SLAVE_SOURCE)
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource replicaDataSource() {
        log.info("SLAVE database source register");
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource routingDataSource(@Qualifier(MASTER_SOURCE) DataSource masterDataSource,
                                        @Qualifier(SLAVE_SOURCE) DataSource slaveDataSource) {

        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(MASTER_SOURCE, masterDataSource);
        dataSourceMap.put(SLAVE_SOURCE, slaveDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        DataSource determinedDataSource = routingDataSource(masterDataSource(), replicaDataSource());
        return new LazyConnectionDataSourceProxy(determinedDataSource);
    }
}
