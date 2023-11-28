package it.EsEstrazioni.Connessione;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	private static final String NOMEDB = "EsEstrazioni";
	 private static final String URL = "jdbc:mysql://localhost:3306/"+NOMEDB;
	 private static final String USER = "root";
	 private static final String PASSWORD = "1234";
	 
	public static Connection conn() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	
			return DriverManager.getConnection(URL,USER,PASSWORD);
		
		/*
		try {
			con = DriverManager.getConnection(DB_URL,"root","1234");
			// creo lo statement
			Statement stm = con.createStatement();
			//eseguo la query il resulset esegue lo statement
			ResultSet rs = stm.executeQuery("SELECT * from genere");
			while(rs.next()) {	
				System.out.println(rs.getInt("id_genere")+ "||" + rs.getString("descrizione"));
				
			}
		}*/ 
}
	public void chiudiConnessione() {
		try {
			conn().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
