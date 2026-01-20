package com.innotree.rap.demo.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class JobAction implements IWorkbenchWindowActionDelegate{
	
	private static final int TASK_AMOUNT = 100;

	@Override
	public void run(IAction action) {
		Job job = new Job("Background Job") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Processing...", TASK_AMOUNT);
				
				for (int i= 0; i < TASK_AMOUNT; i++) {
					//취소요청 확인
					if(monitor.isCanceled()) {
						monitor.done();
						return Status.CANCEL_STATUS;
					}
					
					//진행 상황 업데이트
					monitor.subTask("Progress: " + i + "%");
					monitor.worked(1);
					
					try {
						Thread.sleep(100);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				monitor.done();
				return Status.OK_STATUS;
				
			}
			
		};
		job.setName(job.getName() + " #" + job.hashCode());
		job.schedule();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		
	}

}
