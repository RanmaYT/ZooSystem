package Databases;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public abstract class BaseDatabase<T> {
    private static final String url = "jdbc:postgresql://localhost:5432/AnimalWatch";
    private static final String user = "postgres";
    private static final String password = "0319";

    public DataSource criarDataSource(){
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setURL(url);

        return dataSource;
    }

}
