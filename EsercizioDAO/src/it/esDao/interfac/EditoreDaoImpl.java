package it.esDao.interfac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.esDao.model.Editore;
import it.esDao.model.Genere;

public class EditoreDaoImpl implements EditoreDao {
	private static final String URL = "jdbc:mysql://localhost:3306/esercizio2";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

	@Override
	public List<Editore> getAll() {
		List<Editore> editori = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM editore");
            while (resultSet.next()) {
                Editore editore = new Editore();
                editore.setidEditore(resultSet.getString("id_editore"));
                editore.setNome(resultSet.getString("nome"));
                editori.add(editore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return editori;
	}
	@Override
	public Editore getEditoreById(String id_editore) {
		   Editore editore = null;
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM editore WHERE id_editore = ?")) {
	            preparedStatement.setString (1, id_editore);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                editore = new Editore();
	                editore.setidEditore(resultSet.getString("id_editore"));
	                editore.setNome(resultSet.getString("nome"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return editore;
	}

	@Override
	public void insert(Editore editore) {
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		         PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO editore (id_editore , nome) VALUES (? , ?)")) {
			 	String idEditore = generateIdFromNome(editore.getNome());
			 	preparedStatement.setString(1, idEditore);
		        preparedStatement.setString(2, editore.getNome());
		        preparedStatement.executeUpdate();
		       
		    } catch (SQLException e) {
		        e.printStackTrace();
		       
		    }
	}
	
	// uso questo metodo per generare una sub string che funzionera come generatoree per l'id editore
	private String generateIdFromNome(String nome) {
	    return nome.substring(0, Math.min(nome.length(), 5)).toUpperCase();
	}

	@Override
	public void update(Editore editore) {
		
		    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE editore SET nome = ? WHERE id_editore = ?")) {
		        preparedStatement.setString(1, editore.getNome());
		        preparedStatement.setString(2, editore.getidEditore());
		        preparedStatement.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		   
	}

	@Override
	public void delete(Editore editore) {
		 
		    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM editore WHERE nome = ?")) {
		        preparedStatement.setString(1, editore.getNome());
		         preparedStatement.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		  
	}

}
