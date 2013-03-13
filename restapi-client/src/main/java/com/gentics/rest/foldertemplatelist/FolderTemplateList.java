package com.gentics.rest.foldertemplatelist;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.gentics.contentnode.rest.model.Folder;
import com.gentics.contentnode.rest.model.response.FolderListResponse;
import com.gentics.contentnode.rest.model.response.ResponseCode;
import com.gentics.cr.CRConfigFileLoader;
import com.gentics.cr.CRConfigUtil;
import com.gentics.cr.CRResolvableBean;
import com.gentics.cr.exceptions.CRException;
import com.gentics.cr.rest.ContentRepository;
import com.gentics.cr.util.ContentRepositoryConfig;
import com.gentics.rest.example.RESTClientHelper;
import com.sun.jersey.api.client.WebResource;

public class FolderTemplateList {
	
	private static final String ROOT_FOLDER_ID = "1";

	/**
	 * @param args
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException {
		ConfigDirectory.useThis();
		RESTClientHelper helper = new RESTClientHelper("http://demo-cms.gentics.com/CNPortletapp/rest/");
		CRConfigUtil config = new CRConfigFileLoader("foldertemplatelist", null);
		//Do Login and get the web resource
		helper.doLogin("node", "node");
		
		WebResource base = helper.getWebResource();
		
		//Get the session id
		String sid = helper.getSessionId();
		ContentRepositoryConfig crConfig = new ContentRepositoryConfig(config);
		ContentRepository cr = crConfig.getContentRepository("UTF-8", config);
		
		FolderListResponse folderResponse = base.path("folder").path("getFolders").path(ROOT_FOLDER_ID).queryParam("recursive","true").queryParam("tree", "true").queryParam("sid", sid).get(FolderListResponse.class);
		if (folderResponse != null && folderResponse.getResponseInfo().getResponseCode() == ResponseCode.OK) {
			List<Folder> folders = folderResponse.getFolders();
			if (folders != null) {
				for (Folder f:folders) {
					cr.addObject(processFolder(base, sid, f));
				}
			}
		}
		
		try {
			cr.toStream(System.out);
		} catch (CRException e) {
			e.printStackTrace();
		}
		
		helper.destroy();

	}
	
	private static CRResolvableBean processFolder(WebResource base, String sid, Folder restFolder) {
		CRResolvableBean folder = null;
		
		String folderid = "" + restFolder.getId();
		folder = new CRResolvableBean("10002." + folderid);
		folder.setObj_type("10002");
		folder.setObj_id(folderid);
		folder.set("name", restFolder.getName());
		folder.setMother_id("" + restFolder.getMotherId());
		folder.setMother_type("10002");
		List<Folder> subFolders = restFolder.getSubfolders();
		Collection<CRResolvableBean> children = new Vector<CRResolvableBean>();
		if (subFolders != null) {
			
			for (Folder f : subFolders) {
				children.add(processFolder(base, sid, f));
			}
			
		}
		/*TemplateListResponse templateListResponse = base.path("folder").path("getTemplates").path(folderid).queryParam("sid", sid).get(TemplateListResponse.class);
		if (templateListResponse != null && templateListResponse.getNumItems() > 0) {
			List<Template> templates = templateListResponse.getTemplates();
			if (templates != null) {
				for (Template t : templates) {
					CRResolvableBean templ = new CRResolvableBean("10006." + t.getId());
					templ.setObj_id("" + t.getId());
					templ.setObj_type("10006");
					templ.setMother_id(folderid);
					templ.setMother_type("10002");
					templ.set("name", t.getName());
					children.add(templ);
				}
			}
		}*/
		folder.setChildRepository(children);
		
		return folder;
	}

}
