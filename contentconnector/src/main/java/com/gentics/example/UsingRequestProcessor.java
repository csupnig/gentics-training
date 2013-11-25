package com.gentics.example;

import java.net.URISyntaxException;
import java.util.Collection;

import com.gentics.cr.CRConfigFileLoader;
import com.gentics.cr.CRConfigUtil;
import com.gentics.cr.CRDatabaseFactory;
import com.gentics.cr.CRRequest;
import com.gentics.cr.CRResolvableBean;
import com.gentics.cr.RequestProcessor;
import com.gentics.cr.exceptions.CRException;
import com.gentics.example.conf.ConfigDirectory;

public class UsingRequestProcessor {

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
		request.setRequestFilter("object.obj_type == 10007");
		
	 	Collection<CRResolvableBean> beans =  rp.getObjects(request);
	
	 	for (CRResolvableBean bean : beans) {
			System.out.println(bean.getContentid() + " - " + bean.getString("name"));
		}
	 	
	 	rp.finalize();
	 	CRDatabaseFactory.destroy();
	}

}
