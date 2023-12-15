package it.esDao.interfac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.esDao.model.Autore;
import it.esDao.model.Genere;
import it.esDao.model.Libro;

public class LibroDaoImpl implements LibroDao {
	private static final String URL = "jdbc:mysql://localhost:3306/esercizio2";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    Scanner scanner = new Scanner(System.in);
    
    @Override
    public List<Libro> getAll() {
        List<Libro> libri = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM libri");

            while (resultSet.next()) {
                Libro libro = new Libro();
                libro.setIdLibro(resultSet.getInt("id_libro"));
                libro.setTitolo(resultSet.getString("titolo"));
                libro.setIdAutore(resultSet.getInt("id_autore"));
                libro.setNumPag(resultSet.getInt("numPag"));
                libro.setAnno(resultSet.getInt("anno"));
                libro.setIdGenere(resultSet.getInt("id_genere"));
                libro.setIdEditore(resultSet.getString("id_editore"));

                libri.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libri;
    }

    @Override
    public Libro getLibroById(int idLibro) {
        Libro libro = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM libri WHERE id_libro = ?")) {
            preparedStatement.setInt(1, idLibro);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                libro = new Libro();
                libro.setIdLibro(resultSet.getInt("id_libro"));
                libro.setTitolo(resultSet.getString("Titolo"));
                libro.setIdAutore(resultSet.getInt("id_autore"));
                libro.setNumPag(resultSet.getInt("numPag"));
                libro.setAnno(resultSet.getInt("Anno"));
                libro.setIdGenere(resultSet.getInt("id_genere"));
                libro.setIdEditore(resultSet.getString("id_editore"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }
    

    @Override
    public void insert(Libro libro) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO libri (Titolo, id_autore, numPag, Anno, id_genere, id_editore) VALUES (?, ?, ?, ?, ?, ?)")) {
                preparedStatement.setString(1, libro.getTitolo());
                preparedStatement.setInt(2, libro.getIdAutore()); 
                preparedStatement.setInt(3, libro.getNumPag());
                preparedStatement.setInt(4, libro.getAnno());
                preparedStatement.setInt(5, libro.getIdGenere()); 
                preparedStatement.setString(6, libro.getIdEditore()); 
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

		

	@Override
	public void update(Libro libro) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	        try (PreparedStatement preparedStatement = connection.prepareStatement(
	                "UPDATE libri SET Titolo = ?, id_autore = ?, numPag = ?, Anno = ?, id_genere = ?, id_editore = ? WHERE id_libro = ?")) {
	            preparedStatement.setString(1, libro.getTitolo());
	            preparedStatement.setInt(2, libro.getIdAutore());
	            preparedStatement.setInt(3, libro.getNumPag());
	            preparedStatement.setInt(4, libro.getAnno());
	            preparedStatement.setInt(5, libro.getIdGenere());
	            preparedStatement.setString(6, libro.getIdEditore());
	            preparedStatement.setInt(7, libro.getIdLibro());
	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void delete(Libro libro) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	        try (PreparedStatement preparedStatement = connection.prepareStatement(
	                "DELETE FROM libri WHERE id_libro = ?")) {
	            preparedStatement.setInt(1, libro.getIdLibro());
	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	

}
