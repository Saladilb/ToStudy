

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource

beans {
    xmlns([context:'http://www.springframework.org/schema/context'])

    context.'property-placeholder'('location': 'file:jdbc.default.properties')
    context.'component-scan'('base-package': 'ru.itpark.repository')

    dataSource(DriverManagerDataSource) {
        url = '${jdbc.url}'
        driverClassName = '${jdbc.driverClassName}'
    }

    jdbcTemplate(JdbcTemplate, ref(dataSource)) { }

    namedParameterJdbcTemplate(NamedParameterJdbcTemplate, ref(dataSource)) { }
}