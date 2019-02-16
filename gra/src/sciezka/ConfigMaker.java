package sciezka;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
/**
 * Klasa tworzy konfiguracje i wrzuca ją do plków
 */

public class ConfigMaker {
	public ConfigMaker() {

		Properties config = new Properties();
		Properties maping = new Properties();
		OutputStream config_output = null;
		OutputStream map_output = null;

		try {

			config_output = new FileOutputStream("config.properties");

			config.setProperty("SPEED", "20");
			config.setProperty("EASY", "1.5");
			config.setProperty("MEDIUM", "1");
			config.setProperty("HARD", "0.6");
			config.store(config_output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} 	finally {
			if (config_output != null) {
				try {
					config_output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		try {
			map_output = new FileOutputStream("map.properties");
			maping.setProperty("Left_Vectors1", "100 0 200 300 200 500 50 700 50 1100 250 1400 150 1600 350 1900 350 2000 150 2400 100 2600 200 2900 300 3700 150 4000 50 4300 200 4700 250 5000 100 5500 300 6000  END");
			maping.setProperty("Right_Vectors1","400 0 300 300 300 500 250 700 250 1100 450 1400 250 1600 450 1900 450 2000 250 2400 200 2600 300 2900 400 3700 250 4000 300 4300 250 4700 450 5000 300 5500 200 6000 END");
			maping.setProperty("Left_Vectors2", "100 0 100 300 200 500 50 700 50 1100 250 1400 150 1600 350 1900 350 2000 150 2400 100 2600 200 2900 300 3700 150 4000 50 4300 200 4700 250 5000 100 5500 300 6000  END");
			maping.setProperty("Right_Vectors2","400 0 400 300 300 500 250 700 250 1100 450 1400 250 1600 450 1900 450 2000 250 2400 200 2600 300 2900 400 3700 250 4000 300 4300 250 4700 450 5000 300 5500 200 6000 END");
			maping.store(map_output, null);
		}	catch (IOException io) {
			io.printStackTrace();
		}	finally {
			if (map_output != null) {
				try {
					map_output.close();
				}	catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}

}

