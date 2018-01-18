package com.adam.log.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class LogCatSvr extends Service {

	private static final String LOG_OUTPUT_FILE =
            Environment.getExternalStorageDirectory().toString() + "/LogCat.txt";
	
	private String log_str = null;
	private Process process = null;
	
	private static final int START_UPDATE_LOG = 0;
	
	private boolean mRunning = true;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
        
		try {
			process = Runtime.getRuntime().exec("logcat -d");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//		mHandler.sendEmptyMessageDelayed(START_UPDATE_LOG, 3000);  //start logcat file
		
		ReadLog();
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	
	private void ReadLog(){
		//read log
        try {               	
        	BufferedReader bufferedReader = new BufferedReader(
        			new InputStreamReader(process.getInputStream()));
        	StringBuilder log=new StringBuilder();
        	String line;
        	while (mRunning && (line = bufferedReader.readLine()) != null) {
        		log.append(line+"\n");
        		
        		if (!mRunning) {
					break;
				}
        		
        		log_str = log.toString();
        		
        		WriteLog();
        		
        		}
        	
//        	log_str = log.toString();
//        	TextView tv = (TextView)findViewById(R.id.textview1);
//        	tv.setText(log.toString());
        	bufferedReader.close();
        	
        	} catch (IOException e) {
        		
        	} 
	}
	
	private void WriteLog(){
		//save log
				try {
					FileWriter fstream = new FileWriter(LOG_OUTPUT_FILE, true);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write(log_str+"\n");
					out.newLine();
					out.flush();
			        out.close();
			        fstream.close();			
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	private Handler mHandler = new Handler(){

		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			
			switch(msg.what){
			case START_UPDATE_LOG:
				ReadLog();
				WriteLog();
//				mHandler.sendEmptyMessageDelayed(START_UPDATE_LOG, 3000);
				break;
			default:
				super.dispatchMessage(msg);
				break;
			}
		}
	};

}
