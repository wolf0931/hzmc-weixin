package com.hzmc.weixin.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hzmc.weixin.admin.util.AESUtil;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan("com.hzmc.weixin.admin.dao.mapper")
@PropertySource(value = {"classpath:datasource.properties"})
public class DataConfig {

	@Value("${primary.jdbc.driver}")
	private String primaryJdbcDriver;

	@Value("${primary.jdbc.url}")
	private String primaryJdbcURL;

	@Value("${primary.jdbc.user}")
	private String primaryJdbcUser;

	@Value("${primary.jdbc.pass}")
	private String primaryJdbcPass;

	private RelaxedPropertyResolver propertyResolver;

	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment, "mybatis.");
	}

	@Bean
	public DruidDataSource primaryDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(primaryJdbcDriver);
		dataSource.setUrl(primaryJdbcURL);
		dataSource.setUsername(primaryJdbcUser);
		dataSource.setPassword(AESUtil.AESDecode(primaryJdbcPass));
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(primaryDataSource());
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(primaryDataSource());
		sessionFactory.setTypeAliasesPackage("com.hzmc.weixin.admin.dao.model");
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:/com/hzmc/weixin/admin/dao/mapper/*.xml"));
		return sessionFactory;
	}
}                   