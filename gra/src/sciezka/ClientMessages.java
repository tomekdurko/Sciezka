package sciezka;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

/**
 * Klasa reprezentujaca obsluge zdarzeń z serwerem
 */
public class ClientMessages {
   static Configuration KONFIGURACJA;

    static {
        try {
            KONFIGURACJA = new Configuration();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zwracająca socket pobrany z pliku konfiguracyjnego
     */

    public static int getSocket()
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
    /**
     * Metoda zwracająca adres pobrany z pliku konfiguracyjnego
     */
    public static String getAdress()
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader("IPCONFIG.txt"));
             br.readLine();
             return br.readLine().toString();

        } catch (IOException e) {
            System.out.println("Serwer nie mogl zostac uruchomiony");
            System.err.println(e);
            return null;
        }
    }

    /**
     * Metoda zwracająca wartość poziomu trudnosci medium z serwera
     */
    public static double getMedium()
    {
        try {
            Socket socket = new Socket(getAdress(), getSocket());
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("GET_MEDIUM");
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String odpowiedz = br.readLine();
            System.out.println(odpowiedz);
            socket.close();
            return Double.parseDouble(odpowiedz.substring(7));

        } catch (Exception e) {
            System.out.println("Nie udało się połączyc z serwerem");
            return KONFIGURACJA.getMedium();
        }
        }

    /**
     * Metoda zwracająca wartość poziomu trudnosci easy z serwera
     */
    public static double getEasy()
    {
        try {
            Socket socket = new Socket(getAdress(), getSocket());
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("GET_EASY");
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String odpowiedz = br.readLine();
            System.out.println(odpowiedz);
            socket.close();
            return Double.parseDouble(odpowiedz.substring(5));

        } catch (Exception e) {
            System.out.println("Nie udało się połączyc z serwerem");
            return KONFIGURACJA.getEasy();
        }
    }
    /**
     * Metoda zwracająca wartość poziomu trudnosci hard z serwera
     */
    public static double getHard()
    {
        try {
            Socket socket = new Socket(getAdress(), getSocket());
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("GET_HARD");
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String odpowiedz = br.readLine();
            System.out.println(odpowiedz);
            socket.close();
            return Double.parseDouble(odpowiedz.substring(5));

        } catch (Exception e) {
            System.out.println("Nie udało się połączyc z serwerem");
            return KONFIGURACJA.getHard();
        }
    }
    /**
     * Metoda zwracająca wartość szybkosci z jaka przesuwa sie mapa z serwera
     */
    public static double getSpeed()
    {
        try {
            Socket socket = new Socket(getAdress(), getSocket());
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("GET_SPEED");
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String odpowiedz = br.readLine();
            System.out.println(odpowiedz);
            socket.close();
            return Double.parseDouble(odpowiedz.substring(6));

        } catch (Exception e) {
            System.out.println("Nie udało się połączyc z serwerem");
            return KONFIGURACJA.getSpeed();
        }
    }
    /**
     * Metoda zwracająca współrzędne punktów z których rysowana jest lewa strona mapy z serwera
     * @param lvl poziom wczytywanej mapy
     */
    public static String getLeft(int lvl)
    {
        try {
            Socket socket = new Socket(getAdress(), getSocket());
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("LEVEL_"+lvl+"_LEFT_VECTORS");
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String odpowiedz = br.readLine();
            System.out.println(odpowiedz);
            odpowiedz = odpowiedz.substring(13,odpowiedz.length()-3);

            return odpowiedz;
        } catch (Exception e) {
            System.out.println("Nie udało się połączyc z serwerem");
            return null;
        }
    }
    /**
     * Metoda zwracająca współrzędne punktów z których rysowana jest prawej strony mapy z serwera
     * @param lvl poziom wczytywanej mapy
     */
    public static String getRight(int lvl)
    {
        try {
            Socket socket = new Socket(getAdress(), getSocket());
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("LEVEL_"+lvl+"_RIGHT_VECTORS");
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String odpowiedz = br.readLine();
            System.out.println(odpowiedz);
            odpowiedz = odpowiedz.substring(14,odpowiedz.length()-3);

            return odpowiedz;
        } catch (Exception e) {
            System.out.println("Nie udało się połączyc z serwerem");
            return null;
        }
    }
    /**
     * Metoda zwracająca współrzędne punktów z których rysowana jest  mapa z serwera
     * @param a poziom wczytywanej mapy
     * @return zwraca vector z elementami lewej strony oraz prawej
     */
    public static Vector<Integer> loadMap (int a)
    {

        Vector<Integer> MAP_RIGHT = new Vector<Integer>();
        Vector<Integer> MAP_LEFT = new Vector<Integer>();
        Vector<Integer> MAP = new Vector<Integer>();




        if(a==1) {
            String Right_Vectors = ClientMessages.getRight(1);
            if (Right_Vectors==null)
               return KONFIGURACJA.loadMap(1);
            String Left_Vectors = ClientMessages.getLeft(1);
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
            String Right_Vectors = ClientMessages.getRight(2);
            if (Right_Vectors==null)
                return KONFIGURACJA.loadMap(2);
            String Left_Vectors = ClientMessages.getLeft(2);
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

}
