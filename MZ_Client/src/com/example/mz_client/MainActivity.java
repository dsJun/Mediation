package com.example.mz_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;


import com.example.mz_client.dummy.WorkflowContent;
import com.example.mz_client.dummy.WorkflowContent.WorkflowA;
import com.generic.ReceiverThread;
import com.generic.Workflow;
import com.generic.WorkflowManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity{

	private Button buttonWflow;
	private Button systemLog  ;
	private Button missingFile;


	private boolean isSocketConnected ;

	final String dstIP = "192.168.0.59";
	final int port  = 9999;
	ReceiverThread receiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);


		buttonWflow = (Button)findViewById(R.id.button_workflow_list);	
		systemLog   = (Button)findViewById(R.id.button_system_log);
		missingFile = (Button)findViewById(R.id.button_missing_file);

		startService(new Intent(MainActivity.this, 
				NotifyingService.class));

	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//		if ( receiver == null){		
		//			receiver = new ReceiverThread(hander); 
		//			receiver.start();
		//		}
	}	



	public void getWorkflowList(View view){

		System.out.println("Hello");


		isSocketConnected = false;

		Thread thread = new Thread(){
			public void run() {
				synchronized (this) {
					try {
						isSocketConnected = 	connect(dstIP,port);
					} catch (Exception e) {

					}

					notify();

				}
			}};

			thread.start();

			synchronized (thread) {

				try {
					thread.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if ( isSocketConnected ){
				Intent detailIntent = new Intent(this, WorkflowListActivity.class);
				startActivity(detailIntent);
			}else{

				Toast.makeText(this, "Fail to Connect Socket", 500).show();

			}

	}


	public void getSystemLog (View view){

		System.out.println("System Log");

		Intent detailIntent = new Intent(this, SystemlogListActivity.class);
		startActivity(detailIntent);


	}

	public void getMissingFile(View view){
		System.out.println("Missing File");

	}




	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		stopService(new Intent(MainActivity.this, 
				NotifyingService.class));

	}




	@SuppressLint("HandlerLeak")
	Handler hander = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub

			System.out.println("Message : " + msg.what + " : " + msg.arg1 );

		}	
	};


	public boolean connect ( String ipAddr , int port ){

		try {
			System.out.println("Before Socket Connection");
			//	Socket socket = new Socket("192.000.0.0", 9999 );
			@SuppressWarnings("resource")
			Socket socket = new Socket();
			SocketAddress socketAddr = new InetSocketAddress("172.30.24.12", 9999);
			socket.connect(socketAddr, 500);

			//Server Message 요청 메세지를 전송 한다.
			//선택된 메뉴의 버튼으로 정의 하자...
			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);

			dos.writeUTF("1");

			ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());

			try {

				WorkflowManager  wflowManager = (WorkflowManager)objectIn.readObject();

				ArrayList<Workflow> wflList = wflowManager.getAllWorkflow();


				for ( int i = 0 ; i < wflList.size() ; i++){
					Workflow tmp = wflList.get(i);

					System.out.println("tmp.isbStates : " +  tmp.isbStates());
					WorkflowContent.addItem(new WorkflowA(""+(i+1) , tmp.getsWflowName() , tmp.isbStates() ));

				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			//	dis.close();
			objectIn.close();
			socket.close();
			System.out.println("연결 종료");

		} catch (UnknownHostException e) {
			System.out.println("Can not Connect Host1");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can not Connect Host2");

			e.printStackTrace();

			return false;

		}


		return true;


	}


}
