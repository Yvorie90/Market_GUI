package view.components;

import javax.swing.*;

import static view.GUI.BACKGROUND_COLOR;
import static view.GUI.FONT_COLOR;

public class MyJSpinner extends JSpinner {

    public MyJSpinner(SpinnerModel model) {
        super(model);
        settings();
    }

    public MyJSpinner() {
        super();
        settings();
    }

    private void settings(){
        setBackground(BACKGROUND_COLOR);
        setForeground(FONT_COLOR);
    }

}
