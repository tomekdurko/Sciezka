package serwer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Klasa Przechowuj�ca wszystkie komendy dla serwera
 */
public class ServerCommands {
	/**
	 * Properties pobierane z pliku
	 */
	static Properties config = new Properties();
	/**
	 * strumie� do pobierania z pliku
	 */
	static InputStream input_config = null;
	/**
	 * metoda wysy�aj�ca wiadomo�ci w zale�no�ci od tej odebranej od klienta
	 * @param command komenda odebrana od klienta
	 * @return zwracana wiadomo��
	 */
	public static String serverMessage(String command)
	{
		String clientMessage = command;
		String serverMessage;
		switch(clientMessage) {
		case "GET_MEDIUM":
			serverMessage=medium();
			break;
		case "GET_EASY" :
			serverMessage=easy();
			break;
		case "GET_HARD":
			serverMessage=hard();
			break;
		case "LEVEL_1_LEFT_VECTORS":
			serverMessage=leftVectors(1);
			break;
		case "LEVEL_1_RIGHT_VECTORS":
			serverMessage=rightVectors(1);
			break;
		case "LEVEL_2_LEFT_VECTORS":
			serverMessage=leftVectors(2);
			break;
		case "LEVEL_2_RIGHT_VECTORS":
			serverMessage=rightVectors(2);
			break;
		case "GET_SPEED":
			serverMessage=speed();
			break;
		case "LEADERBOARD":
			serverMessage=leaderboard();
			break;
		default:
			serverMessage="INVALID_COMMAND";
		}
		return serverMessage;
	}
	/**
	 * metoda pobieraj�ca z pliku poziom medium i zwracaj�ca wiadomo�� dla klienta
	 * @return zwracana wiadomo��
	 */
	static String medium()
	{
		try {
			
		input_config = new FileInputStream("config.properties");
		config.load(input_config);
        return "MEDIUM "+config.getProperty("MEDIUM").toString();
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
			return null;
		}


	}
	/**
	 * metoda pobieraj�ca najlepsze wyniki i zwracaj�ca wiadomo�� dla klienta
	 * @return zwracana wiadomo��
	 */
	static String leaderboard()
	{
		try {
			input_config = new FileInputStream("scores.properties");
			config.load(input_config);
	        return "SCORES "+config.getProperty("POINTS").toString()+config.getProperty("NAME").toString();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return null;
		}
		
	}
	/**
	 * metoda pobieraj�ca z pliku szybkosc mapy i zwracaj�ca wiadomo�� dla klienta
	 * @return zwracana wiadomo��
	 */
	static String speed()
	{		
		try {	
	input_config = new FileInputStream("config.properties");
	config.load(input_config);
    return "SPEED "+config.getProperty("SPEED").toString();
	}
	
	catch (IOException ex) {
		
		ex.printStackTrace();
		return null;
	}
		
	}
	/**
	 * metoda pobieraj�ca z pliku poziom easy i zwracaj�ca wiadomo�� dla klienta
	 * @return zwracana wiadomo��
	 */
	static String easy()
	{
		try {
			
		input_config = new FileInputStream("config.properties");
		config.load(input_config);
        return "EASY "+config.getProperty("EASY").toString();
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
			return null;
		}
		
	}
	/**
	 * metoda pobieraj�ca z pliku poziom hard i zwracaj�ca wiadomo�� dla klienta
	 * @return zwracana wiadomo��
	 */
	static String hard()
	{
		try {
			
		input_config = new FileInputStream("config.properties");
		config.load(input_config);
        return "HARD "+config.getProperty("HARD").toString();
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
			return null;
		}
		
	}
	/**
	 * metoda pobieraj�ca z pliku lew� strone mapy i zwracaj�ca wiadomo�� dla klienta
	 * @param lvl okre�la kt�ry poziom pobieramy
	 * @return zwracana wiadomo��
	 */
	static String leftVectors(double lvl)
	{
		try {
			
		
		input_config = new FileInputStream("map.properties");
		config.load(input_config);
		return lvl == 1 ?  "LEFT_VECTORS "+config.getProperty("Left_Vectors1").toString():  "LEFT_VECTORS "+config.getProperty("Left_Vectors2").toString();
        
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * metoda pobieraj�ca z pliku praw� strone mapy i zwracaj�ca wiadomo�� dla klienta
	 * @param lvl okre�la kt�ry poziom pobieramy
	 * @return zwracana wiadomo��
	 */
	static String rightVectors(double lvl)
	{
		try {
			
		
		input_config = new FileInputStream("map.properties");
		config.load(input_config);
		
        return lvl == 1 ?  "RIGHT_VECTORS "+config.getProperty("Right_Vectors1").toString():  "RIGHT_VECTORS "+config.getProperty("Right_Vectors2").toString();
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * metoda zwraca socket pobrany z pliku
	 * @return zwracany socket
	 */
	static int GetServerSocket()
	{
		try {
		BufferedReader br = new BufferedReader(new FileReader("IPCONFIG.txt"));
		return Integer.parseInt(br.readLine());
		} catch (IOException e) {
			System.out.println("Serwer nie mogl zostac uruchomiony");
            System.err.println(e);
            return 0;
		}
		
		
       
	}
}
