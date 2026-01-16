package com.innotree.rap.demo;

import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;


public class DemoWorkbench implements EntryPoint {
    @Override
    public int createUI() {
        Display display = PlatformUI.createDisplay();
        int result = PlatformUI.createAndRunWorkbench(display, new DemoWorkbenchAdvisor());
        display.dispose();
        return result;
    }
}
