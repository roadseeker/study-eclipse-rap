package com.innotree.rap.demo.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;


/**
 * DemoChartView - 막대 그래프 차트 뷰
 * 
 * Canvas와 PaintListener를 사용하여 커스텀 차트를 그립니다.
 */
public class DemoChartView extends ViewPart{

	public static final String ID = "com.innotree.rap.views.chart";
	private Bar[] bars;
	
	@Override
	public void createPartControl(Composite parent) {
		initBars(parent.getDisplay());
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		
		Canvas canvas = new Canvas(composite, SWT.NONE);
		canvas.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent event) {
				
				drawGrid(event);
				drawBars(event);
			}
		});
		
	}

	protected void drawGrid(PaintEvent event) {
		
		Display display = event.display;
		GC gc = event.gc;
		
		Font font = new Font(display, "Arial", 10, SWT.NONE);
		gc.setFont(font);
		
		// Y축 눈금 및 가로선 
		for (int i =2; i < 9; i++) {
			gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
			gc.drawString(String.valueOf(8 - i), 10, i * 20 - 7);
			
			gc.setForeground(new Color(display, 230, 230, 230));
			gc.drawLine(20, i * 20, 160, i * 20);
			
		}
		
		gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
		
		// Y축
        gc.drawLine(20, 20, 20, 160);
        gc.drawPolygon(new int[] { 20, 10, 23, 20, 17, 20 }); // 화살표
        
     // X축
        gc.drawLine(20, 160, 160, 160);
        gc.drawPolygon(new int[] { 170, 160, 160, 157, 160, 163 }); // 화살표
	}
	
	protected void drawBars(PaintEvent event) {
		for (int i = 0; i < bars.length; i++) {
            bars[i].draw(event, 30 + i * 20);
        }
		
	}

	private void initBars(Display display) {
		
		bars = new Bar[6];
		
		String[] titles = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun"};
		int [] heights = new int[] {34, 111, 21, 45, 87, 50};
		
		Color[] colors = new Color[] {
	              new Color(display, 99, 150, 239),   // 파랑
	              new Color(display, 239, 130, 123),  // 빨강
	              new Color(display, 49, 203, 49),    // 초록
	              new Color(display, 255, 215, 0),    // 노랑
	              new Color(display, 132, 113, 255),  // 보라
	              new Color(display, 140, 239, 140)   // 연두
	          };
		for (int i = 0; i < bars.length; i ++) {
			bars[i] = new Bar(titles[i], heights[i], colors[i]);
		}
		
	}

	@Override
	public void setFocus() {
		
	}
	
	/**
	 * 막대 그래프 항목 클래스
	 */
	private class Bar {
		private final String title;
		private final int height;
		private final Color color;
		
		public Bar(String title, int height, Color color) {
			this.title = title;
			this.height = height;
			this.color = color;
		}
		
		
		public void draw(PaintEvent event, int x) {
			Display display = event.display;
			GC gc = event.gc;
			
			// 막대 채우기
			gc.setBackground(color);
			gc.fillRectangle(x, 160 - height, 15, height);
			
			// 막대 테두리
			gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
			gc.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
			gc.drawRectangle(x, 160 - height, 15, height);
			
			// 3D 효과 (오른쪽 그림자)
			gc.fillRectangle(x+13, 160 - height, 2, height);
			
			// 라벨
			Font font = new Font(display, "Arial", 8, SWT.NONE);
			gc.setFont(font);
			gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
			gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			gc.drawString(title, x + 2, 162);
			
		}
		
	}

}
