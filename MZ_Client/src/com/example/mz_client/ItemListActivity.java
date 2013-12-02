package com.example.mz_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;



import com.example.mz_client.dummy.WorkflowContent;
import com.example.mz_client.dummy.WorkflowContent.WorkflowA;
import com.generic.Workflow;
import com.generic.WorkflowManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;


/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details
 * (if present) is a {@link ItemDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link ItemListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ItemListActivity extends FragmentActivity
implements ItemListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	private boolean isSocketConnected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);

		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			System.out.println("SDSFSDFSDFSDF");

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ItemListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.item_list))
					.setActivateOnItemClick(true);
		}

		System.out.println("hello");

		// TODO: If exposing deep links into your app, handle intents here.
	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks}
	 * indicating that the item with the given ID was selected.
	 */
	@SuppressLint("ShowToast")
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
			ItemDetailFragment fragment = new ItemDetailFragment();
			fragment.setArguments(arguments);

			getSupportFragmentManager().beginTransaction()
			.replace(R.id.item_detail_container, fragment)
			.commit();

			System.out.println("mTwoPane");

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			final String dstIP = "127.0.0.1";
			final int port  = 7777;

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

					System.out.println("String : "+ id);
					//			Intent detailIntent = new Intent(this, ListViewLoader.class);
					//detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
					//			startActivity(detailIntent);

					Intent detailIntent = new Intent(this, WorkflowListActivity.class);
					//			detailIntent.putExtra(WorkflowListFragment., id);
					startActivity(detailIntent);
				}else{

					Toast.makeText(this, "Fail to Connect Socket", 500).show();

				}

		}
	}


	public boolean connect ( String ipAddr , int port ){

		try {
			System.out.println("Before Socket Connection");
			//	Socket socket = new Socket("192.000.0.0", 9999 );
			@SuppressWarnings("resource")
			Socket socket = new Socket();
			SocketAddress socketAddr = new InetSocketAddress("192.168.0.59", 9999);
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

			System.out.println("연결을 종료합니다.");

			//	dis.close();
			objectIn.close();
			//     socket.close();
			System.out.println("연결이 종료되었습니다.");

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
