package com.Naor.Haimov.Client;
import java.sql.Timestamp;
import java.util.Date;
import com.Naor.Haimov.Interfaces.*;

public class ClientDescriptor implements StringConsumer, StringProducer {
	private String _nickName;
	private StringConsumer _mb;

	@Override
	public void AddConsumer(StringConsumer mb) {
		_mb=mb;
	}

	@Override
	public void RemoveConsumer(StringConsumer sc) {
		_mb=null;
	}

	@Override
	public void consume(String str) {
		if (str.charAt(0)=='%')
		{
			_nickName = str.substring(1);
			String invoke = _nickName +" is connected to the chat";
			System.out.println("ClientDescriptor has generated a Nickname: "+"''"+_nickName+"''");
			_mb.consume(invoke);
		}
		else 
		{
			Date date = new Date();
			String message = new Timestamp(date.getTime()).toString();
			message+=" "+_nickName+ " : " + str;
			System.out.println("ClientDescriptor is forwarding a new generated message to the MessageBoard: "+"''"+message+"''");
			_mb.consume(message);
			
		}
		
		
	}

}
