package com.bit2015.network.chat;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

	private static final int PORT = 1120;
		// 192.168.1.113 1120
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		List<PrintWriter> listPrintWriters = new ArrayList<PrintWriter>();
		
		try 
		{
			//1. �������ϻ���
			serverSocket = new ServerSocket();
			
			//2. binding
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();
			serverSocket.bind( new InetSocketAddress( hostAddress, PORT ) );
			log ( "���� ��ٸ� :" + hostAddress + ":" + PORT);
			
			//3. ���� ��û ��ٸ�
			while( true ) {
				Socket socket = serverSocket.accept ();
				
				Thread thread = new ChatServerProcessThread( socket, listPrintWriters );
	//			thread.start();
			}
			}catch( IOException ex ) {		
				log( "error:" + ex );
			}finally {
				if( serverSocket != null && serverSocket.isClosed() == false ) {
				try {
					
					serverSocket.close();
				
				}catch( IOException ex) {
				}
			}
		}
	}
	

	public static void log( String log ) {
	
		System.out.println( "[chat-server]" + log );
	}
	}
