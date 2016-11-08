package lodeinfo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceBeans {

    @Bean(name = "newsDs")
    @Primary
    @ConfigurationProperties(prefix="news.datasource")
    public DataSource NewsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "megamagDs")
    @ConfigurationProperties(prefix="megamag.datasource")
    public DataSource MegamagDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "asteriskDs")
    @ConfigurationProperties(prefix = "asterisk.datasource")
    public DataSource AsteriskDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcMegamag")
    @Primary
    @Autowired
    public JdbcTemplate jdbcTemplateMegamag(@Qualifier("megamagDs") DataSource megamagDs) {
        return new JdbcTemplate(megamagDs);
    }

    @Bean(name = "jdbcAsterisk")
    @Autowired
    public JdbcTemplate jdbcTemplateAsterisk(@Qualifier("asteriskDs") DataSource asteriskDs) {
        return new JdbcTemplate(asteriskDs);
    }
}
