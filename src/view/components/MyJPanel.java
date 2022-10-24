package view.components;

import javax.swing.*;
import java.awt.*;

public class MyJPanel extends JPanel {

    public MyJPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        settings();
    }

    public MyJPanel(LayoutManager layout) {
        super(layout);
        settings();
    }

    public MyJPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        settings();
    }

    public MyJPanel() {
        super();
        settings();
    }
    
    private void settings(){
        setOpaque(false);
    }
    
}
