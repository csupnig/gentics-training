package com.gentics.rest.example;

import java.util.Map;
import java.util.Map.Entry;

import com.gentics.contentnode.rest.model.response.PageRenderResponse;
import com.sun.jersey.api.client.WebResource;

public class RenderAPage {

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
		
		PageRenderResponse pageResponse = base.path("page").path("render").path("1").queryParam("tagmap", "true").queryParam("links","frontend").queryParam("sid", sid).get(PageRenderResponse.class);
		
		System.out.println("Response Message: " + pageResponse.getResponseInfo().getResponseMessage());
		
		String content = pageResponse.getContent();
		
		System.out.println("Content: " + content);
		
		Map<String, String> props = pageResponse.getProperties();
		for (Entry<String, String> e : props.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}
		
		
		helper.destroy();
	}

}
