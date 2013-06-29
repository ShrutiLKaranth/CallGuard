package com.neevtech.callguard.android.util.Constants;

/**
 *
 */
public class WebServiceConstants {

	// Web service Urls
	public static final String SERVER_BASE_URL = "http://localhost:20110/CallGuard/webresources/";
	public static final String REGISTER_URL = "users/createUser";
	public static final String LOGIN_URL = "user/login";

	// webService status
	public static final String STATUS_SUCCESS = "SUCCESS";
	public static final String STATUS_FAILURE = "FAILURE";

	// webservice messages
	public static String REGISTERED_SUCCESS_MSG = "User registered Successfully";
	public static String REGISTERED_FAILURE_MSG = "Unable to register user";
	public static String REGISTERED_USER_ALREADY_REGISTERED = "User already registered";
	public static String LOGIN_SUCCESS = "LOGIN_SUCCESS";
	public static String LOGIN_FAILURE = "LOGIN_FAILURE";

}
