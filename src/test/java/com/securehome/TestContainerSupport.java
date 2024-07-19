package com.securehome;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

@ActiveProfiles("test")
public abstract class TestContainerSupport {

    private static final String MYSQL_IMAGE_NAME = "mysql:8";

    private static  MySQLContainer MY_SQL_CONTAINER = new MySQLContainer(MYSQL_IMAGE_NAME);

    static {
        MY_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.driver-class-name", MY_SQL_CONTAINER::getDriverClassName);
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
    }

}
