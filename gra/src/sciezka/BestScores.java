package sciezka;

import java.util.Vector;
/**
 * Klasa reprezentujaca obsluge wyników
 */
public class BestScores {

    Vector<String> nicks;
    Vector<Integer> points;

    BestScores(Vector<String> nicks,Vector<Integer> points)
    {
        this.nicks=nicks;
        this.points=points;
    }

    /**
     * Metoda dodawania wyniku
     * @param punkty punkty uzyskane
     * @param nick nick gracza
     */
    public void addNewScore(long punkty,String nick) {
        boolean change=true;
        for(int i=0;i<5;i++) {
            if(punkty>points.get(i) && change) {
               points.add(i,(int)punkty);
               nicks.add(i,nick);
               change=false;
            }
        }
    }
    /**
     * Metoda zwraca Stringa z listą wyników
     */
    public String showScore() {
        String wynik="";
        for(int i=0;i<5;i++) {
            wynik=wynik+(i+1)+"."+nicks.get(i)+ "  "+points.get(i)+"\n";
        }
    return wynik;
    }
}