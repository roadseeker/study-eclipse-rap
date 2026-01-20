package com.innotree.rap.demo.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class DemoTreeView extends ViewPart {
	
	public static final String ID = "com.innotree.rap.demo.views.tree";
	
	private TreeViewer viewer;
	
	
	// 트리 노드 클래스
	public static class TreeNode {
		private String name;
		private String url; //추가
		private TreeNode parent;
		private List<TreeNode> children = new ArrayList<>();
		
		public TreeNode (String name) {
			this.name = name;
		}
		
		// URL에 있는 생성자 추가
		public TreeNode (String name, String url) {
			this.name = name;
			this.url = url;
		}
		
		public String getName() {
			return name;
		}
		
		public void addChild(TreeNode child) {
			children.add(child);
			child.parent = this;
		}
		
		public TreeNode[] getChildren() {
			return children.toArray(new TreeNode[0]);
		}
		
		public boolean hasChildren() {
			return !children.isEmpty();
		}
		
		public TreeNode getParent() {
			return parent;
		}
		
		@Override
		public String toString() {
			return name;
		}
		
		// URL getter 추가
		public String getUrl() {
			return url;
		}
		
		// URL setter 추가
		public void setUrl(String url) {
			this.url = url;
		}
	}
	
	
	// ContentProvider: 트리 데이터 제공
	private class TreeContentProvider implements ITreeContentProvider {

		private static final long serialVersionUID = 1L;

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object[] getElements(Object inputElement) {
			
			return getChildren(inputElement);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if(parentElement instanceof TreeNode) {
				return ((TreeNode)parentElement).getChildren();
			}
			if(parentElement instanceof TreeNode[]) {
				return (TreeNode[]) parentElement;
			}
			return new Object[0];
		}

		@Override
		public Object getParent(Object element) {
			if(element instanceof TreeNode) {
				return ((TreeNode)element).getParent();
			}
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if(element instanceof TreeNode) {
				return ((TreeNode)element).hasChildren();
			}
			return false;
		}
		
	}
	
	//LabelProvider: 트리 아이템 표시
	private class TreeLabelProvider extends LabelProvider {

		private static final long serialVersionUID = 1L;

		@Override
		public String getText(Object element) {
			if(element instanceof TreeNode) {
				return ((TreeNode)element).getName();
			}
			return super.getText(element) ;
		}
		
		@Override
		public Image getImage(Object element) {
			
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
			
		}
	}
	

	@Override
	public void createPartControl(Composite parent) {
		
		// TreeViewer 생성(JFace)
		viewer = new TreeViewer(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		viewer.setContentProvider(new TreeContentProvider());
		viewer.setLabelProvider(new TreeLabelProvider());
		
		// 샘플 데이터 설정
		viewer.setInput(createSampleData());
		
		// 첫번째 라벨 확장
		viewer.expandToLevel(2);
		
		// SelectionProvider 등록
		getSite().setSelectionProvider(viewer);
	}

	/**
	 * 샘플 데이터
	 */
	private TreeNode[] createSampleData() {
		//Root 노드
		TreeNode root = new TreeNode("Innotree", "https://www.innotree.com");
		//자식 노드들 
		TreeNode child1 = new TreeNode("InnoQuartz", "https://innoquartz.com/");
        TreeNode child2 = new TreeNode("Wikipedia", "https://www.wikipedia.org/");
        TreeNode child3 = new TreeNode("Features");
		
        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        
        // Features 하위 노드
        child3.addChild(new TreeNode("데이터 컨설팅", "https://www.innotree.com/html/ko/services_data"));
        child3.addChild(new TreeNode("AI 빅데이터", "https://www.innotree.com/html/ko/services_ai"));
        child3.addChild(new TreeNode("커머스/유통", "https://www.innotree.com/html/ko/services_commerce"));
		
		return new TreeNode[]{ root };
	}

	@Override
	public void setFocus() {
		viewer.getTree().setFocus();
		
	}

}
