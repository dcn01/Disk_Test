package com.aningsk.disktest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Intent service;
	private serviceReceiver receiver; 
	private SystemInfo systemInfo;
	private TextView showView, showDiskSize, showRamSize, showPartitions;
	private Button startButton, stopButton;
	private boolean startFlag = false; //make sure Service cannot start before stop.
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showView = (TextView)findViewById(R.id.textView1);
        startButton = (Button)findViewById(R.id.button1);
        stopButton = (Button)findViewById(R.id.button2);
        showRamSize = (TextView)findViewById(R.id.textView2);
        showDiskSize = (TextView)findViewById(R.id.textView3);
        showPartitions = (TextView)findViewById(R.id.textView4);
        
        systemInfo = new SystemInfo();
        receiver = new serviceReceiver();
		IntentFilter filter = new IntentFilter("TestEnd");
		registerReceiver(receiver, filter);
        service = new Intent(MainActivity.this, TestService.class);
        
        startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				clickStart(arg0);
			}
        });
        stopButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				clickStop(arg0);
			}
        });
        
        showRamSize.setText(getResources().getString(R.string.ram_size) + ":" + systemInfo.getRamSize());
        showDiskSize.setText(getResources().getString(R.string.disk_size) + ":" + 
        		Integer.parseInt(systemInfo.getDiskSize()) * 512 / 1024 / 1024 + "MB");
        showPartitions.setText(getResources().getString(R.string.partitions) + ":" + systemInfo.getPartitions());
    }
    
    protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
		stopService(service);
    }
    
	public void clickStart(View v) {
		if (startFlag == false) {
			showView.setText(R.string.please_wait);
			startFlag = true;
			startService(service);
		} else {
			showView.setText(R.string.please_stop);
		}
	}
	
	public void clickStop(View v) {
		if (startFlag)
			startFlag = false;
		showView.setText(R.string.test_stop);
		stopService(service);
	}

	private class serviceReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			boolean endFlag = false;
			boolean successFlag = false;
			endFlag = intent.getBooleanExtra("endFlag", false);
			successFlag = intent.getBooleanExtra("successFlag", false);
			if (endFlag == true && successFlag == true)
				showView.setText(R.string.test_success);
			else if (endFlag == true && successFlag == false)
				showView.setText(R.string.test_fail);
		}
		
	}
}

