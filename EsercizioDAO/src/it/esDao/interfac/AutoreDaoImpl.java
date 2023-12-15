package it.esDao.interfac;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.esDao.model.Autore;


public class AutoreDaoImpl implements AutoreDao {
	private static final String URL = "jdbc:mysql://localhost:3306/esercizio2";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    
    
	@Override
	public List<Autore> getAll() {
		List<Autore> autori = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM autori");
            while (resultSet.next()) {
                Autore autore = new Autore();
                autore.setIdAutore(resultSet.getInt("id_autore"));
                autore.setNomeA(resultSet.getString("nomeA"));
                autore.setCognomeA(resultSet.getString("cognomeA"));
                autore.setAnnoNascita(resultSet.getDate("annoNascita"));
                autore.setAnnoMorte(resultSet.getDate("annoMorte"));
                autore.setSesso(resultSet.getString("Sesso").charAt(0));
                autore.setNazione(resultSet.getString("Nazione"));
                autori.add(autore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autori;
	}

	@Override
	public Autore getAutoreById(int idAutore) {
		Autore autore = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM autori WHERE id_autore = ?")) {
            preparedStatement.setInt (1, idAutore);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                autore = new Autore();
                autore.setIdAutore(resultSet.getInt("id_autore"));
                autore.setCognomeA(resultSet.getString("cognomeA"));
                autore.setNomeA(resultSet.getString("nomeA"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autore;
	}

	@Override
	public void insert(Autore autore) {
	    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO autori (nomeA, cognomeA, annoNascita, annoMorte, Sesso, Nazione) VALUES (?, ?, ?, ?, ?, ?)")) {
	           preparedStatement.setString(1, autore.getNomeA());
	           preparedStatement.setString(2, autore.getCognomeA());
	           preparedStatement.setDate(3, (Date) autore.getAnnoNascita());
	           preparedStatement.setDate(4, (Date) autore.getAnnoMorte());
	           preparedStatement.setString(5, String.valueOf(autore.getSesso()));
	           preparedStatement.setString(6, autore.getNazione());
	           preparedStatement.executeUpdate();
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }


	}

	@Override
	public void update(Autore autore) {
	    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE autori SET nomeA = ?, cognomeA = ?, annoNascita = ?, annoMorte = ?, Sesso = ?, Nazione = ? WHERE id_autore = ?")) {
	        preparedStatement.setString(1, autore.getNomeA());
	        preparedStatement.setString(2, autore.getCognomeA());
	        preparedStatement.setDate(3, (Date) autore.getAnnoNascita());
	        preparedStatement.setDate(4, (Date) autore.getAnnoMorte());
	        preparedStatement.setString(5, String.valueOf(autore.getSesso()));
	        preparedStatement.setString(6, autore.getNazione());
	        preparedStatement.setInt(7, autore.getIdAutore());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void delete(Autore autore) {
	    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM autori WHERE id_autore = ?")) {
	        preparedStatement.setInt(1, autore.getIdAutore());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
