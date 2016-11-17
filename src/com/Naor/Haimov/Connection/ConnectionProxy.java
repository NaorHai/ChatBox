package com.Naor.Haimov.Connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.Naor.Haimov.Interfaces.*;

public class ConnectionProxy extends Thread implements StringConsumer, StringProducer {

	Socket _socket;
	StringConsumer _client;
	DataOutputStream dos;
	DataInputStream dis;
	
	public ConnectionProxy(Socket socket) {
		_socket = socket;
		try {
			 dos = new DataOutputStream (_socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			 dis = new DataInputStream (_socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}


	}


	@Override
	public void consume(String str)  {
		if (str.equals("")) 
			return;
		
			try
			{
				dos.writeUTF(str);
			}
			catch (IOException e)
			{
			}
	}
	@Override
	public void run() 
	{
		try
		{
		System.out.println("Server has created a new proxy connection");
		String message;
		
			while(true)
			{
				message = dis.readUTF();
				System.out.println("Proxy has received a message: "+"''"+message+"''");
				_client.consume(message);
			}
		}
		catch (IOException e)
		{
		}
	}

	@Override
	public void AddConsumer(StringConsumer sc) {
		_client=sc;
	}

	@Override
	public void RemoveConsumer(StringConsumer sc) {
		_client = null;		
	}


}
