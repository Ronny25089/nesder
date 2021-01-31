package com.nesder;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.nesder.config.DatasourceConfig;

@SpringBootApplication
@MapperScan(basePackages = "com.nesder.dao.repository")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class NesderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NesderApplication.class, args);
	}

	@Autowired
	DatasourceConfig dataBaseConfig;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml");

		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		
		factoryBean.setMapperLocations(resources);
		factoryBean.setDataSource(dataSource());
		return factoryBean.getObject();
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DataSource dataSource = dataBaseConfig.createDataSource();
		return dataSource;
	}
}
