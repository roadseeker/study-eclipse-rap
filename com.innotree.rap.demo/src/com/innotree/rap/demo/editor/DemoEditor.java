package com.innotree.rap.demo.editor;

import java.security.MessageDigest;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class DemoEditor extends EditorPart{
	
	public static final String ID = "com.innotree.rap.demo.editor";
	
	private Text textEditor;
	private boolean dirty;

	
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		setPartName(input.getName());
	}
	
	
	@Override
	public void createPartControl(Composite parent) {
		
		parent.setLayout(new GridLayout(1, false));
		
		// 제목 라벨
		Label label = new Label (parent, SWT.NONE);
		label.setText("Editor: " + getEditorInput().getName());
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false ));
		
		//텍스트 편집영역
		textEditor = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		textEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		//초기내용 설정
		if(getEditorInput() instanceof DemoEditorInput) {
			DemoEditorInput input = (DemoEditorInput) getEditorInput();
			textEditor.setText(input.getContent());
		}
		
		//수정 리스너 - dirty 상태관리
		textEditor.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent event) {
				
				setDirty(true);
			}	
		});
	}
	
	@Override
	public void setFocus() {
		
		textEditor.setFocus();
	}
	
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		
		//저장로직
		if(getEditorInput() instanceof DemoEditorInput) {
			DemoEditorInput input = (DemoEditorInput) getEditorInput();
			input.setContent(textEditor.getText());
		}
		
		setDirty(false);
		
		MessageDialog.openInformation(getSite().getShell(), "Save", "Documnet '" + getEditorInput().getName() + "' saved successfully!");
		
	}

	@Override
	public void doSaveAs() {

		// Save As 구현 (필요시)
	}


	@Override
	public boolean isDirty() {
		return dirty;
	}

	
	private void setDirty(boolean value) {
		if(dirty != value) {
			dirty = value;
			firePropertyChange(PROP_DIRTY);
		}
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

}
