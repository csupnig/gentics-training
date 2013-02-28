package com.gentics.rest.example;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.gentics.contentnode.rest.model.Page;
import com.gentics.contentnode.rest.model.Property;
import com.gentics.contentnode.rest.model.Tag;
import com.gentics.contentnode.rest.model.request.PageSaveRequest;
import com.gentics.contentnode.rest.model.response.GenericResponse;
import com.gentics.contentnode.rest.model.response.Message;
import com.gentics.contentnode.rest.model.response.PageLoadResponse;
import com.sun.jersey.api.client.WebResource;

public class AlterAPage {

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
		Property teaserProp = props.get("text");
		String s = teaserProp.getStringValue();
		System.out.println("Teaser was: " + s);
		teaserProp.setStringValue("THIS IS AN EXAMPLE. WE ARE SETTING THE TEASER!");
		
		PageSaveRequest psr = new PageSaveRequest();
		psr.setPage(page);
		
		//Unlock the page
		psr.setUnlock(true);
		GenericResponse savePage = base.path("page").path("save").path(page.getId().toString()).queryParam("sid", sid).entity(psr, MediaType.APPLICATION_JSON).post(GenericResponse.class);
		List<Message> messages = savePage.getMessages();
		for (Message message : messages) {
			System.out.println("Message: " + message.getMessage());
		}
		
		helper.destroy();
	}

}
