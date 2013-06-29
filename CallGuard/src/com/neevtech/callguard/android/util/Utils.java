package com.neevtech.callguard.android.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.neevtech.callguard.android.util.Constants.Constants;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.Toast;

public class Utils {
	public static AndroidHttpClient httpclient = null;
	private static Toast toast;

	/**
	 * Checks if the device is connected to the Internet.
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnectedToInternet(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		return connectivityManager.getActiveNetworkInfo() != null ? connectivityManager
				.getActiveNetworkInfo().isConnectedOrConnecting() : false;

	}

	/**
	 * Method is used to get JSON from the Map to params-values pairs.
	 * 
	 * @param hashMap
	 *            , params with values in Map.
	 * @return
	 */
	public static String getJsonBodyParams(HashMap<String, String> hashMap) {
		Iterator<String> it = hashMap.keySet().iterator();
		JSONObject jsonMainObj = new JSONObject();
		String k;
		try {
			while (it.hasNext()) {
				k = it.next();
				jsonMainObj.put(k, hashMap.get(k));
			}
		} catch (Exception e) {
		}
		return jsonMainObj.toString();

	}
	
	/**
	 * @param hashMap
	 * @return
	 * @throws Exception
	 */
	public static String getJsonBodyParams1(HashMap<String, Object> hashMap)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(hashMap);
	}

	/**
	 * validate email text pattern
	 * 
	 * @param target
	 * @return
	 */
	public final static boolean isValidEmail(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
					.matches();
		}
	}

	/**
	 * validate email text pattern
	 * 
	 * @param target
	 * @return
	 */
	public final static boolean isValidPhoneNo(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.PHONE.matcher(target).matches();
		}
	}

	/**
	 * Displays an Information Dialog
	 * 
	 * @param context
	 * @param title
	 * @param message
	 */
	public static void showInfoDialog(Context context, String title,
			String message, DialogInterface.OnClickListener okAction) {
		if (okAction == null) {
			okAction = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {

				}
			};
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message).setTitle(title)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setCancelable(false).setPositiveButton("Ok", okAction);
		AlertDialog alert = builder.create();
		alert.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_SEARCH) {
					// Handle search key
					return true;
				}
				return false;
			}
		});
		alert.show();

	}

	/**
	 * show sort length Toast massage
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showSortLengthToast(Context context, String msg) {
		try {
			if (toast != null) {
				toast.cancel();
			}
			toast = Toast.makeText(context, msg, Constants.TOAST_VISIBLE_TIME);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getOsVersion() {
		StringBuilder builder = new StringBuilder();
		builder.append("android : ").append(Build.VERSION.RELEASE);

		Field[] fields = Build.VERSION_CODES.class.getFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			int fieldValue = -1;

			try {
				fieldValue = field.getInt(new Object());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

			if (fieldValue == Build.VERSION.SDK_INT) {
				builder.append(" : ").append(fieldName).append(" : ");
				builder.append("sdk=").append(fieldValue);
			}
		}
		return builder.toString();
	}
}
