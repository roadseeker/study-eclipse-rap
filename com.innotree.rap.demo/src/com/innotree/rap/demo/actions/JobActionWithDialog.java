package com.innotree.rap.demo.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * JobActionWithDialog - 프로그레스 다이알로그와 함께 Job 실행
 * 
 * job.setUser(true)를 설정하면 진행 다이알로그가 표시됩니다. 
 * 사용자가 작업 진행 상황을 직접 볼 수 있고, 취소할 수 있습니다. 
 */
public class JobActionWithDialog implements IWorkbenchWindowActionDelegate {
	
	private static final int TASK_AMOUNT = 100;

	@Override
	public void run(IAction action) {
		
		Job job = new Job("Long Running Task") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Processing Data...", TASK_AMOUNT);
				
				for (int i =0; i<TASK_AMOUNT; i++) {
					if(monitor.isCanceled()) {
						monitor.done();
						return Status.CANCEL_STATUS;
					}
					
					monitor.subTask("Completed: " + i + "%");
					monitor.worked(1);
					
					try {
						Thread.sleep(100);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				monitor.done();
				return Status.OK_STATUS;
				
			}
			
		};
		
		job.setName(job.getName()+ " #" + job.hashCode());
		job.setUser(true); // 프로그레스 다이알로그 표시
		job.schedule();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		
	}

}
