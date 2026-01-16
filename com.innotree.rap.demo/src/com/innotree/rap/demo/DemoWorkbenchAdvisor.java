package com.innotree.rap.demo;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class DemoWorkbenchAdvisor extends WorkbenchAdvisor {

	@Override
	public String getInitialWindowPerspectiveId() {
		return "com.innotree.rap.demo.perspective";
	}

	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		return new DemoWorkbenchWindowAdvisor(configurer);
		
	}

}
