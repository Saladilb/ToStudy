package ru.itpark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import ru.itpark.domain.Film;
import ru.itpark.repository.FilmRepository;

import java.sql.SQLException;
import java.util.List;

@ContextConfiguration(locations = {"classpath:beans.xml", "classpath:tests.xml"})
@ExtendWith(SpringExtension.class)
public class XMLConfigSQLiteDBTest {
    @Autowired
    ApplicationContext context;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void beforeEach() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS films");
        jdbcTemplate.execute("CREATE TABLE films ( id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, description TEXT)");
    }

    @Test
    public void testInMemoryDb() throws SQLException {
        Assertions.assertTrue(jdbcTemplate.getDataSource().getConnection().getMetaData().getURL().startsWith("jdbc:sqlite:file:tests"));
    }

    @Test
    public void testBatchUpdate(@Autowired FilmRepository filmRepository) {
        filmRepository.create(
                List.of(
                        new Film(0, "demo", "second"),
                        new Film(0, "demo", "second"),
                        new Film(0, "demo", "second"),
                        new Film(0, "demo", "second"),
                        new Film(0, "demo", "second")
                )
        );

//        Assertions.assertEquals(5, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "films", "title = 'demo'"));
        Assertions.assertEquals(6, JdbcTestUtils.countRowsInTable(jdbcTemplate, "films"));
    }
}
