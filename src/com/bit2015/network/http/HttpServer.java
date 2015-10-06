package com.bit2015.network.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	private static final int PORT = 8819;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		
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
				
				Thread thread = new HttpThread( socket );
				thread.start();
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
	
		System.out.println( "[http-server]" + log );
	}
	}

