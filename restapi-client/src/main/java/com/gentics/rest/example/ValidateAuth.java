package com.gentics.rest.example;

import javax.ws.rs.core.MediaType;

import com.gentics.contentnode.rest.model.request.LoginRequest;
import com.gentics.contentnode.rest.model.response.AuthenticationResponse;
import com.sun.jersey.api.client.WebResource;

public class ValidateAuth {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RESTClientHelper helper = new RESTClientHelper();
		
		//Do Login and get the web resource
		helper.doLogin("node", "node");
		WebResource base = helper.getWebResource();
		
		//Get the session id
		String sid = helper.getSessionId();
		
		String sessionSecret = helper.getSessionSecret();
		
		AuthenticationResponse response = base.path("auth").path("validate")
				.path(sid+sessionSecret).get(AuthenticationResponse.class);

		System.out.println("Code: " + response.getResponseInfo().getResponseCode());
		
		helper.destroy();
	}

}
