import org.sqlite.SQLiteDataSource

beans {
    dataSource(SQLiteDataSource) {
        url = 'jdbc:sqlite:file:tests?mode=memory&cache=shared'
    }
}