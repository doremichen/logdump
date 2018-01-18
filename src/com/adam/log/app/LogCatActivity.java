/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: LogCatActivity.java
 * Brief: This file implements to receive the secret code action
 * 
 * Author: AdamChen
 * Create Date: 2018/1/18
 */
 
package com.adam.log.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LogCatActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private static final String TAG = "LogCatActivity";
	private static final boolean DBG = true;
	
	private static final String LOG_OUTPUT_FILE =
            Environment.getExternalStorageDirectory().toString();
	
	private Button getBtn;
	
	private String log_str = null;
	private Process process = null;
	
	private void debug(String str){
		if(DBG)Log.v(TAG, str);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        debug("+++ onCreate +++");
        
        setContentView(R.layout.main);
        
        getBtn = (Button)this.findViewById(R.id.get_btn);
        
        getBtn.setOnClickListener(this);
        
        getBtn.setVisibility(View.GONE);
        
        

//        Intent it = new Intent();
//        
//    	it.setClass(this, LogCatSvr.class);
//    	
//    	this.startService(it);      
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.get_btn:
			ReadLog();
			WriteLog();
			break;
		}
	}
    
	private void ReadLog(){
		//read log
        try { 
        	process = Runtime.getRuntime().exec("logcat -d");
        	BufferedReader bufferedReader = new BufferedReader(
        			new InputStreamReader(process.getInputStream()), 1024);
        	StringBuilder log=new StringBuilder();
        	String line;
        	while ((line = bufferedReader.readLine()) != null) {
        		log.append(line+"\n");        		        		
        		}
        	
        	log_str = log.toString();
//        	TextView tv = (TextView)findViewById(R.id.textview1);
//        	tv.setText(log.toString());
        	bufferedReader.close();
        	
        	} catch (IOException e) {
        		
        	} 
	}
	
	private void WriteLog(){
		long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
        		"HH-mm-ss");

        String time = dateFormat.format(date);
		
        String path = LOG_OUTPUT_FILE + "/logcat."+time+".txt";
        
		//save log
				try {
					FileWriter fstream = new FileWriter(path, true);
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
    
    
}