package com.Naor.Haimov.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.Naor.Haimov.Client.ClientDescriptor;
import com.Naor.Haimov.Client.MessageBoard;
import com.Naor.Haimov.Connection.ConnectionProxy;

public class ServerApplication {

	public static void main(String[] args) {
		
		ServerSocket server  = null;
		MessageBoard mb = new MessageBoard();
		try
		{
			server = new ServerSocket(2000,5);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Socket socket = null;
		ClientDescriptor client = null;
		ConnectionProxy connection = null;
		
		while (true)
		{
			try
			{
				System.out.println("Server is waiting for a new connection ");
				socket=server.accept();
				connection  = new ConnectionProxy(socket);
				client = new ClientDescriptor();
				connection.AddConsumer(client);
				client.AddConsumer(mb);
				mb.AddConsumer(connection);
				connection.start();

			}
			catch (IOException e)
			{
				System.out.println("The Connection to the server has been lost!");
			}
		}
	
	}
}
