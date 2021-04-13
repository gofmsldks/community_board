package com.community.community_board.configuration;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@PropertySource("classpath:/application.properties")

public class DBConfiguration {

    @Autowired
    private ApplicationContext applicationContext;
    //ApplicationContext는 스프링 컨테이너(Spring Container) 중 하나.
    //컨테이너는 사전적 의미로 무언가를 담는 용기 또는 그릇을 의미,
    //스프링 컨테이너는 빈(Bean)의 생성과 사용, 관계, 생명 주기 등을 관리.
    //빈(Bean)은 쉽게 이야기하면 객체.
    //예를 들어, 프로젝트에 100개의 클래스가 있다고 가정.
    //100개의 클래스 간의 의존적인 문제가 많으면 "결합도가 높다"라고 표현,
    //이러한 문제를 컨테이너에서 빈(Bean)을 주입받는 방법으로 해결.

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
        factoryBean.setTypeAliasesPackage("com.community.community_board.*");
        factoryBean.setConfiguration(mybatisConfg());

        return factoryBean.getObject();
    }
    // SqlSessionFactory: 데이터베이스의 커넥션과 SQL 실행에 대한 모든 것을 갖는 중요한 역할.

    // SqlSessionFactoryBean: 마이바티스와 스프링의 연동 모듈로 사용. 마이바티스 XML Mapper, 설정 파일 위치 등을 지정하고,
    // SqlSessionFactoryBean 자체가 아닌, getObject 메서드가 리턴하는 SqlSessionFactory를 생성.

    // setMapperLocations :getResources 메서드의 인자로 지정된 패턴에 포함되는 XML Mapper를 인식하도록 하는 역할

    // setTypeAliasesPackage:BoardMapper XML에서 parameterType과 resultType은 클래스의 풀 패키지 경로가 포함되어야 합니다. 해당 메서드를 사용해서 풀 패키지 경로를 생략.

    // 패키지의 패턴이 복잡하면 com.community.*.domain 하면 됨.

    // setConfiguration: 마이바티스 설정과 관련된 빈(Bean)을 설정 파일로 지정.


    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
        //1. SqlSessionTemplate은 마이바티스 스프링 연동 모듈의 핵심이다.
        //2. SqlSessionTemplate은 SqlSession을 구현하고,
        //    코드에서 SqlSession을 대체하는 역할을 한다.
        //3. SqlSessionTemplate은 쓰레드에 안전하고,
        //    여러 개의 DAO나 Mapper에서 공유할 수 있다.
        //4. 필요한 시점에 세션을 닫고, 커밋 또는 롤백하는 것을 포함한
        //    세션의 생명주기를 관리한다.
    }


    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfg() {
        return new org.apache.ibatis.session.Configuration();
        //mybatisConfig: application.properties에서 mybatis.configuration으로 시작하는 모든 설정을 읽어 들여 빈(Bean)으로 등록
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}

