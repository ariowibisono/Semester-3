package Coba;

import java.io.*;
import java.net.*;
import java.util.*;
public class Server
{
	private static ServerSocket serverSocket;
	private static final int PORT = 1234;
	public static void main(String[] args)
			throws IOException
			{
		try
		{
			serverSocket = new ServerSocket(PORT);
			System.out.println("MULTI CLIENT SERVER pada PORT:" + PORT);
		}
		catch (IOException ioEx)
		{
			System.out.println("\nTidak Dapat Membuka Port!");
			System.exit(1);
		}
		do
		{
			//Tunggu Koneksi
			System.out.println("Server Siap...");
			Socket client = serverSocket.accept();
			System.out.println("\nClient Baru ..masuk..\n");
			//Create a thread to handle communication with
			//this client and pass the constructor for this
			//thread a reference to the relevant socket...
			ClientHandler handler =
					new ClientHandler(client);
			handler.start();//As usual, method calls run .
		}while (true);
			}
}
class ClientHandler extends Thread
{
	private Socket client;
	private Scanner input;
	private PrintWriter output;
	public ClientHandler(Socket socket)
	{
		//Set up reference to associated socket...
		client = socket;
		try
		{
			input = new Scanner(client.getInputStream());
			output = new PrintWriter(
					client.getOutputStream(),true);
		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
	}
	public void run() // output.prinln di ganti system.out.println
	{
		// Buat Array
		String[] motor = new String[3];
		
		// Inisialisasi isi array dengan kosong
		for(int k=0; k<motor.length; k++){
			motor[k] = "kosong"; 
		}
		
		// Boolean untuk ngecek ada atau tidak data
		boolean keluar = false;
		boolean penuh = false;
		
		String received;
		int i = 0;
		int urutan;
		
		do {
			//menerima pesan dari client
			//melalui socket input stream..
			received = input.nextLine();
			String Plat = received.toUpperCase();
			
			i = 0;
			penuh = false;
			keluar = false;
			
			// Looping cari ruang yang kosong dan bila ingin keluar
			for (int j=0; j<motor.length; j++){
				penuh = true;
				if (motor[j].contains(Plat)){
					keluar = true;
					penuh = false;
					j = motor.length - 1;
				}
			}
			
			// Menginputkan data plat nomor ke dalam ruang yang kosong
			if ((keluar == false)){
				do {
					if (motor[i] == "kosong"){
						motor[i] = Plat;
						
						//Menampilkan kembali pesan ke client
						//melalui socket output stream...
						output.println("Nomor Plat : " +Plat+" telah di tambahkan pada "+new Date().toString());
						i = motor.length - 1;
						penuh = false;
						if (i < (motor.length -1)){
							penuh = true;
						}
					}
					i++;
				}while(i < motor.length);
			}
			
			// Menghapus array karena kendaraan keluar
			if (keluar == true){
				for(int l = 0; l < motor.length; l++){
			        if (motor[l].contains(Plat)){
						urutan = motor[l].indexOf(Plat);
						motor[l] = "kosong";
					}
				}
				output.println(" Terima Kasih Telah Menggunakan Parkir Progresif ");
			}
			
			if (penuh == true){
				output.println(" Maaf Parkir Penuh ");
			}
			
			// Menampilkan isi array dan Pengecekan full tidaknya
			for (int m=0; m<motor.length; m++){
				System.out.println(motor[m]);
				
				if (motor[m] != "kosong"){
					penuh = true;
				}
			}
			System.out.println("");
			
			//ulangi sampai QUIT
		}while (!received.equalsIgnoreCase("QUIT"));
		try
		{
			if (client!=null)
			{
				System.out.println("Menutup koneksi...sukses..");
				client.close();
			}
		}
		catch(IOException ioEx)
		{
			System.out.println("Gagal menutup koneksi!");
		}
	}
}