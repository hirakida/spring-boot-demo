package com.example;

import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.distribution.Version;

@TestConfiguration
public class DataSourceTestConfig {

    @Bean(destroyMethod = "stop")
    public EmbeddedMysql embeddedMysql(DataSourceProperties properties) {
        String[] url = properties.getUrl().split(":");
        String port = url[3].split("/")[0];
        String db = url[3].split("/")[1];
        MysqldConfig mysqldConfig = MysqldConfig.aMysqldConfig(Version.v5_7_latest)
                                                .withCharset(Charset.UTF8)
                                                .withPort(Integer.valueOf(port))
                                                .withUser(properties.getUsername(),
                                                          properties.getPassword())
                                                .withTimeZone(TimeZone.getDefault())
                                                .build();

        return EmbeddedMysql.anEmbeddedMysql(mysqldConfig)
                            .addSchema(db,
                                       ScriptResolver.classPathScript("schema.sql"),
                                       ScriptResolver.classPathScript("data.sql"))
                            .start();
    }

    @DependsOn("embeddedMysql")
    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }
}
