import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class Draw {

    private final Icon save = new ImageIcon(Objects.requireNonNull(getClass().getResource("resources/save.png")));
    private final Icon undo = new ImageIcon(Objects.requireNonNull(getClass().getResource("resources/undo.png")));
    private final Icon redo = new ImageIcon(Objects.requireNonNull(getClass().getResource("resources/redo.png")));
    private final Icon pencilIcon = new ImageIcon(Objects.requireNonNull(getClass()
            .getResource("resources/pencil.png")));
    private final Icon rect = new ImageIcon(Objects.requireNonNull(getClass().getResource("resources/rect.png")));
    Canvas canvas;
    Color color = Color.WHITE;
    JButton clearButton, blackButton, blueButton, greenButton, redButton,
            colorPicker, magentaButton, grayButton, orangeButton, yellowButton,
            pinkButton, cyanButton, lightGrayButton, saveButton, loadButton,
            saveAsButton, rectangle, pencil, undoButton, redoButton;
    private File file;
    private int saveCounter = 0;
    private JLabel filenameBar, thicknessStat;
    ActionListener listener = new ActionListener() {

        public void actionPerformed(ActionEvent event) {
            JFileChooser fileChooser;
            if (event.getSource() == clearButton) {
                canvas.clear();
            } else if (event.getSource() == blackButton) {
                canvas.black();
            } else if (event.getSource() == blueButton) {
                canvas.blue();
            } else if (event.getSource() == greenButton) {
                canvas.green();
            } else if (event.getSource() == redButton) {
                canvas.red();
            } else if (event.getSource() == magentaButton) {
                canvas.magenta();
            } else if (event.getSource() == grayButton) {
                canvas.gray();
            } else if (event.getSource() == orangeButton) {
                canvas.orange();
            } else if (event.getSource() == yellowButton) {
                canvas.yellow();
            } else if (event.getSource() == pinkButton) {
                canvas.pink();
            } else if (event.getSource() == cyanButton) {
                canvas.cyan();
            } else if (event.getSource() == lightGrayButton) {
                canvas.lightGray();
            } else if (event.getSource() == undoButton) {
                canvas.undo();
            } else if (event.getSource() == redoButton) {
                canvas.redo();
            } else if (event.getSource() == rectangle) {
                canvas.rect();
            } else if (event.getSource() == pencil) {
                canvas.pencil();
            } else if (event.getSource() == saveButton) {
                if (saveCounter == 0) {
                    fileChooser = new JFileChooser();
                    if (fileChooser.showSaveDialog(saveButton) == JFileChooser.APPROVE_OPTION) {
                        file = fileChooser.getSelectedFile();
                        saveCounter = 1;
                        filenameBar.setText(file.toString());
                        canvas.save(file);
                    }
                } else {
                    filenameBar.setText(file.toString());
                    canvas.save(file);
                }
            } else if (event.getSource() == saveAsButton) {
                saveCounter = 1;
                fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(saveAsButton) == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    filenameBar.setText(file.toString());
                    canvas.save(file);
                }
            } else if (event.getSource() == loadButton) {
                fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(loadButton) == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    filenameBar.setText(file.toString());
                    canvas.load(file);
                }
            } else if (event.getSource() == colorPicker) {
                color = JColorChooser.showDialog(null, "Pick your color!",
                        color);
                if (color == null)
                    color = (Color.WHITE);
                canvas.picker(color);
            }
        }
    };
    private JSlider thicknessSlider;
    ChangeListener thick = new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            thicknessStat.setText(String.format("%s",
                    thicknessSlider.getValue()));
            canvas.setThickness(thicknessSlider.getValue());
        }
    };
    private int width, height;

    public void setWH(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void openPaint() {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException
                         | IllegalAccessException
                         | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        JFrame frame = new JFrame("Paint (" + width + "X" + height + ")");
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        canvas = new Canvas();

        container.add(canvas, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        Box box = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();

        panel1.setLayout(new FlowLayout());

        pencil = new JButton(pencilIcon);
        pencil.setPreferredSize(new Dimension(40, 40));
        pencil.addActionListener(listener);
        rectangle = new JButton(rect);
        rectangle.setPreferredSize(new Dimension(40, 40));
        rectangle.addActionListener(listener);
        thicknessSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 1);
        thicknessSlider.setMajorTickSpacing(25);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPreferredSize(new Dimension(40, 40));
        thicknessSlider.addChangeListener(thick);
        undoButton = new JButton(undo);
        undoButton.setPreferredSize(new Dimension(20, 20));
        undoButton.addActionListener(listener);
        redoButton = new JButton(redo);
        redoButton.setPreferredSize(new Dimension(20, 20));
        redoButton.addActionListener(listener);
        blackButton = new JButton();
        blackButton.setBackground(Color.BLACK);
        blackButton.setPreferredSize(new Dimension(40, 40));
        blackButton.addActionListener(listener);
        blueButton = new JButton();
        blueButton.setBackground(Color.BLUE);
        blueButton.setPreferredSize(new Dimension(40, 40));
        blueButton.addActionListener(listener);
        greenButton = new JButton();
        greenButton.setBackground(Color.GREEN);
        greenButton.setPreferredSize(new Dimension(40, 40));
        greenButton.addActionListener(listener);
        redButton = new JButton();
        redButton.setBackground(Color.RED);
        redButton.setPreferredSize(new Dimension(40, 40));
        redButton.addActionListener(listener);
        magentaButton = new JButton();
        magentaButton.setBackground(Color.MAGENTA);
        magentaButton.setPreferredSize(new Dimension(40, 40));
        magentaButton.addActionListener(listener);
        grayButton = new JButton();
        grayButton.setBackground(Color.GRAY);
        grayButton.setPreferredSize(new Dimension(40, 40));
        grayButton.addActionListener(listener);
        orangeButton = new JButton();
        orangeButton.setBackground(Color.ORANGE);
        orangeButton.setPreferredSize(new Dimension(40, 40));
        orangeButton.addActionListener(listener);
        yellowButton = new JButton();
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.setPreferredSize(new Dimension(40, 40));
        yellowButton.addActionListener(listener);
        pinkButton = new JButton();
        pinkButton.setBackground(Color.PINK);
        pinkButton.setPreferredSize(new Dimension(40, 40));
        pinkButton.addActionListener(listener);
        cyanButton = new JButton();
        cyanButton.setBackground(Color.CYAN);
        cyanButton.setPreferredSize(new Dimension(40, 40));
        cyanButton.addActionListener(listener);
        lightGrayButton = new JButton();
        lightGrayButton.setBackground(Color.LIGHT_GRAY);
        lightGrayButton.setPreferredSize(new Dimension(40, 40));
        lightGrayButton.addActionListener(listener);
        saveButton = new JButton(save);
        saveButton.addActionListener(listener);
        saveAsButton = new JButton("Save As");
        saveAsButton.addActionListener(listener);
        loadButton = new JButton("Load");
        loadButton.addActionListener(listener);
        colorPicker = new JButton("Color Picker");
        colorPicker.addActionListener(listener);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(listener);

        filenameBar = new JLabel("No file");
        thicknessStat = new JLabel("1");

        box.add(Box.createVerticalStrut(40));
        box1.add(thicknessSlider, BorderLayout.NORTH);
        box1.add(thicknessStat, BorderLayout.NORTH);
        box.add(box1, BorderLayout.NORTH);
        panel1.add(filenameBar, BorderLayout.SOUTH);
        box.add(Box.createVerticalStrut(20));
        box.add(undoButton, BorderLayout.NORTH);
        box.add(Box.createVerticalStrut(5));
        box.add(redoButton, BorderLayout.NORTH);
		/*box.add(Box.createVerticalStrut(5));
		box.add(pencil, BorderLayout.NORTH);
		box.add(Box.createVerticalStrut(5));
		box.add(rectangle, BorderLayout.NORTH);*/

        panel.add(greenButton);
        panel.add(blueButton);
        panel.add(blackButton);
        panel.add(redButton);
        panel.add(magentaButton);
        panel.add(grayButton);
        panel.add(orangeButton);
        panel.add(yellowButton);
        panel.add(pinkButton);
        panel.add(cyanButton);
        panel.add(lightGrayButton);
        panel.add(saveButton);
        panel.add(saveAsButton);
        panel.add(loadButton);
        panel.add(colorPicker);
        panel.add(clearButton);

        container.add(panel, BorderLayout.NORTH);
        container.add(panel1, BorderLayout.SOUTH);
        container.add(box, BorderLayout.WEST);

        frame.setVisible(true);

        frame.setSize(width + 79, height + 11);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
