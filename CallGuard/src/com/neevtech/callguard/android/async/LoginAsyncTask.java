package com.neevtech.callguard.android.async;

import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import android.content.Context;
import android.util.Log;

import com.neevtech.callguard.android.dto.RegisterResponseDTO;
import com.neevtech.callguard.android.dto.ResponseDTO;
import com.neevtech.callguard.android.util.POJOToJSON;
import com.neevtech.callguard.android.util.Utils;
import com.neevtech.callguard.android.util.Constants.WebServiceConstants;
import com.neevtech.callguard.android.util.http.HTTPJsonPost;

public class LoginAsyncTask extends BaseAsyncTask {

	private final String LOG_TAG = LoginAsyncTask.class.getSimpleName();

	public LoginAsyncTask(Context context, AsyncTaskCallback taskCallback) {
		super(context, taskCallback);
	}

	@Override
	protected ResponseDTO doInBackground(HashMap<String, String>... params) {
		try {
			String bodyParams = Utils.getJsonBodyParams(params[0]);

			String followerListURL = HTTPJsonPost.getExactURL(
					WebServiceConstants.SERVER_BASE_URL,
					WebServiceConstants.LOGIN_URL);
			HTTPJsonPost jsonPost = new HTTPJsonPost();
			String postData = jsonPost.postData(followerListURL, bodyParams);
			Log.d(LOG_TAG, "Request" + bodyParams);
			Log.d(LOG_TAG, "response" + "data : " + postData);
			RegisterResponseDTO responseDTO = (RegisterResponseDTO) POJOToJSON
					.fromJson(postData, ResponseDTO.class);

			return responseDTO;
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
