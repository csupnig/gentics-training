package com.gentics.example;

import java.util.Collection;
import java.util.Vector;

import com.gentics.cr.CRConfig;
import com.gentics.cr.CRRequest;
import com.gentics.cr.CRResolvableBean;
import com.gentics.cr.RequestProcessor;
import com.gentics.cr.exceptions.CRException;

public class BuildingRequestProcessor extends RequestProcessor {

	public BuildingRequestProcessor(CRConfig arg0) throws CRException {
		super(arg0);
	}

	@Override
	public Collection<CRResolvableBean> getObjects(CRRequest request, boolean doNavigation)
			throws CRException {
		Collection<CRResolvableBean> beans = new Vector<CRResolvableBean>();
		
		for (int i = 1; i < 10; i++) {
			CRResolvableBean bean = new CRResolvableBean(i);
			bean.set("name", "Bean Name" + i);
			bean.set("content", "This is Content #set($test = 1) $test $test.class.name LINK: <plink id=\"10007.2\" /> and some more content.");
			beans.add(bean);
		}
		
		
		return beans;
	}
	
	@Override
	public void finalize() {
		// TODO Auto-generated method stub

	}

}
