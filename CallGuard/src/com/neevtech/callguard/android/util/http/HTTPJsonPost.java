package com.neevtech.callguard.android.util.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HTTPJsonPost {

	private static final String LOG_TAG = HTTPJsonPost.class.getSimpleName();

	public static String getExactURL(String baseURL, String referenceURL) {

		StringBuffer mURL = new StringBuffer();
		mURL.append(baseURL);
		mURL.append(referenceURL);
		Log.d(LOG_TAG, "url :" + mURL.toString());
		return mURL.toString();
	}

	public String postData(String url, String bodyParams) {
		// Create a new HttpClient and Post Header

		HttpParams myParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(myParams, 10000);
		HttpProtocolParams.setVersion(myParams, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(myParams, HTTP.UTF_8);
		HttpConnectionParams.setSoTimeout(myParams, 10000);
		HttpClient httpclient = new DefaultHttpClient(myParams);
		String responseText;
		try {

			HttpPost httppost = new HttpPost(url.toString());
			httppost.setHeader("Content-type", "application/json");

			StringEntity se = new StringEntity(bodyParams);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			httppost.setEntity(se);

			HttpResponse response = httpclient.execute(httppost);
			responseText = EntityUtils.toString(response.getEntity());
			Log.d(LOG_TAG, url);
			Log.w(LOG_TAG, "Rsponse" + responseText + ":");

		} catch (Exception e) {
			e.printStackTrace();
			responseText = null;
		}
		return responseText;
	}

	public static String getData(String url) {

		String responseText;
		try {

			HttpGet get = new HttpGet(url);

			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(get);

			responseText = EntityUtils.toString(response.getEntity());
			Log.w("tag", url + ": " + responseText);

		} catch (Exception e) {
			e.printStackTrace();
			responseText = null;
		}
		return responseText;
	}
}
