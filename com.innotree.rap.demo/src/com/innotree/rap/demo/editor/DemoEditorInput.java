package com.innotree.rap.demo.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class DemoEditorInput implements IEditorInput{
	
	private String name;
	private String content;
	
	public DemoEditorInput(String name) {
		this.name = name;
		this.content = "Enter your content here....";
	}
	
	public DemoEditorInput(String name, String content) {
		this.name = name;
		this.content = content;
	}
	
	

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "Demo Editor: " + name;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		DemoEditorInput other = (DemoEditorInput) obj;
		return name != null && name.equals(other.name);
	}
	
	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

}
