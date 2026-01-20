package com.innotree.rap.demo.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.part.ViewPart;

/**
 * DemoBrowserView - 웹 브라우저 뷰
 * 
 * SWT Browser 위젯을 사용하여 웹페이지를 표시합니다. 
 * TreeView 선택에 반응하여 URL을 변경할 수 있습니다. 
 */
public class DemoBrowserView extends ViewPart{
	
	public static final String ID = "com.innotree.rap.demo.views.browser";
	
	private static final String DEFULT_URL = "https://innoQuartz.com";
	
	private Browser browser;
	private Text urlText;
	private ISelectionListener selectionListener;
	
	

	@Override
	public void createPartControl(Composite parent) {
		
		parent.setLayout(new GridLayout(1, false));
		
		//URL 입력 필드
		urlText = new Text(parent, SWT.BORDER | SWT.SINGLE); 
		urlText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		urlText.setText(DEFULT_URL);
		
		//Enter 키로 URL 이동
		urlText.addListener(SWT.DefaultSelection, event -> {
			String url = urlText.getText();
			if(!url.isEmpty()) {
				browser.setUrl(url);
			}
		});
		
		// Browser 위젯
		IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();
		
		browser = new Browser(parent, SWT.NONE);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		browser.setUrl(DEFULT_URL);
		
		// Selection 리스너 등록
		createSelectionListener();
	}

	/**
	 * TreeView 선택 시 URL 변경을 위한 리스너
	 */
	private void createSelectionListener() {
		selectionListener = new ISelectionListener() {
			
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				
				handleSelection(selection);
			}

			
		};
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);
	}
	
	/**
	 * 선택된 항목에서 URL을 추출하여 브라우저에 표시
	 */
	private void handleSelection(ISelection selection) {
		
		if(browser.isDisposed()) {
			return;
		}
		
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object firstElement = structuredSelection.getFirstElement();
			
			//TreeNode에 URL이 있는 경우 처리
			if(firstElement instanceof DemoTreeView.TreeNode) {
				DemoTreeView.TreeNode node = (DemoTreeView.TreeNode) firstElement;
				String url = node.getUrl();
				
				if(url != null && !url.isEmpty()) {
					browser.setUrl(url);
					urlText.setText(url);
				}
			}
		}
	}

	@Override
	public void setFocus() {
		browser.setFocus();
	}
	
	
	@Override
	public void dispose() {
		if(selectionListener != null) {
			getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
		}
		super.dispose();
	}
	
	/**
	 * 외부에서 Url 설정
	 * 
	 */

	public void setUrl(String url) {
		if(!browser.isDisposed()) {
			browser.setUrl(url);
			urlText.setText(url);
		}
	}
}
