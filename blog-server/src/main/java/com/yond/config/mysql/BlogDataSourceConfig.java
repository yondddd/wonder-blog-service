package com.yond.config.mysql;

import com.yond.env.env.Environment;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.yond.mapper", sqlSessionFactoryRef = "blogSqlSessionFactory")
public class BlogDataSourceConfig {

    @Bean(name = "blogDataSource", initMethod = "", destroyMethod = "")
    @Primary
    public DataSource getDateSource1() {
        HikariDataSource dataSource = new HikariDataSource();
        String hostname = Environment.getProperty("HOSTNAME");
        String host;
        String password;
        if (StringUtils.isNotBlank(hostname)) {
            host = "172.27.234.224";
            password = "mysqldddd";
        } else {
            host = "192.168.56.10";
            password = "1234dddd";
        }
        dataSource.setJdbcUrl("jdbc:mysql://" + host + ":3306/nblog?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true");
        dataSource.setUsername("operator");
        dataSource.setPassword(password);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setMaximumPoolSize(10);
        return dataSource;
    }

    @Bean(name = "blogSqlSessionFactory")
    @Primary
    public SqlSessionFactory mainSqlSessionFactory(@Qualifier("blogDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean("blogSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mainSqlSessionTemplate(
            @Qualifier("blogSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
