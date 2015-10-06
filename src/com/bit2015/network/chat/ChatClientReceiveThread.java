package com.bit2015.network.chat;

import java.io.BufferedReader;
import java.io.IOException;

	public class ChatClientReceiveThread {

		private BufferedReader bufferedReader; 
		public ChatClientReceiveThread( BufferedReader bufferedReader ) { 
		this.bufferedReader = bufferedReader; 
		} 
		public void run() { 
		try { 
			while( true ) { 
				String data = bufferedReader.readLine(); 
				if( data == null ) { 
					break; 
				} 
		System.out.println( data ); 
				} 
		} catch( IOException ex ) { 
		ChatClient.log( "error:" + ex ); 
		} 
		} 
	} 

