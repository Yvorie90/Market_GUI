package view.components;

import javax.swing.*;

import static view.GUI.BACKGROUND_COLOR;
import static view.GUI.FONT_COLOR;

public class MyJButton extends JButton {

    public MyJButton() {
        super();
        settings();
    }

    public MyJButton(Icon icon) {
        super(icon);
        settings();
    }

    public MyJButton(String text) {
        super(text);
        settings();
    }

    public MyJButton(Action a) {
        super(a);
        settings();
    }

    public MyJButton(String text, Icon icon) {
        super(text, icon);
        settings();
    }

    private void settings(){
        setBackground(BACKGROUND_COLOR);
        setForeground(FONT_COLOR);
    }

}
