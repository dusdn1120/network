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
			//1. UDP Ŭ���̾�Ʈ ���� ����
			datagramSocket = new DatagramSocket();
			//������ �ƴϱ� ������ ��Ʈ ���� �ʿ� X
			
			while( true ) {
				
			}
			//2. packet������
			System.out.print( ">>" )
			String message = scanner.nextLine();
			if( "quit".equals(message)==false) {
			break;
			}
			byte[] data = message.getBytes();
	
			DatagramPacket sendPacket=
					new DatagramPacket(data, data.length, new InetSocketAddress( SERVER_IP, SERVER_PORT) );
			datagramSocket.send( sendPacket );
			
			//3. ������ �ޱ�
			DatagramPacket receivePacket = new DatagramPacket( new byte[BUFFER_SIZE], BUFFER_SIZE );
			datagramSocket.receive( receivePacket );
			
			//4. ������ ���
			message = 
					new String( receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
			System.out.println( "<<" + message);
			
			//5. �ڿ�����			
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
