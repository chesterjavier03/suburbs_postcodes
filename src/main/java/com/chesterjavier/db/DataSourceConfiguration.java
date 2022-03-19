package com.chesterjavier.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by chesterjavier
 */

@Configuration
@Slf4j
public class DataSourceConfiguration {

  private static final String DB_SCRIPT_PATH = "classpath:suburbs-db.sql";

  @Bean
  @Primary
  public DataSource datasource() {
    DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
        .addScript(DB_SCRIPT_PATH).build();
    log.info("DataSource initialized.");
    return dataSource;
  }
}