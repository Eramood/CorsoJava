package it.esDao.interfac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.esDao.model.Genere;

public class GenereDaoImpl implements GenereDao {
	private static final String URL = "jdbc:mysql://localhost:3306/esercizio2";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
	@Override
	public List<Genere> getAll() {
		List<Genere> generi = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM genere");
            while (resultSet.next()) {
                Genere genere = new Genere();
                genere.setidGenere(resultSet.getInt("id_genere"));
                genere.setDescrizione(resultSet.getString("descrizione"));
                generi.add(genere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generi;
	}

	@Override
	public Genere getGenereById(int codiceG) {
		   Genere genere = null;
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM genere WHERE id_genere = ?")) {
	            preparedStatement.setInt(1, codiceG);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                genere = new Genere();
	                genere.setidGenere(resultSet.getInt("id_genere"));
	                genere.setDescrizione(resultSet.getString("descrizione"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return genere;
	}

	@Override
	public void insert(Genere genere) {
		  try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			         PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO genere (descrizione) VALUES (?)")) {
			        preparedStatement.setString(1, genere.getDescrizione());
			         preparedStatement.executeUpdate();
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			       
			    }
	}

	@Override
	public void update(Genere genere) {
	    
	    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE genere SET descrizione = ? WHERE id_genere = ?")) {
	        preparedStatement.setString(1, genere.getDescrizione());
	        preparedStatement.setInt(2, genere.getidGenere());
	        preparedStatement.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	}

	@Override
	public void delete(Genere genere) {
	    
	    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM genere WHERE descrizione = ?")) {
	        preparedStatement.setString(1, genere.getDescrizione());
	      preparedStatement.executeUpdate();
	      
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	}

}
