package Databases;

import Exceptions.NenhumItemCadastradoException;
import Model.Animal;
import Model.Relato;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RelatoDatabase extends BaseDatabase<Relato>{
    @Override
    public void cadastrarNovaEntidade(Relato relato) {
        final String sql = "INSERT INTO Relato(relato_titulo, relato_texto, relato_animalRelatado)" +
                "VALUES (?,?,?)";

        DataSource dataSource = criarDataSource();

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, relato.getTituloRelato());
            preparedStatement.setString(2, relato.getTextoRelato());
            preparedStatement.setInt(3, relato.getAnimalRelatadoId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("FALHA AO SALVAR RELATO");
        }
    }

    @Override
    public Map<Integer, String> getNomeItensCadastrados() {
        Map<Integer, String> itensCadastrados = new HashMap<>();

        // Criar o sql
        final String sql = "SELECT relato_id, relato_titulo FROM Relato";

        DataSource dataSource = criarDataSource();

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                throw new NenhumItemCadastradoException("Não há relatos cadastrados!");
            }

            while(resultSet.next()) {
                itensCadastrados.put(resultSet.getInt("relato_id"), resultSet.getString("relato_titulo"));
            }

        } catch (SQLException e) {
            System.out.println("Falha ao coletar títulos dos relatos");
        }

        return itensCadastrados;
    }

    @Override
    public Relato getEntidadeDoBanco(int idRelato) {
        Relato relato = null;

        final String sql = "SELECT * FROM Relato WHERE relato_id = ?";

        DataSource dataSource = criarDataSource();

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, idRelato);

            ResultSet resultado = preparedStatement.executeQuery();

            if(!resultado.isBeforeFirst()) {
                throw new NenhumItemCadastradoException("Não há animais cadastrados!");
            }

            while(resultado.next()) {
                String tituloRelato = resultado.getString("relato_titulo");
                String textoRelato = resultado.getString("relato_texto");
                int idAnimalRelatado = resultado.getInt("relato_animalRelatado");

                relato = new Relato(tituloRelato, textoRelato, idAnimalRelatado);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pegar animal do banco de dados");
        }

        return relato;
    }

    @Override
    public void deletarDoBanco(int idEntidade) {
        final String sql = "DELETE FROM Relato WHERE relato_id = ?";

        DataSource dataSource = criarDataSource();

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, idEntidade);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha ao deletar o relato");
        }
    }
}
