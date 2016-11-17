package com.Naor.Haimov.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;
import javax.swing.border.Border;
import com.Naor.Haimov.Connection.ConnectionProxy;
import com.Naor.Haimov.Interfaces.*;
public class ClientGUI implements StringConsumer, StringProducer {
	
		private JButton _btSend, _btConnect;
		private JTextField _tfWrite, _tfNick;
		private JTextArea _tfBoard;
		private JFrame _frame;
		private JPanel _convPanel, _headPanel;
		private JLabel  _nickLabel, _credit, _space, _space2;
		private  ConnectionProxy _proxy; 
		private 	Socket _socket;

		
		
		public ClientGUI() {
	         Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

	         _btSend = new JButton ("Send");
			_btSend.setOpaque(true);
			_btSend.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e)
				{
	
					_proxy.consume(_tfWrite.getText());
					_tfWrite.setText("");

				}
			});
			
			_btConnect= new JButton ("Connect");
			_btConnect.setOpaque(true);
			_btConnect.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e)
				{
					if (_tfNick.getText().equals(""))
						{
						double randNumber = Math.random();
						double d = randNumber * 100;
						int randomInt = (int)d;	
						_tfNick.setText("Guest"+randomInt);
						}
					Connect();
					String s ="%";
					s+=_tfNick.getText();
					_proxy.consume(s);
					_btConnect.setEnabled(false);
					_btConnect.setText("Connected to server");
					_btConnect.setBackground(Color.GREEN);
					_btConnect.setBorder(null);
					_tfNick.setBackground(Color.LIGHT_GRAY);
					_tfNick.setEnabled(false);
					_tfBoard.setBackground(Color.LIGHT_GRAY);

				}
			});

			_tfWrite = new JTextField(20);
			_tfNick = new JTextField(10);
	        _tfNick.setBorder(border);

			_tfBoard = new JTextArea(20,20);
			_tfBoard.setEditable(false); 

			_frame = new JFrame("ChatBox");
			_convPanel = new JPanel();
			_headPanel = new JPanel();
			_nickLabel = new JLabel ("Choose your Nickname:");
			_space = new JLabel("  ");
			_space2 = new JLabel("  ");
			_credit = new JLabel ("Created By Naor Haimov");
		}
		
		public void createGUI() {
			_frame.add(_space, BorderLayout.AFTER_LINE_ENDS);
			_frame.add(_space2, BorderLayout.BEFORE_LINE_BEGINS);
			_frame.add(_tfBoard, BorderLayout.CENTER);
			_convPanel.add(_tfWrite);
			_convPanel.add(_btSend);
			_convPanel.add(_credit);
			_frame.add(_convPanel, BorderLayout.SOUTH);
			_headPanel.add(_nickLabel);
			_headPanel.add(_tfNick);
			_headPanel.add(_btConnect);			
			_frame.add(_headPanel, BorderLayout.NORTH);
			_frame.setSize(530, 450);
			_frame.setVisible(true);
			_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
		
	public void Connect () 
	{
		try {
			_socket = new Socket("localhost" ,2000);
			_proxy = new ConnectionProxy(_socket);
			_proxy.AddConsumer(this);
			_proxy.start();
			
		} 
		catch (UnknownHostException e1) 
		{
			System.out.println("UnknownHostException  - Host not found!");
			e1.printStackTrace();
		}
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
	}
		
		@Override
		public void AddConsumer(StringConsumer sc) {
			
		}

		@Override
		public void RemoveConsumer(StringConsumer sc) {
		}

		@Override
		public void consume(String str) {
			_tfBoard.append(str+"\n");	
		}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				System.out.println("GUI has been created!");
				ClientGUI _user = new ClientGUI();
				_user.createGUI();
			}
		} );

		

	}
}
