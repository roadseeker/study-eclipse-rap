package com.innotree.rap.demo.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class DemoSelectionView extends ViewPart {
	
	public static final String ID = "com.innotree.rap.demo.views.selection";
	
	private List list;
	private ISelectionListener selectionListener;
	private String lastEntry = "";  // 이전 항목 저장
	
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		
		//제목 라벨
		Label label = new Label(parent, SWT.NONE);
		label.setText("Selection Log - 다른 View에서 선택한 항목이 표시됩니다");
		
		//선택 로그 리스트
		list = new List(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		// Selection 리스너 등록
		createSelectionListener();
		
	}

	@Override
	public void setFocus() {
		list.setFocus();
	}
	
	private void createSelectionListener() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        ISelectionService selectionService = window.getSelectionService();

        selectionListener = new ISelectionListener() {
            @Override
            public void selectionChanged(IWorkbenchPart part, ISelection selection) {
                if (list.isDisposed()) {
                    return;
                }

                String entry = "[" + part.getTitle() + "] ";

                if (selection instanceof IStructuredSelection) {
                    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                    Object firstElement = structuredSelection.getFirstElement();

                    if (firstElement == null) {
                        entry += "(empty)";
                    } else if (firstElement instanceof String[]) {
                        // TableView에서 선택한 경우
                        String[] row = (String[]) firstElement;
                        entry += "Table Row: " + row[0] + ", " + row[1] + " (count: " + structuredSelection.size() + ")";
                    } else {
                        // TreeView 등 다른 View에서 선택한 경우
                        entry += firstElement.toString();
                    }
                } else {
                    entry += selection.toString();
                }

             // 중복 체크
                if (!entry.equals(lastEntry)) {
                    list.add(entry, 0);
                    list.setSelection(0);
                    lastEntry = entry;
                }
            }
        };
        // 변경: addSelectionListener → addPostSelectionListener
        selectionService.addPostSelectionListener(selectionListener);
    }
	
	@Override
	public void dispose() {
		//리스너 해제
		if (selectionListener != null) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			
			if(window != null) {
				ISelectionService selectionService = window.getSelectionService();
				// 변경: removeSelectionListener → removePostSelectionListener
				selectionService.removePostSelectionListener(selectionListener);
			}
		}
		super.dispose();
	}
	
	

}
