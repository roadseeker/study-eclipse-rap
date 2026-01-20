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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

import com.innotree.rap.demo.editor.DemoEditorInput;

public class DemoActionBarAdvisor extends ActionBarAdvisor {
	
	//File 메뉴 액션
	private IWorkbenchAction exitAction;
	private IWorkbenchAction importAction;
	private IWorkbenchAction exportAction;
	private IWorkbenchAction saveAction;
	private IWorkbenchAction saveAllAction;
	private Action	newEditorAction;
	
	//Window 메뉴 액션
	private MenuManager showViewMenuMgr;
	private IWorkbenchAction preferencesAction;
	
	//Help 메뉴 액션
	private Action aboutAction;
	
	private static int editorCount = 0;

	public DemoActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
		
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		// 아이콘 로드
		ImageDescriptor exitIcon = AbstractUIPlugin.imageDescriptorFromPlugin("com.innotree.rap.demo", "icons/ttt.gif");
		ImageDescriptor aboutIcon = AbstractUIPlugin.imageDescriptorFromPlugin("com.innotree.rap.demo", "icons/help.gif");
		
		
		
		//File 메뉴 액션
		exitAction = ActionFactory.QUIT.create(window);
		exitAction.setImageDescriptor(exitIcon); //아이콘 설정
		register(exitAction);
		
		importAction = ActionFactory.IMPORT.create(window);
		register(importAction);
		
		exportAction = ActionFactory.EXPORT.create(window);
		register(exportAction);
		
		saveAction = ActionFactory.SAVE.create(window);
		register(saveAction);
		
		saveAllAction = ActionFactory.SAVE_ALL.create(window);
		register(saveAllAction);
		
		//New Editor 액션
		newEditorAction = new Action() {
			@Override
			public void run() {
				try {
					editorCount++;
					DemoEditorInput input = new DemoEditorInput("Document" + editorCount + ".demo");
					window.getActivePage().openEditor((IEditorInput)input, "com.innotree.rap.demo.editor");
					
				}catch (PartInitException e) {
					
				}
			}
		};
		
		newEditorAction.setText("New Editor");
		newEditorAction.setImageDescriptor(window.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		
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
		aboutAction.setImageDescriptor(aboutIcon);  // 아이콘 설정
		register(aboutAction);
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		//File 메뉴
		MenuManager fileMenu = new MenuManager("File", IWorkbenchActionConstants.M_FILE);
		fileMenu.add(newEditorAction);
		fileMenu.add(new Separator());
		fileMenu.add(saveAction);
		fileMenu.add(saveAllAction);
		fileMenu.add(new Separator());
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
        
        toolbar.add(newEditorAction);
        toolbar.add(saveAction);
        toolbar.add(new Separator());
        toolbar.add(aboutAction);
        toolbar.add(exitAction);
    }
	
}