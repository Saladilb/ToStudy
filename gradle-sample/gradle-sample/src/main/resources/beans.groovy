

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import ru.itpark.repository.FilmRepositorySQLiteImpl

//beans {
//    xmlns([context:'http://www.springframework.org/schema/context'])
//
//    context.'property-placeholder'([location: 'file:jdbc.default.properties'])
//
//    dataSource(DriverManagerDataSource) {
//        url = '${jdbc.url}'
//        driverClassName = '${jdbc.driverClassName}'
//    }
//
//    jdbcTemplate(JdbcTemplate, ref(dataSource)) { }
//
//    namedParameterJdbcTemplate(NamedParameterJdbcTemplate, ref(dataSource)) { }
//
//    filmDAO(FilmRepositorySQLiteImpl) {
//        jdbcTemplate = ref(jdbcTemplate)
//        namedParameterJdbcTemplate = ref(namedParameterJdbcTemplate)
//    }
//}

beans {
    xmlns context: 'http://www.springframework.org/schema/context'

    context.'property-placeholder' 'location': 'file:jdbc.default.properties'

    dataSource DriverManagerDataSource, {
        url = '${jdbc.url}'
        driverClassName = '${jdbc.driverClassName}'
    }

    jdbcTemplate JdbcTemplate, ref(dataSource)

    namedParameterJdbcTemplate NamedParameterJdbcTemplate, ref(dataSource)

    filmDAO FilmRepositorySQLiteImpl, {
        jdbcTemplate = ref(jdbcTemplate)
        namedParameterJdbcTemplate = ref(namedParameterJdbcTemplate)
    }
}

// http://docs.grails.org/latest/guide/single.html#spring
// https://spring.io/blog/2014/03/03/groovy-bean-configuration-in-spring-framework-4
