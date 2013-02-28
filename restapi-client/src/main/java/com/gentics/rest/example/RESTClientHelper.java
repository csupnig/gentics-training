package com.gentics.rest.example;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.gentics.contentnode.rest.model.request.LoginRequest;
import com.gentics.contentnode.rest.model.response.LoginResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;

public class RESTClientHelper {
	
	private WebResource webResource;
	
	private String sessionID;
	
	private Client client;
	
	public RESTClientHelper () {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		client = Client.create(clientConfig);
		client.addFilter(new ClientFilter() {
			private ArrayList<Object> cookies;

			@Override
			public ClientResponse handle(ClientRequest request)
					throws ClientHandlerException {
				if (cookies != null) {
					request.getHeaders().put("Cookie", cookies);
				}
				ClientResponse response = getNext().handle(request);
				if (response.getCookies() != null) {
					if (cookies == null) {
						cookies = new ArrayList<Object>();
					}
					// simple addAll just for illustration (should probably
					// check for duplicates and expired cookies)
					cookies.addAll(response.getCookies());
				}
				return response;
			}
		});
		
		this.webResource = client.resource("http://demo-cms.gentics.com/CNPortletapp/rest/");
	}
	
	public void doLogin(String username, String password) {
		LoginRequest request = new LoginRequest();
		request.setLogin("node");
		request.setPassword("node");
		LoginResponse response = getWebResource().path("auth").path("login")
				.entity(request, MediaType.APPLICATION_JSON).post(LoginResponse.class);

		this.sessionID = response.getSid();
	}
	
	/**
	 * This method acts as helper
	 * @return
	 */
	public WebResource getWebResource() {
		return this.webResource;
	}
	
	public String getSessionId() {
		return this.sessionID;
	}
	
	public void destroy() {
		client.destroy();
	}
}
