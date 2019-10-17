package com.nesder.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataBaseConfig {

	private String driverClassName;
	private String url;
	private String username;
	private String password;

	@Bean
	@Primary
	public DataSource createDataSource() {
		return DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username)
				.password(password).build();
	}

	@Bean
	@Primary
	public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
