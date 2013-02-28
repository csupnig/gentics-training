package com.gentics.example;

import java.net.URISyntaxException;

import com.gentics.cr.CRConfigFileLoader;
import com.gentics.cr.CRConfigUtil;
import com.gentics.cr.CRDatabaseFactory;
import com.gentics.cr.CRRequest;
import com.gentics.cr.CRResolvableBean;
import com.gentics.cr.RequestProcessor;
import com.gentics.cr.exceptions.CRException;
import com.gentics.example.conf.ConfigDirectory;

public class RenderingContent {

	/**
	 * @param args
	 * @throws URISyntaxException 
	 * @throws CRException 
	 */
	public static void main(String[] args) throws URISyntaxException, CRException {
		//Init config directory like this or via -Dcom.gentics.portalnode.confpath="..."
		ConfigDirectory.useThis();

		CRConfigUtil config = new CRConfigFileLoader("ccr", null);
		
		RequestProcessor rp = config.getNewRequestProcessorInstance(1);
		
		CRRequest request = new CRRequest();
		request.setDoReplacePlinks(true);
		request.setDoVelocity(true);
		request.setContentid("10007.1");
	 	CRResolvableBean bean =  rp.getContent(request);
	 	
	 	
		System.out.println(bean.getString("name"));
		System.out.println(bean.getContent());
		
	 	
	 	rp.finalize();
	 	CRDatabaseFactory.destroy();
	}

}
