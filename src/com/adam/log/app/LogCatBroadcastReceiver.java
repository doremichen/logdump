/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: LogCatBroadcastReceiver.java
 * Brief: This file implements to receive the secret code action
 * 
 * Author: AdamChen
 * Create Date: 2018/1/18
 */
/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: LogCatBroadcastReceiver.java
 * Brief: This file implements to receive the secret code action
 * 
 * Author: AdamChen
 * Create Date: 2018/1/18
 */
 
package com.adam.log.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import android.provider.Telephony;
import static android.provider.Telephony.Intents.SECRET_CODE_ACTION;

public class LogCatBroadcastReceiver extends BroadcastReceiver {

//	@Override
	public void onReceive(Context context, Intent intent) {
    	
		if (intent.getAction().equals(SECRET_CODE_ACTION)) {
			Intent i = new Intent(Intent.ACTION_MAIN);
			i.setClass(context, LogCatActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		}
	}

}

