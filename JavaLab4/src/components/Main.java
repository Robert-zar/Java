package components;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class ComboBoxDemo extends JPanel
        implements ActionListener {
    JLabel picture;

    public ComboBoxDemo() {
        super(new BorderLayout());

        String[] carStrings = { "Mercedes", "BMW", "Audi" , "Lexus", "KIA", "Porsche", "Bentley"};

        //Create the combo box, select the item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox carList = new JComboBox(carStrings);
        carList.setSelectedIndex(0);
        carList.addActionListener(this);

        //Set up the picture.
        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);
        Label(carStrings[carList.getSelectedIndex()]);
        picture.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        //The preferred size is hard-coded to be the width of the
        //widest image and the height of the tallest image + the border.
        //A real program would compute this.
        picture.setPreferredSize(new Dimension(780, 500));

        //Lay out the demo.
        add(carList, BorderLayout.PAGE_START);
        add(picture, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(100,100,100,100));

    }

    /** Listens to the combo box. */
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String carName = (String)cb.getSelectedItem();
        Label(carName);
    }

    protected void Label(String name) {
        ImageIcon icon = createImageIcon("images/" + name + ".jpg");
        picture.setIcon(icon);

        if (icon != null && name == "Mercedes") {
            picture.setText("<html> Максимальная скорость: 310 км/ч;<br>Разгон до 100 км/ч: 3.8 сек;<br>Мощность: 522 л.с.;<br>Крутящий момент: 650 н.м. (при 1750 об/мин.);<br>Объём двигателя: 3982 см³;<br>Масса: 1570 кг</html>");

        } else if (icon != null && name == "BMW") {
            picture.setText("<html> Тип двигателя: бензин;<br>Объем, л: 4,4;<br>Мощность, л.с. 625;<br>Крутящий момент, Нм: 750;<br>Тип коробки передач: автомат;<br>Число передач: 8;<br>Привод: полный;<br>Разгон 0-100 км/ч, с: 3,3;<br>Макс скорость, км/ч: 250</html>");

        } else if (icon != null && name == "Audi") {
            picture.setText("<html> Предельная мощность, л. с (кВт) 600 (441);<br>Крутящий момент, Н. м 800;<br>Разгон до 100 км/ч, с 3,6;<br>Вес автомобиля, кг 2140</html>");

        }
        else if (icon != null && name == "Lexus") {
            picture.setText("<html> Тип двигателя: бензин;<br>Объем, л 3,5 ;<br>Мощность, л.с. 421;<br>Крутящий момент, Нм 600;<br>Тип коробки передач: автомат;<br>Разгон 0-100 км/ч, с 4,9;<br>Макс скорость, км/ч 250</html>");

        }

        else if (icon != null && name == "KIA") {
            picture.setText("<html> Двигатель: бензиновый (2497 см³);<br>Мощность 194 л. с.;<br>Крутящий момент двигателя 246 Н·м;<br>Коробка передач: автоматическая (8 ступеней);<br>Привод: передний;<br>Разгон до сотни 8.6 секунды;<br>Максимальная скорость: 210 км/ч;<br>Расход топлива (л/100 км) город / трасса / смешанный: 10.1 / 5.4 / 7.1</html>");

        }

        else if (icon != null && name == "Porsche") {
            picture.setText("<html> Двигатель: бензиновый (2894 см³);<br>Мощность 450 л. с.;<br>Крутящий момент двигателя 550 Н·м;<br>Коробка передач: роботизированная (8 ступеней);<br>Привод: полный;<br>Разгон до сотни 4.3 секунды;<br>Максимальная скорость 295 км/ч;<br>Расход топлива (л/100 км) город / трасса / смешанный: 13 / 8 / 9.8</html>");

        }

        else if (icon != null && name == "Bentley") {
            picture.setText("<html> Тип двигателя: бензин;<br>Расположение двигателя: переднее, продольное;<br>Объем двигателя, см³ 5950;<br>Тип наддува: турбонаддув;<br>Максимальная мощность, л.с./кВт при об/мин 659 / 485 при 5000-6000;<br>Максимальный крутящий момент, Н*м при об/мин 900 при 1500-5000</html>");

        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ComboBoxDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ComboBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ComboBoxDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}