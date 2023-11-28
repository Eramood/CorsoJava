package it.EsEstrazioni.OpeSQL;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import it.EsEstrazioni.Connessione.DbConnection;
import it.EsEstrazioni.Connessione.DbHandler;

public class StampeStatistiche {
	private static final String PATHFILE = "./PdfFolder"; //destinazione del file creato
	public void stampaVideo() {
		 DbHandler dbHandler = DbHandler.getInstance();
			try {
					 Connection connection = dbHandler.getConnection();
		            Statement statement = connection.createStatement(); 
				ResultSet rs = statement.executeQuery
				("SELECT p.id_part, p.nome, p.sede, COUNT(e.id_ESTRAZIONE) AS numero_estrazioni "
				+ "FROM partecipanti p "
				+ "LEFT JOIN estrazioni e ON p.id_part = e.id_part "
				+ "GROUP BY p.id_part, p.nome, p.sede "
				+ "HAVING numero_estrazioni > 0 "
				+ "ORDER BY numero_estrazioni DESC;");
			while(rs.next()) {
				System.out.println(
			"!- nome "+rs.getString("nome")+
			" sede "+ rs.getString("sede")+
			" n estrazioni "+rs.getInt("numero_estrazioni")+"-!");
			}
				
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	public void stampaPdf() {
		String nomeFile =PATHFILE+"/Lista_"+"statistiche"+".pdf";
		Document document = new Document();
		try {
			FileOutputStream outputStream = 
					new FileOutputStream(nomeFile);
			PdfWriter.getInstance(document, outputStream);
	        
	        //Create Font objects
	        Font font = new Font(Font.FontFamily.COURIER, 12);
	        document.open();
	        
	        DbHandler dbHandler = DbHandler.getInstance();
			try {
					 Connection connection = dbHandler.getConnection();
		            Statement statement = connection.createStatement(); 
					ResultSet rs = statement.executeQuery
					("SELECT p.id_part, p.nome, p.sede, COUNT(e.id_ESTRAZIONE) AS numero_estrazioni "
					+ "FROM partecipanti p "
					+ "LEFT JOIN estrazioni e ON p.id_part = e.id_part "
					+ "GROUP BY p.id_part, p.nome, p.sede "
					+ "HAVING numero_estrazioni > 0 "
					+ "ORDER BY numero_estrazioni DESC;");
					
				while(rs.next()) {
					 document.add(new Paragraph(
							 	" || "+rs.getString("nome")+
								" || "+ rs.getString("sede")+
								" || n estrazioni "+rs.getInt("numero_estrazioni"),font));
				}
					
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
	        
	        document.close();
	        outputStream.close();
	        
	        
	        
		} catch (FileNotFoundException | DocumentException e1) {
			
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
