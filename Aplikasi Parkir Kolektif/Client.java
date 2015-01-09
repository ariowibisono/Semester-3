package Coba;

import java.io.*;
import java.net.*;
import java.util.*;
public class Client {	
	private static InetAddress host;
	private static final int PORT = 1001;
	public static void main(String[] args)
	{
		try
		{
			host = InetAddress.getByName("Ario");
			//host = InetAddress.getByName("dhani-PC");
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("\nHost ID tidak ditemukan!\n");
			System.exit(1);
		}
		
		sendMessages();
	}
	private static void sendMessages()
	{
		Socket socket = null;
		try
		{
			socket = new Socket(host,PORT);
			Scanner networkInput =
					new Scanner(socket.getInputStream());
			PrintWriter networkOutput =
					new PrintWriter(
							socket.getOutputStream(),true);
			
			//Set up stream for keyboard entry...
			Scanner userEntry = new Scanner(System.in);
			String message, response;
			do
			{
				System.out.print(
						"Tuliskan NoPlat ('QUIT' untuk keluar program): ");
				message = userEntry.next();
				networkOutput.println(message);
				response = networkInput.next();
				System.out.println(
						"\nSERVER> " + response);
			}while (!message.equalsIgnoreCase("QUIT"));
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
			try
			{
				System.out.println(
						"\nPenutupan Koneksi...");
				socket.close();
			}
			catch(IOException ioEx)
			{
				System.out.println(
						"Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}