package Databases;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Map;


// TODO: CRIAR ALGO COMO T extends Interface
public abstract class BaseDatabase<T> {
    private static final String url = "jdbc:postgresql://localhost:5432/AnimalWatch";
    private static final String user = "postgres";
    private static final String password = "0319";

    protected DataSource criarDataSource(){
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    public abstract void cadastrarNovaEntidade(T entidade);
    public abstract Map<Integer, String> getNomeItensCadastrados();
    public abstract T getEntidadeDoBanco(int idEntidade);
    public abstract void deletarDoBanco(int idEntidade);
}
