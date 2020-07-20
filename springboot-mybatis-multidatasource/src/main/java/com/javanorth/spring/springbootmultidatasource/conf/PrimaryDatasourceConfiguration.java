package com.javanorth.spring.springbootmultidatasource.conf;


import com.javanorth.spring.springbootmultidatasource.util.DecryptUtil;
import com.javanorth.spring.springbootmultidatasource.util.LogUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@MapperScan(value = "com.javanorth.spring.springbootmultidatasource.dao.primary",
        sqlSessionFactoryRef = "primarySqlSessionFactory",
        sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class PrimaryDatasourceConfiguration {

    private static final String PRIMARY_MAPPER_LOCATION = "classpath:mappers/**/*.xml";

    @Value("${spring.datasource.db1.jdbc-url}")
    private String url;

    @Value("${spring.datasource.db1.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.db1.username}")
    private String username;

    @Value("${spring.datasource.db1.password}")
    private String password;

    /**
     * 创建主数据源
     * @return
     */
    @Primary
    @Bean(name = "primaryDatasource")
    @Qualifier("primaryDatasource")
    public DataSource primaryDatasource() {
        String pwd = DecryptUtil.decryptStr(password);
        LogUtil.info(PrimaryDatasourceConfiguration.class, "=========================");
        LogUtil.info(PrimaryDatasourceConfiguration.class, "Primary DataSource username: {}, password: {}", username, pwd);
        LogUtil.info(PrimaryDatasourceConfiguration.class, "=========================");
        return DataSourceBuilder.create().url(url)
                .driverClassName(driverClassName)
                .username(username)
                .password(pwd)
                .build();
    }

    @Primary
    @Bean(name = "primaryDataSourceTransactionManager")
    public DataSourceTransactionManager primaryDataSourceTransactionManager() {
        return new DataSourceTransactionManager(primaryDatasource());
    }

    /**
     * 创建sqlSessionFactory
     * @param primaryDatasource 数据源
     * @return sqlSessionFactory
     */
    @Primary
    @Bean(name = "primarySqlSessionFactory")
    @Qualifier("primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDatasource") DataSource primaryDatasource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(primaryDatasource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PrimaryDatasourceConfiguration.PRIMARY_MAPPER_LOCATION));
        Objects.requireNonNull(sessionFactory.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }

    @Primary
    @Bean(name = "primarySqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory
                                                                primarySqlSessionFactory) {
        return new SqlSessionTemplate(primarySqlSessionFactory);
    }




}
