package sciezka;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

/**
 * Klasa konfigurująca z pliku properties
 */
public class Configuration {
	public  double MEDIUM;
	public  double HARD;
	public  double EASY;
	public  double SPEED;

public Configuration() throws  IOException
{
		Properties config = new Properties();
		InputStream input_config = null;

			try {

				input_config = new FileInputStream("config.properties");
			config.load(input_config);
            this.MEDIUM=(Double.parseDouble(config.getProperty("MEDIUM")));
            this.HARD=(Double.parseDouble(config.getProperty("HARD")));
            this.EASY=(Double.parseDouble(config.getProperty("EASY")));
            this.SPEED=(Double.parseDouble(config.getProperty("SPEED")));
			}

			catch (IOException ex) {
				ex.printStackTrace();
			}
			finally {
				if (input_config != null) {
					try {
						input_config.close();
					} catch (IOException es) {

						es.printStackTrace();
					}
				}
			}

}
    /**
     * Metoda zwraca szybkość poziomów
     */
public double getSpeed()
{
	return this.SPEED;
}
    /**
     * Metoda zwraca współczynnik szybkości łatwego poziomu
     */
public double getEasy()
{
	return this.EASY;
}
    /**
     * Metoda zwraca współczynnik szybkości średniego poziomu
     */
public double getMedium()
{
	return this.MEDIUM;
}
    /**
     * Metoda zwraca współczynnik szybkości trudnego poziomu
     */
public double getHard()
{
	return this.HARD;
}

    /**
     * Metoda zwraca mapę
     * @param a parametr który określa która mape zwracamy
     */
	public Vector<Integer> loadMap (int a)
	{
		Properties maping = new Properties();
		InputStream input_maping = null;
		Vector<Integer> MAP_RIGHT = new Vector<Integer>();
		Vector<Integer> MAP_LEFT = new Vector<Integer>();
		Vector<Integer> MAP = new Vector<Integer>();

		try {

			input_maping = new FileInputStream("map.properties");
			maping.load(input_maping);

			if(a==1) {
				String Right_Vectors = maping.getProperty("Right_Vectors1");
				String Left_Vectors = maping.getProperty("Left_Vectors1");
				Scanner scan1=new Scanner(Left_Vectors);
				Scanner scan2= new Scanner(Right_Vectors);

				while(scan2.hasNextInt())
				{
					MAP_RIGHT.addElement(scan2.nextInt());
				}

				while(scan1.hasNextInt())
				{
					MAP_LEFT.addElement(scan1.nextInt());

				}
				scan1.close();
				scan2.close();
			}

			if(a==2) {
				String Right_Vectors = maping.getProperty("Right_Vectors2");
				String Left_Vectors = maping.getProperty("Left_Vectors2");
				Scanner scan1=new Scanner(Left_Vectors);
				Scanner scan2= new Scanner(Right_Vectors);

				while(scan2.hasNextInt())
				{
					MAP_RIGHT.addElement(scan2.nextInt());
				}

				while(scan1.hasNextInt())
				{
					MAP_LEFT.addElement(scan1.nextInt());

				}
				scan1.close();
				scan2.close();
			}




		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			if (input_maping != null) {
				try {
					input_maping.close();
				} catch (IOException es) {
					es.printStackTrace();
				}
			}
		}

		for(int i =0; i< (MAP_RIGHT.size()+MAP_LEFT.size()); i++)
		{
			if(i<MAP_RIGHT.size())
			{
				MAP.addElement(MAP_RIGHT.get(i));
			}
			else MAP.addElement(MAP_LEFT.get(i-MAP_RIGHT.size()));
		}

		return MAP;
	}
    /**
     * Metoda zwraca najlepsze nicki
     */
	public Vector<String> getTopNick()
	{
		Properties maping = new Properties();
		InputStream input_maping = null;
		Vector<String> nicks= new Vector<>();

		try {
			input_maping = new FileInputStream("scores.properties");
			maping.load(input_maping);
			String is = maping.getProperty("NAME");
			String tablica[] = is.split(" ");
			for (int i=0;i<tablica.length-1;i++)
				nicks.add(tablica[i]);
			return nicks;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
    /**
     * Metoda zwraca najwyższe wyniki
     */
	public Vector<Integer> getTopPoints()
	{
		Properties maping = new Properties();
		InputStream input_maping = null;
		Vector<Integer> points= new Vector<>();

		try {
			input_maping = new FileInputStream("scores.properties");
			maping.load(input_maping);
			String is = maping.getProperty("POINTS");
			String tablica[] = is.split(" ");
			for (int i=0;i<tablica.length-1;i++)
				points.add(Integer.parseInt(tablica[i]));
			return points;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
    /**
     * Metoda zapisuje wyniki do pliku
     */
	public void saveNewScors(Vector<String> vectorNicks,Vector<Integer> vectorPoints)
	{
		Properties points = new Properties();
		Properties nicks = new Properties();
		OutputStream points_output = null;
		OutputStream nicks_output = null;

		try {
			points_output = new FileOutputStream("scores.properties");
			String punkty="";
			String names="";
			for (int i=0;i<vectorNicks.size();i++)
			{
				punkty=punkty+vectorPoints.get(i)+" ";
				names=names+vectorNicks.get(i)+" ";
			}
			punkty=punkty+"END";
			names=names+"END";
			nicks_output = new FileOutputStream("scores.properties");
			points.getProperty("POINTS",punkty);
			nicks.getProperty("NAMES",names);
			points.store(points_output,null);
			nicks.store(nicks_output,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    /**
     * Metoda rysuje mape
     */
	public void writeMap(StackPane stackPane, GraphicsContext gc, Canvas canvas, Vector<Integer> Mapa)
	{
		stackPane.getChildren().add(canvas);
		gc.fillRect(0, 0, 500, 50);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		gc.beginPath();
		gc.moveTo(0,0);
		for(int i=0;i<(Mapa.size()/2);i+=2)
		{
			gc.lineTo(Mapa.get(i),Mapa.get(i+1));

		}
		gc.moveTo(500,0);
		for(int i=(Mapa.size()/2);i<(Mapa.size());i+=2)
		{
			gc.lineTo(Mapa.get(i),Mapa.get(i+1));
		}

	}

}

