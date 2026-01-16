package com.innotree.rap.demo.views;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class DemoTableView extends ViewPart {

	public static final String ID = "com.innotree.rap.demo.views.table";
	
	private static final int ROWS = 20;
	private static final int COLUMNS = 5;
	private TableViewer viewer;
	
	//테이블 데이테 제공자
	private class ViewContentProvider implements IStructuredContentProvider {

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
			List<String[]> buffer = new ArrayList<>();
			for (int i = 0; i < ROWS; i++) {
				String[] row = new String[COLUMNS];
				for(int j=0; j < COLUMNS; j++) {
					row[j] = "Data " + i + "-" + j;
				}
				buffer.add(row);
			}
			
			return buffer.toArray();
		}
		
	}
	
	//테이블 라벨 제공자
	private class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {


		private static final long serialVersionUID = 1L;

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			String[] row = (String[]) element;
			return row[columnIndex];
		}
		
	}
	
	
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.BORDER);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//컬럼 생성
		String[] columnNames = { "ID", "Name", "Description", "Status", "Date"};
		int[] columnWidths = {50, 100, 150, 80, 100};
		
		for (int i = 0; i < COLUMNS; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(columnNames[i]);
			column.setWidth(columnWidths[i]);
		}
		viewer.setInput(this);
		
		//Selection Provider 등록 (다른 View와 연동)
		getSite().setSelectionProvider(viewer);
		
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
