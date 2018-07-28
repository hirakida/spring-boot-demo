package com.example;

import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.distribution.Version;

@Configuration
public class AppConfig {

    @Bean(destroyMethod = "stop")
    public EmbeddedMysql embeddedMysql(DataSourceProperties dataSourceProperties) {
        String[] url = dataSourceProperties.getUrl().split(":");
        String port = url[3].split("/")[0];
        String db = url[3].split("/")[1];
        MysqldConfig mysqldConfig = MysqldConfig.aMysqldConfig(Version.v5_7_latest)
                                                .withCharset(Charset.UTF8)
                                                .withPort(Integer.valueOf(port))
                                                .withUser(dataSourceProperties.getUsername(),
                                                          dataSourceProperties.getPassword())
                                                .withTimeZone(TimeZone.getDefault())
                                                .build();

        return EmbeddedMysql.anEmbeddedMysql(mysqldConfig)
                            .addSchema(db,
                                       ScriptResolver.classPathScript("schema.sql"),
                                       ScriptResolver.classPathScript("data.sql"))
                            .start();
    }

    @Bean
    public DataSource dataSource(EmbeddedMysql embeddedMysql,
                                 DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
}
