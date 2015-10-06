package com.bit2015.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
			
		Scanner scanner = null;
		Socket socket = null;  
		OutputStream outputStream = null;
		
		try {
			
		   //1. Ű���� ����
			
			scanner = new Scanner( System.in );
						
		   //2. socket ����
			
			socket = new Socket("192.168.1.113", 1120);
						
		   //3. ����
			
			socket.connect( new InetSocketAddress( SERVER_ADDRESS, SERVER_PORT ) );
			
		   //4. reader/ writer ����
		   BufferedReader bufferedReader = 
				   new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
		   PrintWriter printWriter =
				   new PrintWriter( socket.getOutputStream() );
		   
		   //5. join ��������
		   System.out.print("�г���>>" );
		   String nickname = scanner.nextLine();
		   printWriter.println( "join:" + nickname );
		   printWriter.flush();
		   bufferedReader.readLine();
		   //System.out.println( data );
		   
		   //6. ChatClientRecevieThread ����
		   Thread thread = new ChatClientReceiveThread( bufferedReader );
		   thread.start();
		   
		   //7. Ű���� �Է� ó��
		   while( true ) {
		      System.out.print( ">>" );
		      String input = scanner.nextLine();
						
		      if( "quit".equals( input ) == true ) {
		          // 8. quit �������� ó��
		    	  printWriter.println( "quit" );
		          break;
		      } else {
		          // 9. �޽��� ó��      
		    	  printWriter.println( "message:" + input ); 
		    	  printWriter.flush(); 

		      }
		   }
		   //10. �ڿ�����
		   bufferedReader.close();
		   printWriter.close();
		   if( socket.isClosed() == false ) {
		       socket.close();
		   }
		} catch( IOException ex ) {
		       log( "error:" + ex );
		}


	}

	public static void log( String log ) {
		System.out.println( "[chat_client]" + log);
	}
	
}
