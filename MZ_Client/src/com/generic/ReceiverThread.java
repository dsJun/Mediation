package com.generic;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.os.Handler;
import android.os.Message;

public class ReceiverThread extends Thread{

	private ServerSocket serverSocket;
	private Handler handler ;

	public ReceiverThread(){


	}
	public ReceiverThread(Handler hander) {
		// TODO Auto-generated constructor stub
		
		try {
			serverSocket = new ServerSocket(9998);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.handler = hander;
		
		
	}
	@Override
	public void run() {

		while ( true ){
			System.out.println("설");
			try {
				System.out.println("요청을 기다림..");



				Socket socket = serverSocket.accept();

				System.out.println(" " + socket.getInetAddress()
						+ "로부터 연결요청이 들어왔습니다.");

				InputStream in = socket.getInputStream();
				DataInputStream dis = new DataInputStream(in);

		//		System.out.println("서로부터 받은 메세지 : " + dis.readUTF());
				String str = dis.readUTF();
				Message msg = new Message();
				msg.what = 0 ;
				msg.arg1 = Integer.parseInt(str);
				
				handler.sendMessage(msg);

					dis.close();
				socket.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Why1");
			}
			System.out.println("Why2");

		}
	}
}
