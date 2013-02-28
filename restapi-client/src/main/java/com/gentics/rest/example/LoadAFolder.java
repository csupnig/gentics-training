package com.gentics.rest.example;

import java.util.Map;

import com.gentics.contentnode.rest.model.Folder;
import com.gentics.contentnode.rest.model.Property;
import com.gentics.contentnode.rest.model.Tag;
import com.gentics.contentnode.rest.model.response.FolderLoadResponse;
import com.sun.jersey.api.client.WebResource;

public class LoadAFolder {

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
		
		FolderLoadResponse folderResponse = base.path("folder").path("load").path("4").queryParam("sid", sid).get(FolderLoadResponse.class);
		
		System.out.println("Response Message: " + folderResponse.getResponseInfo().getResponseMessage());
		
		Folder folder = folderResponse.getFolder();
		
		System.out.println("Foldername: " + folder.getName());
		
		Map<String, Tag> tags = folder.getTags();
		Tag permTag = tags.get("object.name_de");
		
		Map<String, Property> props = permTag.getProperties();
		Property nameProp = props.get("text");
		String s = nameProp.getStringValue();
		
		System.out.println("Object Property name_de: " + s);
		
		helper.destroy();
	}

}
