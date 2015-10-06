package com.bit2015.network.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {

	private static final int PORT = 22222;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd XX:mm:ss a");
		String data = format.format( new Date() );
		
		System.out.println( data );
		
	}

}
