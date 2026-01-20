package com.innotree.rap.demo.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class SampleAction implements IWorkbenchWindowActionDelegate{
	
	private IWorkbenchWindow window;

	/*
	 * 액션이 실행될때 호출됩니다.
	 */
	@Override
	public void run(IAction action) {
		
		MessageDialog.openInformation(window.getShell(), "Innotree RAP Demo", "Hello, Eclipse RAP World!\n\nThis is a sample action");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		
		this.window = window;
	}

}
