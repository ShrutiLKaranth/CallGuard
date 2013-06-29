package com.neevtech.callguard.android.async;

import com.neevtech.callguard.android.dto.ResponseDTO;

public interface AsyncTaskCallback {

	public void onPreExecute();

	public void onPostExecute(ResponseDTO result);

}
