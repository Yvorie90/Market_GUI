package view.components;

import javax.swing.*;

import static view.GUI.FONT_COLOR;

public class MyJLabel extends JLabel {

    public MyJLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        settings();
    }

    public MyJLabel(String text) {
        super(text);
        settings();
    }


    public MyJLabel(Icon image) {
        super(image);
        settings();
    }

    public MyJLabel() {
        super();
        settings();
    }

    private void settings(){
        setForeground(FONT_COLOR);
        setHorizontalAlignment(JLabel.CENTER);
    }


}
