package ru.itpark.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itpark.repository.FilmRepository;
import ru.itpark.repository.FilmRepositorySQLiteImpl;

import javax.sql.DataSource;

/**
 * Created by worker on 12/15/17.
 */
@PropertySource(value = "file:jdbc.default.properties")
@Configuration
public class Config {
    @Bean
    public DataSource dataSource(@Value("${jdbc.url}") String url, @Value("${jdbc.driverClassName}") String driverClassName) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driverClassName);
        driverManagerDataSource.setUrl(url);
        return driverManagerDataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Autowired DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public FilmRepository filmDAO(@Autowired JdbcTemplate jdbcTemplate, @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        FilmRepositorySQLiteImpl filmDAOSQLite = new FilmRepositorySQLiteImpl();
        filmDAOSQLite.setJdbcTemplate(jdbcTemplate);
        filmDAOSQLite.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        return filmDAOSQLite;
    }
}
