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

    public boolean AnimalJaCadastrado(Animal animal){
        // Valida se já há um animal com esse nome popular ou com o nome científico.
        final String sql = "SELECT * FROM Animal WHERE animal_nome = ? OR animal_nomeCientifico = ?";

        DataSource dataSource = criarDataSource();

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, animal.getNomePopular());
            preparedStatement.setString(2, animal.getNomeCientifico());

            ResultSet resultSet = preparedStatement.executeQuery();

            // Se não houver resultados, retorna falso
            if(!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar se o animal já está cadastrado");
        }

        return true;
    }

    public void atualizarAnimal(int idAnimal, Animal animal){
        // Alerta: a parte de atualizar animal está gambiarrada, provavelmente deve ter uma forma melhor de fazer essa parte!
        final String sql = "UPDATE Animal " +
                "SET animal_nome = ?, animal_nomeCientifico = ?, animal_habitat = ?, animal_localNoZoo = ? " +
                "WHERE animal_id = ?";

        DataSource dataSource = criarDataSource();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, animal.getNomePopular());
            preparedStatement.setString(2, animal.getNomeCientifico());
            preparedStatement.setString(3, animal.getHabitat());
            preparedStatement.setString(4, animal.getLocalNoZoo());
            preparedStatement.setInt(5, idAnimal);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao atualizar informações do animal no banco!");
        }
    }

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
            throw new RuntimeException("Falha ao salvar animal no banco");
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
            throw new RuntimeException("Falha ao coletar nomes dos animais no banco");
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
            throw new RuntimeException("Falha ao pegar animal no banco");
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
            throw new RuntimeException("Falha ao deletar animal no banco");
        }
    }
}
