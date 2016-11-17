package com.Naor.Haimov.Client;

import java.util.ArrayList;

import com.Naor.Haimov.Interfaces.*;

public class MessageBoard implements StringConsumer, StringProducer {

	
	ArrayList<StringConsumer> clientsList = new ArrayList<StringConsumer>();

	@Override
	public void AddConsumer(StringConsumer sc) {

		clientsList.add(sc);
		
	}

	@Override
	public void RemoveConsumer(StringConsumer sc) {
		clientsList.remove(sc);
	}

	@Override
	public void consume(String str) {
		System.out.println("A new message has arrived to the MessageBoard");
		for (StringConsumer client:clientsList)
		{
			client.consume(str);
		}
		System.out.println("MessageBoard has published: "+"''"+str+"''" + " to all it's connections");
		System.out.println("--------------------------------------------------------------------------------------------- ");

	}

}
 