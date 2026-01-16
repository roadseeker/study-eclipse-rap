package com.innotree.rap.demo;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class DemoPerspective implements IPerspectiveFactory {

	@Override
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(false);

        // 1. 좌측: Tree View (20%) - editorArea 기준
        IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.2f, editorArea);
        left.addView("com.innotree.rap.demo.views.tree");

        // 2. 우측: Form View (35%) - editorArea 기준 (left 제외한 나머지의 35%)
        IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, 0.7f, editorArea);
        right.addView("com.innotree.rap.demo.views.form");

        // 3. 중앙 상단: Table View - editorArea 기준 (left, right 제외한 나머지의 60%)
        IFolderLayout top = layout.createFolder("top", IPageLayout.TOP, 0.6f, editorArea);
        top.addView("com.innotree.rap.demo.views.table");

        // 4. 중앙 하단: Selection View - 남은 editorArea 전체
        IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.5f, editorArea);
        bottom.addView("com.innotree.rap.demo.views.selection");
    }
}

