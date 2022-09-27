import java.awt.*;
import java.awt.event.*;

class GraphTestO extends Frame {
    GraphTestO(String s) {
        super(s);
        setBounds(0, 0, 300, 300);
        setVisible(true);
    }
    public void paint(Graphics g){
        Dimension d = getSize();
        int dx = d.width / 20, dy = d.height / 20;
        int myWidth = 250, myHeight = 250;
        g.drawLine(0, 0, myWidth, myHeight);
        setBackground(Color.black);
    }
    public static void main(String[] args) {
        GraphTestO f = new GraphTestO(" Чёрный квадрат");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });
    }
}

