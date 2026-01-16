package com.innotree.rap.demo;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

public class DemoActionBarAdvisor extends ActionBarAdvisor {
	
	//File 메뉴 액션
	private IWorkbenchAction exitAction;
	private IWorkbenchAction importAction;
	private IWorkbenchAction exportAction;
	
	//Window 메뉴 액션
	private MenuManager showViewMenuMgr;
	private IWorkbenchAction preferencesAction;
	
	//Help 메뉴 액션
	private Action aboutAction;

	public DemoActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
		
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		//File 메뉴 액션
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);
		
		importAction = ActionFactory.IMPORT.create(window);
		register(importAction);
		
		exportAction = ActionFactory.EXPORT.create(window);
		register(exportAction);
		
		//Window 메뉴 - Show View
		showViewMenuMgr = new MenuManager("Show View", "showView");
		showViewMenuMgr.add(ContributionItemFactory.VIEWS_SHORTLIST.create(window));
		
		//Window  메뉴 - Preferences
		preferencesAction = ActionFactory.PREFERENCES.create(window);
		register(preferencesAction);
		
		//Help 메뉴 - About
		aboutAction = new Action() {
			@Override
			public void run() {
				Shell shell = window.getShell();
				Bundle bundle = Platform.getBundle(PlatformUI.PLUGIN_ID);
				
				Object version = bundle.getHeaders().get(Constants.BUNDLE_VERSION);
				MessageDialog.openInformation(shell, "Innotree RAP Demo", "Running on RAP version " + version + "\n\nPowered by Eclipse RAP");
				
			}
		};
		
		aboutAction.setText("About");
		aboutAction.setId("com.innotree.rap.demo.about");
		register(aboutAction);
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		//File 메뉴
		MenuManager fileMenu = new MenuManager("File", IWorkbenchActionConstants.M_FILE);
		fileMenu.add(importAction);
        fileMenu.add(exportAction);
        fileMenu.add(new Separator());
        fileMenu.add(exitAction);
        menuBar.add(fileMenu);
		
		//Window 메뉴
		MenuManager windowMenu = new MenuManager("Window", IWorkbenchActionConstants.M_WINDOW);
		windowMenu.add(showViewMenuMgr);
        windowMenu.add(new Separator());
        windowMenu.add(preferencesAction);
        menuBar.add(windowMenu);
		
		//Help 메뉴
		MenuManager helpMenu = new MenuManager("Help", IWorkbenchActionConstants.M_HELP);
		helpMenu.add(aboutAction);
		menuBar.add(helpMenu);
	}
	
	@Override
    protected void fillCoolBar(ICoolBarManager coolBar) {
        // 메인 툴바
        IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar, "main"));

        toolbar.add(aboutAction);
        toolbar.add(new Separator());
        toolbar.add(exitAction);
    }
	
}