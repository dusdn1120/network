package com.bit2015.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPEchoClient {
	
	private static final String SERVER_IP = "121.138.83.43";
	private static final int SERVER_PORT = 60000; 
	private static final int BUFFER_SIZE = 1024;
	
	public static void main(String[]args){

		DatagramSocket datagramSocket = null;
		Scanner scanner = new Scanner ( System.in );
		
		try{
			//1. UDP 클라이언트 소켓 생성
			datagramSocket = new DatagramSocket();
			//서버가 아니기 때문에 포트 만들 필요 X
			
			while( true ) {
				
			}
			//2. packet보내기
			System.out.print( ">>" )
			String message = scanner.nextLine();
			if( "quit".equals(message)==false) {
			break;
			}
			byte[] data = message.getBytes();
	
			DatagramPacket sendPacket=
					new DatagramPacket(data, data.length, new InetSocketAddress( SERVER_IP, SERVER_PORT) );
			datagramSocket.send( sendPacket );
			
			//3. 데이터 받기
			DatagramPacket receivePacket = new DatagramPacket( new byte[BUFFER_SIZE], BUFFER_SIZE );
			datagramSocket.receive( receivePacket );
			
			//4. 데이터 출력
			message = 
					new String( receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
			System.out.println( "<<" + message);
			
			//5. 자원정리			
			datagramSocket.close();
			scanner.close();
			
		} catch( IOException ex ){
		log( "error~" + ex );
		}
		}

	public static void log( String log) {
		System.out.println( "[udp-echo-client" + log);
	}

}
