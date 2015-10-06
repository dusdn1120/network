package com.bit2015.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer {

	private static final int PORT = 60000;
	private static final int BUFFER_SIZE = 1024;
	
		public static void main(String[] args) {
		DatagramSocket datagramSocket = null;
	
		
		try {
		//1. UDP ���� ���� ����		
		datagramSocket = new DatagramSocket( PORT );
		
		//2. ���Ŵ��
		log( "packet ���� ���" );
		DatagramPacket receivePacket = 
			new DatagramPacket( new byte[ BUFFER_SIZE ], BUFFER_SIZE );
		
		datagramSocket.receive( receivePacket ); // ���Ŵ�� ���·� ��
		
		//3. ���� ������ ���
		String message = new String( receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8" );
		log( "packet ����:" + message );
		
		//4. ������ ������
		DatagramPacket sendPacket = 
		new DatagramPacket(
				receivePacket.getData(),
				receivePacket.getLength(),
				receivePacket.getAddress(),
				receivePacket.getPort() );
		datagramSocket.send( sendPacket );
		
			
		} catch ( IOException ex) {
			log( "error:" + ex );
		} finally {
		//5. �ڿ�����
			datagramSocket.close();
		}
		}
	


	public static void log( String log ) {
		System.out.println( "[udp-echo-server]" + log );
		
	}
	
	}
