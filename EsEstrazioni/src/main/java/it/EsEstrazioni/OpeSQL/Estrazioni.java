package it.EsEstrazioni.OpeSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.EsEstrazioni.Connessione.DbConnection;
import it.EsEstrazioni.Connessione.DbHandler;

public class Estrazioni {
	
	public void estrazioneCasuale() {
		 DbHandler dbHandler = DbHandler.getInstance();
		try {
				 Connection connection = dbHandler.getConnection();
	            Statement statement = connection.createStatement(); 
				ResultSet rs = statement.executeQuery("SELECT * FROM partecipanti "
						+ "ORDER BY RAND ( ) "
						+ "LIMIT 1;");
				rs.next();	
					System.out.println("| è stato estratto: "  
										+ rs.getString("nome")+ " da " 
											+ rs.getString("sede")+"|");
				int	idpartecipante =rs.getInt("id_part");
				
				String inserimento = "INSERT INTO estrazioni(id_part) VALUES"
						+"("+idpartecipante+");";
				statement.executeUpdate(inserimento);
				
	
				
	        } catch (SQLException e) {
	            e.printStackTrace();
	            
	        }
   }

}
