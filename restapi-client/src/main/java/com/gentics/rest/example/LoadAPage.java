package com.gentics.rest.example;

import java.util.Map;

import com.gentics.contentnode.rest.model.Page;
import com.gentics.contentnode.rest.model.Property;
import com.gentics.contentnode.rest.model.Tag;
import com.gentics.contentnode.rest.model.response.PageLoadResponse;
import com.sun.jersey.api.client.WebResource;

public class LoadAPage {

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
		
		PageLoadResponse pageResponse = base.path("page").path("load").path("1").queryParam("sid", sid).get(PageLoadResponse.class);
		
		System.out.println("Response Message: " + pageResponse.getResponseInfo().getResponseMessage());
		
		Page page = pageResponse.getPage();
		
		System.out.println("Pagename: " + page.getName());
		
		Map<String, Tag> tags = page.getTags();
		Tag permTag = tags.get("teasers");
		
		Map<String, Property> props = permTag.getProperties();
		Property nameProp = props.get("text");
		String s = nameProp.getStringValue();
		
		System.out.println("Tag teasers: " + s);
		
		helper.destroy();
	}

}
