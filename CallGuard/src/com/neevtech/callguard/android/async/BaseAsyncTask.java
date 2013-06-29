package com.neevtech.callguard.android.async;

import java.util.HashMap;

import android.content.Context;
import android.os.AsyncTask;

import com.neevtech.callguard.android.dto.ResponseDTO;

/**
 * 
 */
public abstract class BaseAsyncTask extends
		AsyncTask<HashMap<String, String>, Void, ResponseDTO> {
	protected Context context;
	private AsyncTaskCallback taskCallback;

	public BaseAsyncTask(Context context, AsyncTaskCallback taskCallback) {
		this.context = context;
		this.taskCallback = taskCallback;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (taskCallback != null) {
			taskCallback.onPreExecute();
		}
	}

	@Override
	protected void onPostExecute(ResponseDTO result) {
		super.onPostExecute(result);
		if (taskCallback != null) {
			taskCallback.onPostExecute(result);
		}
	}

}
