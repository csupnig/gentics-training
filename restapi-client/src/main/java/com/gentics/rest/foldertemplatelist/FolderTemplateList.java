package com.gentics.rest.foldertemplatelist;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.gentics.contentnode.rest.model.File;
import com.gentics.contentnode.rest.model.Folder;
import com.gentics.contentnode.rest.model.Page;
import com.gentics.contentnode.rest.model.Template;
import com.gentics.contentnode.rest.model.response.FileListResponse;
import com.gentics.contentnode.rest.model.response.FolderListResponse;
import com.gentics.contentnode.rest.model.response.PageListResponse;
import com.gentics.contentnode.rest.model.response.ResponseCode;
import com.gentics.contentnode.rest.model.response.TemplateListResponse;
import com.gentics.cr.CRConfigFileLoader;
import com.gentics.cr.CRConfigUtil;
import com.gentics.cr.CRResolvableBean;
import com.gentics.cr.exceptions.CRException;
import com.gentics.cr.rest.ContentRepository;
import com.gentics.cr.util.ContentRepositoryConfig;
import com.gentics.rest.example.RESTClientHelper;
import com.sun.jersey.api.client.WebResource;

public class FolderTemplateList {
	
	private static final boolean DO_FILES = false;
	private static final boolean DO_PAGES = false;
	private static final boolean DO_TEMPLATES = false;
	
	private static boolean doFiles = false;
	private static boolean doPages = false;
	private static boolean doTemplates = false;

	/**
	 * @param args
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException {
		ConfigDirectory.useThis();
		CRConfigUtil config = new CRConfigFileLoader("foldertemplatelist", null);
		doFiles = config.getBoolean("DO_FILES", DO_FILES);
		doPages = config.getBoolean("DO_PAGES", DO_PAGES);
		doTemplates = config.getBoolean("DO_TEMPLATES", DO_TEMPLATES);
		
		String rootIds = config.getString("ROOT_IDS");
		String[] rootidarray = rootIds.split(",");
		
		RESTClientHelper helper = new RESTClientHelper(config.getString("REST_BASE_URL", "http://demo-cms.gentics.com/CNPortletapp/rest/"));
		
		//Do Login and get the web resource
		helper.doLogin(config.getString("USERNAME","node"), config.getString("PASSWORD", "node"));
		
		WebResource base = helper.getWebResource();
		
		//Get the session id
		String sid = helper.getSessionId();
		ContentRepositoryConfig crConfig = new ContentRepositoryConfig(config);
		ContentRepository cr = crConfig.getContentRepository("UTF-8", config);
		for (String folderid : rootidarray) {
			FolderListResponse folderResponse = base.path("folder").path("getFolders").path(folderid).queryParam("recursive","true").queryParam("tree", "true").queryParam("sid", sid).get(FolderListResponse.class);
			if (folderResponse != null && folderResponse.getResponseInfo().getResponseCode() == ResponseCode.OK) {
				List<Folder> folders = folderResponse.getFolders();
				if (folders != null) {
					for (Folder f:folders) {
						cr.addObject(processFolder(base, sid, f));
					}
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
		if (doPages) {
			PageListResponse pageListResponse = base.path("folder").path("getPages").path(folderid).queryParam("sid", sid).get(PageListResponse.class);
			if (pageListResponse != null && pageListResponse.getNumItems() > 0) {
				List<Page> pages = pageListResponse.getPages();
				if (pages != null) {
					for (Page p : pages) {
						CRResolvableBean page = new CRResolvableBean("10007." + p.getId());
						page.setObj_id("" + p.getId());
						page.setObj_type("10007");
						page.setMother_id(folderid);
						page.setMother_type("10002");
						page.set("name", p.getName());
						children.add(page);
					}
				}
			}
		}
		if (doFiles) {
			FileListResponse fileListResponse = base.path("folder").path("getFiles").path(folderid).queryParam("sid", sid).get(FileListResponse.class);
			if (fileListResponse != null && fileListResponse.getNumItems() > 0) {
				List<File> files = fileListResponse.getFiles();
				if (files != null) {
					for (File f : files) {
						CRResolvableBean file = new CRResolvableBean("10008." + f.getId());
						file.setObj_id("" + f.getId());
						file.setObj_type("10008");
						file.setMother_id(folderid);
						file.setMother_type("10002");
						file.set("name", f.getName());
						children.add(file);
					}
				}
			}
		}
		if (doTemplates) {
			TemplateListResponse templateListResponse = base.path("folder").path("getTemplates").path(folderid).queryParam("sid", sid).get(TemplateListResponse.class);
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
			}
		}
		folder.setChildRepository(children);
		
		return folder;
	}

}
