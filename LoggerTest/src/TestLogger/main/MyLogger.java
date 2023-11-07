package TestLogger.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyLogger {
    private static String filePath = "" ;
    private static String level = "error";
    private static String handler = "console";
	
	public static void main(String[] args) {
		MyLogger log = new MyLogger();
		String path ="./properties/config.properties" ;
		try {
			InputStream inputStream = new FileInputStream(path);
			Properties properties = new Properties();
			properties.load(inputStream);
			level= properties.getProperty("logger.level");
			filePath= properties.getProperty("logger.file");
			handler= properties.getProperty("logger.handler");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if(level.contains("info")) {
			log.warning();
			log.info();
			log.error();
		}else if(level.contains("warning")) {
			log.warning();
			log.error();
		}else if(level.contains("error")) {
			log.error();
		}
	}
	
	
	public void logWriter(String messaggio){
		
		String message = messaggio;
		
		try {
			File file =new File(filePath);
			FileWriter fileWriter= new FileWriter(file,true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			writer.write(message);;
			writer.newLine();
			writer.close();
			
			//System.out.println("file scritto");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	  public void warning() {
		  if(handler.contains("CONSOLE") || handler.contains("FILE") || handler.contains("ALL") ) {
		  String warningMessaggio = "WARNING c'è un problema";
			if(handler.contains("CONSOLE")){
				System.out.println(warningMessaggio);
			}else if(handler.contains("FILE")){
				this.logWriter(warningMessaggio);
			}else if(handler.contains("ALL")) {
				System.out.println(warningMessaggio);
				this.logWriter(warningMessaggio);
			}
		  }
	}
	  public void error() {
		  if(handler.contains("CONSOLE") || handler.contains("FILE") || handler.contains("ALL") ) {
			  String errorMessaggio = "ERRORE nel sistema";
				if(handler.contains("CONSOLE")){
					System.out.println(errorMessaggio);
				}else if(handler.contains("FILE")){
					this.logWriter(errorMessaggio);
				}else if(handler.contains("ALL")) {
					System.out.println(errorMessaggio);
					this.logWriter(errorMessaggio);
				}
			  }
		}
	  public  void info() {
		  if(handler.contains("CONSOLE") || handler.contains("FILE") || handler.contains("ALL") ) {
			  String infoMessaggio = "INFO puoi vedere errori e avvertimenti";
				if(handler.contains("CONSOLE")){
					System.out.println(infoMessaggio);
				}else if(handler.contains("FILE")){
					this.logWriter(infoMessaggio);
				}else if(handler.contains("ALL")) {
					System.out.println(infoMessaggio);
					this.logWriter(infoMessaggio);
				}
			  }
		}
}
