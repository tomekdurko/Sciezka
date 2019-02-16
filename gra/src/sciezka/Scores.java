package sciezka;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Vector;

public class Scores  {

    long[] BestScores;
    String[] NickNames;


    public Scores() {
	/*	BestScores[0]=0;
		BestScores[1]=0;
		BestScores[2]=0;
		BestScores[3]=0;
		BestScores[4]=0;
		Properties scoring = new Properties();
		OutputStream scoring_output=null;

		try {
			scoring_output = new FileOutputStream("scores.properties");

			String scores=BestScores[0] +" "+ BestScores[1] + " " + BestScores[2]+ " "+ BestScores[3]+ " " + BestScores[4]+" END";
			scoring.setProperty("Wyniki", scores);
			String names=NickNames[0].toString() +" "+ NickNames[1].toString() + " " + NickNames[2].toString()+ " "+ NickNames[3].toString()+ " " + NickNames[4].toString()+" END";
			scoring.setProperty("Nazwy", names);

		} catch (IOException io) {
			io.printStackTrace();
		} 	finally {
			if (scoring_output != null) {
				try {
					scoring_output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		*/

    }

    public void addScore(long score,String nick) {
        for(int i = 0; i<5;i++){
            if(BestScores[i]<score) {
                BestScores[i]=score;
                NickNames[i]=nick;
            }
        }
    }

    public void write() {
        System.out.println(BestScores[1]);
    }



}



