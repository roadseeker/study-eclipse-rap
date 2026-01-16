package com.innotree.rap.demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class DemoWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public DemoWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}
	
	@Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new DemoActionBarAdvisor(configurer);
	}
	
	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1024, 768));
		configurer.setShowCoolBar(true);
		configurer.setShowPerspectiveBar(true);
		configurer.setTitle("Innotree RAP Application");
		configurer.setShellStyle(SWT.TITLE | SWT.MAX | SWT.RESIZE);
	}
}
