package com.neevtech.callguard.android.fragment;

import com.neevtech.callguard.android.async.AsyncTaskCallback;
import com.neevtech.callguard.android.async.LoginAsyncTask;
import com.neevtech.callguard.android.dto.ResponseDTO;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoginFragment extends Fragment {

	private LoginAsyncTask loginTask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loginTask = new LoginAsyncTask(getActivity(), loginTaskCallback);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	private AsyncTaskCallback loginTaskCallback = new AsyncTaskCallback() {

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
