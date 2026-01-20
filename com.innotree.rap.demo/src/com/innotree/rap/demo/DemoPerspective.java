package com.innotree.rap.demo;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class DemoPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(true);
  
        // 1. 좌측: Tree View (20%)
        IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.2f, editorArea);
        left.addView("com.innotree.rap.demo.views.tree");

        // 2. 우측: Form View
        // 남은 80%에서 Form이 20%가 되려면: 20% / 80% = 0.25
        // RIGHT는 "오른쪽에 새 폴더 생성"이므로 ratio = 0.25 (Form이 25% 차지)
        IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, 0.4f, editorArea);
        right.addView("com.innotree.rap.demo.views.form");

        // 3. 하단: Table View + Selection View
        // 남은 60%에서 하단이 40%가 되도록: 0.4f
        IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.5f, editorArea);
        bottom.addView("com.innotree.rap.demo.views.table");
        bottom.addView("com.innotree.rap.demo.views.selection");
    }
}

