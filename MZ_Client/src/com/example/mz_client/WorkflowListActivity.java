package com.example.mz_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


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
public class WorkflowListActivity extends FragmentActivity
implements WorkflowListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_workflow_list);
		
		
		if (findViewById(R.id.workflow_list) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list itßems should be given the
			// 'activated' state when touched.
			((WorkflowListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.workflow_list))
					.setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks}
	 * indicating that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		System.out.println("WorkflowListActivity : onItemSelected");
/*		if (mTwoPane) {
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

		} else {*/
			
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			final String dstIP = "127.0.0.1";
			final int port  = 7777;


			Thread thread = new Thread(){public void run() {
				try {
					connect(dstIP,port);
				} catch (Exception e) {
				}
			}};

			thread.start();


			System.out.println("String : "+ id);
			Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		//}
	}


	public void connect ( String ipAddr , int port ){

		try {

			Socket socket = new Socket("192.168.0.59", 9999 );

			
			//Server Message 요청 메세지를 전송 한다.
			//선택된 메뉴의 버튼으로 정의 하자...
			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);

			dos.writeUTF("1");

			// 소켓의 입력스트림을 얻는다.
			InputStream in = socket.getInputStream();
		//	DataInputStream dis = new DataInputStream(in);

			
			ObjectInputStream objectIn = new ObjectInputStream(in);
			
			
			// 소켓으로부터 받은 데이터를 출력한다.
//			System.out.println("서버로부터 받은 메세지 : " + dis.readUTF());
			
		//	objectIn.readObject();
			
			System.out.println("연결을 종료합니다.");

		//	dis.close();
			objectIn.close();
			//     socket.close();
			System.out.println("연결이 종료되었습니다.");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
