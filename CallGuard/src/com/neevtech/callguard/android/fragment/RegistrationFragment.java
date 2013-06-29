package com.neevtech.callguard.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neevtech.callguard.R;
import com.neevtech.callguard.android.async.AsyncTaskCallback;
import com.neevtech.callguard.android.async.RegisterAsyncTask;
import com.neevtech.callguard.android.dto.ResponseDTO;

public class RegistrationFragment extends Fragment {

	private RegisterAsyncTask registerTask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerTask = new RegisterAsyncTask(getActivity(),
				registerTaskCallback);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.registration_page, container);
		
		return view;

	}

	private AsyncTaskCallback registerTaskCallback = new AsyncTaskCallback() {

		@Override
		public void onPreExecute() {
			// TODO called before web-service call

		}

		@Override
		public void onPostExecute(ResponseDTO result) {
			// TODO called after web-service call

		}
	};
}
