package serwer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
	Server(Socket socket) throws Exception {
		}

		public static void main(String args[]) throws Exception {
			ServerSocket serverSocket;
			try {
				serverSocket = new ServerSocket(ServerCommands.GetServerSocket());
			} catch (Exception e) {
				System.err.println("Create server socket: " + e);
				return;
			}
			while (true) {
				try {
					System.out.println(serverSocket);
					Socket socket = serverSocket.accept();
					InputStream is = socket.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					OutputStream os = socket.getOutputStream();
					PrintWriter pw = new PrintWriter(os, true);
					String fromClient = br.readLine();
					if(fromClient!=null)
					{
						
						String serverResponse= ServerCommands.serverMessage(fromClient);
						pw.println(serverResponse);
						System.out.println("From client: [" + fromClient + "]");
						System.out.println("To client: [" + serverResponse + "]");
					}

				} catch (Exception e) {
					System.err.println("Server exception: " + e);
				}
			}
		}
}



