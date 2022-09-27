//Выволит окно с названием "Моя программа"

import java.awt.*;
import java.awt.event.*;
class SimpleFrame extends Frame{
    SimpleFrame(String s){
        super(s);
        setSize(400, 150);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                dispose();
                System.exit(0);

        }
        });
    }


    public static void main(String[] args){
        new SimpleFrame(" Моя программа");
}
}
