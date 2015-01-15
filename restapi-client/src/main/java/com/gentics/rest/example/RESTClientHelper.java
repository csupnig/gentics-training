package com.gentics.rest.example;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;

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
	
	private static final String DEFAULTURL = "http://localhost/CNPortletapp/rest/";
	
	private static final String SESSION_SECRET_KEY ="GCN_SESSION_SECRET";
	
	private WebResource webResource;
	
	private String sessionID;
	
	private Client client;
	
	private ArrayList<Object> cookies;
	
	public RESTClientHelper() {
		this(null);
	}
	
	public RESTClientHelper (final String resturl) {
		
		
		String restBase = resturl;
		if (restBase == null) {
			restBase = DEFAULTURL;
		}
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		client = Client.create(clientConfig);
		client.addFilter(new ClientFilter() {
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
		
		this.webResource = client.resource(restBase);
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
	
	public String getSessionSecret() {
		for (Object o : this.cookies) {
			if (o instanceof NewCookie) {
				System.out.println(((NewCookie)o).getName());
				NewCookie c = (NewCookie)o;
				if (SESSION_SECRET_KEY.equals(c.getName())){
					return c.getValue();
				}
			}
		}
		return "";
	}
	
	public void destroy() {
		client.destroy();
	}
}
