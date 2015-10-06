package com.bit2015.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class ChatServerProcessThread extends Thread {
	
	private static final String PROTOCOL_DIVIDER = ":";
	
	private String nickname;
	private Socket socket;
	private List<PrintWriter> listPrintWriters;
	public ChatServerProcessThread( Socket socket, List<PrintWriter> listPrintWriters ) {
		this.socket = socket;
		this.listPrintWriters = listPrintWriters;
	}
	@Override
	public void run() {
		//���� �Ǵ� �κ�
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;
		
	try {
		//1. ��Ʈ�� ���
		bufferedReader = 
		new BufferedReader ( new InputStreamReader( socket.getInputStream(),"UTF-8" ) ); 
		printWriter =
				new PrintWriter( socket.getOutputStream() ) ;
		//2. ����Ʈ ȣ��Ʈ ���� ���
		InetSocketAddress inetSocketAddress =
				(InetSocketAddress)socket.getRemoteSocketAddress();
		String remoteHostAddress = inetSocketAddress.getHostName();
		int remoteHostPort = inetSocketAddress.getPort();
		ChatServer.log( "����� from " + remoteHostAddress + ":" + remoteHostPort );
		
		//3. ��ûó��
		while( true ) {
			String request = bufferedReader.readLine();
			if( request == null ) {
				ChatServer.log( "Ŭ���̾�Ʈ�� ���� ���� ����" );
				doQuit( printWriter );
				break;
			}
			
			String[] tokens = request.split( PROTOCOL_DIVIDER );
			if( "join".equals( tokens[0] ) ) {
				doJoin( printWriter, tokens[1] );
			} else if( "message".equals( tokens[0] ) ){
				doMessage( tokens[1] );
			} else if( "quit".equals( tokens[0] ) ){
				doQuit( printWriter );
				break;
			} else {
				ChatServer.log( "����: �� �� ���� ��û���(" + tokens[0] + ")"  );
			}
		}
		
		//4. �ڿ�����
		bufferedReader.close();
		printWriter.close();
		if( socket.isClosed() == false) {
		socket.close();
		}
	}catch( IOException ex ) {
		ChatServer.log( "error:" + ex );
		//Ŭ���̾�Ʈ�� ������ ���� , ��������� ������ ���� ����
		doQuit( printWriter );
	}
	}
	private void doQuit( PrintWriter printWriter ) {
		// PrintWriter ����
		removePrintWriter( printWriter );
		
		// ���� �޽��� ��ε� ĳ����
		String data = nickname + "���� �����Ͽ����ϴ�.";
		broadcast ( data );
	}
	
	private void doMessage( String message ) {
		String data = nickname + ":" + message;
		broadcast( data );
	}
	
	private void doJoin( PrintWriter printWriter, String nickname ) {
		
		//1. �г��� ����
		this.nickname = nickname;
		
		//2. �޽��� ��ε� ĳ����
		String message = nickname + "���� �����߽��ϴ�.";
		broadcast ( message ) ;
		
		//3. 
		addPrintWriter( printWriter );
		
		//4. ack
		printWriter.println( "join:ok" );
	}
	
	private void addPrintWriter( PrintWriter printWriter ) {
		synchronized( listPrintWriters ) {
		listPrintWriters.add( printWriter ) ;
	}
	}
	private void removePrintWriter( PrintWriter printWriter) {
		listPrintWriters.remove( printWriter );
	}

	private void broadcast( String data ) {
		
//		for( PrintWriter printEtiyrt : listPrintWriters ) {
//			printWriter.println( data );
//		}
		
		int count = listPrintWriters.size();
		for( int i = 0; i < count; i++ ) {
			PrintWriter printWriter = listPrintWriters.get( i );
			printWriter.println( data );
			printWriter.flush();
		}
	}
}
	