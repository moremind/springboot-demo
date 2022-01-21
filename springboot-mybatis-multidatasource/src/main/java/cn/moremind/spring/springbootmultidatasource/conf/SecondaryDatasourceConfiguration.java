package cn.moremind.spring.springbootmultidatasource.conf;

import cn.moremind.spring.springbootmultidatasource.util.DecryptUtil;
import cn.moremind.spring.springbootmultidatasource.util.LogUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@MapperScan(value = "com.javanorth.spring.springbootmultidatasource.dao.secondary",
        sqlSessionFactoryRef = "secondarySqlSessionFactory",
        sqlSessionTemplateRef = "secondarySqlSessionTemplate")
public class SecondaryDatasourceConfiguration {

    private static final String SECOND_MAPPER_LOCATION = "classpath:mappers/**/*.xml";

    @Value("${spring.datasource.db2.jdbc-url}")
    private String url;

    @Value("${spring.datasource.db2.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.db2.username}")
    private String username;

    @Value("${spring.datasource.db2.password}")
    private String password;

    /**
     * 创建主数据源
     * @return
     */
    @Qualifier("secondaryDatasource")
    @Bean(name = "secondaryDatasource")
    public DataSource secondaryDatasource() {
        String pwd = DecryptUtil.decryptStr(password);
        LogUtil.info(SecondaryDatasourceConfiguration.class, "=========================");
        LogUtil.info(SecondaryDatasourceConfiguration.class, "Secondary DataSource username: {}, password: {}", username, pwd);
        LogUtil.info(SecondaryDatasourceConfiguration.class, "=========================");
        return DataSourceBuilder.create().url(url)
                .driverClassName(driverClassName)
                .username(username)
                .password(pwd)
                .build();
    }

    @Bean(name = "secondaryDataSourceTransactionManager")
    public DataSourceTransactionManager secondaryDataSourceTransactionManager() {
        return new DataSourceTransactionManager(secondaryDatasource());
    }

    /**
     * 创建sqlSessionFactory
     * @param secondaryDatasource 数据源
     * @return sqlSessionFactory
     */
    @Bean(name = "secondarySqlSessionFactory")
    @Qualifier("secondarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("secondaryDatasource") DataSource secondaryDatasource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(secondaryDatasource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(SecondaryDatasourceConfiguration.SECOND_MAPPER_LOCATION));
        Objects.requireNonNull(sessionFactory.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }

    @Bean(name = "secondarySqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory
                                                                secondarySqlSessionFactory) {
        return new SqlSessionTemplate(secondarySqlSessionFactory);
    }

}


