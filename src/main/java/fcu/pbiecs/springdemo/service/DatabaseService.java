package fcu.pbiecs.springdemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DatabaseService {
// 使用Spring的@Value註解來讀取application.properties中的資料庫連線資訊
    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    // 建立資料庫連線
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(datasourceUrl, username, password);
    }
}
