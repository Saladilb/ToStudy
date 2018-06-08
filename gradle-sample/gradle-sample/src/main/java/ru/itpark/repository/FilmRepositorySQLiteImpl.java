package ru.itpark.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itpark.domain.Film;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;


@Repository
public class FilmRepositorySQLiteImpl implements FilmRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int create(String title, String description) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                "INSERT INTO films (title, description) VALUES (?, ?)",
                List.of(
                        new SqlParameter(Types.VARCHAR),
                        new SqlParameter(Types.VARCHAR)
                )
        );
        jdbcTemplate.update(
                preparedStatementCreatorFactory.newPreparedStatementCreator(List.of(title, description)),
                keyHolder
        );
        /*
        namedParameterJdbcTemplate.update(
                "INSERT INTO films (title, description) VALUES (:title, :description)",
                new MapSqlParameterSource(
                        Map.of("title", title, "description", description)
                ),
                keyHolder
        );
        */

        return keyHolder.getKey().intValue();
    }

    @Override
    public void create(List<Film> films) {
        jdbcTemplate.batchUpdate(
                "INSERT INTO films (title, description) VALUES (?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, films.get(i).getTitle());
                        ps.setString(2, films.get(i).getDescription());
                    }

                    @Override
                    public int getBatchSize() {
                        return films.size();
                    }
                }
        );
    }

    @Override
    public Film findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, title, description FROM films WHERE id = ?",
                new Object[] {id},
                (rs, rowNum) -> new Film(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description")
                )

        );
        /*
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT id, title, description FROM films WHERE id = :id",
                Map.of("id", id),
                (rs, rowNum) -> new Film(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description")
                )
        );
        */
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update(
                "DELETE FROM films WHERE id = ?",
                id
        );
        /*
        namedParameterJdbcTemplate.update(
                "DELETE FROM films WHERE id = :id",
                Map.of("id", id)
        );
        */
    }
}
