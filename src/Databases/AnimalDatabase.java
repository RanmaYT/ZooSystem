package Databases;

import Exceptions.NenhumItemCadastradoException;
import Model.Animal;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AnimalDatabase extends BaseDatabase<Animal>{

    @Override
    public void cadastrarNovaEntidade(Animal animal) {
        final String sql = "INSERT INTO Animal(animal_nome, animal_nomeCientifico, animal_habitat, animal_LocalNoZoo)" +
                "VALUES (?,?,?,?)";

        DataSource dataSource = criarDataSource();

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, animal.getNomePopular());
            preparedStatement.setString(2, animal.getNomeCientifico());
            preparedStatement.setString(3, animal.getHabitat());
            preparedStatement.setString(4, animal.getLocalNoZoo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("FALHA AO SALVAR ANIMAL");
        }
    }

    @Override
    public Map<Integer, String> getNomeItensCadastrados() {
        Map<Integer, String> itensCadastrados = new HashMap<>();

        // Criar o sql
        final String sql = "SELECT animal_id, animal_nome FROM Animal";

        DataSource dataSource = criarDataSource();

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                throw new NenhumItemCadastradoException("Não há animais cadastrados!");
            }

            while(resultSet.next()) {
                itensCadastrados.put(resultSet.getInt("animal_id"), resultSet.getString("animal_nome"));
            }

        } catch (SQLException e) {
            System.out.println("Falha ao coletar nome dos animais");
        }

        return itensCadastrados;
    }

    @Override
    public Animal getEntidadeDoBanco(int idEntidade) {
        Animal animal = null;

        final String sql = "SELECT * FROM Animal WHERE animal_id = ?";

        DataSource dataSource = criarDataSource();

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, idEntidade);

            ResultSet resultado = preparedStatement.executeQuery();

            if(!resultado.isBeforeFirst()) {
                throw new NenhumItemCadastradoException("Não há animais cadastrados!");
            }

            while(resultado.next()) {
                String nomePopular = resultado.getString("animal_nome");
                String nomeCientifico = resultado.getString("animal_nomeCientifico");
                String habitat = resultado.getString("animal_habitat");
                String localNoZoo = resultado.getString("animal_localNoZoo");


                animal = new Animal(nomePopular, nomeCientifico, habitat, localNoZoo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pegar animal do banco de dados");
        }

        return animal;
    }

    @Override
    public void deletarDoBanco(int idEntidade) {
        final String sql = "DELETE FROM Animal WHERE animal_id = ?";

        DataSource dataSource = criarDataSource();

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, idEntidade);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha ao deletar a entidade");
        }
    }
}
