import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;

public class InputWH {
    public int width;
    public int height;
    Draw draw = new Draw();

    InputWH() {
        showInput();
    }

    /**
     * @param args none
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(InputWH::new);
    }

    private void showInput() {
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
        JPanel p = new JPanel(new BorderLayout(5, 5));
        JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
        JPanel labels1 = new JPanel(new FlowLayout());
        labels.add(new JLabel("Width", SwingConstants.RIGHT));
        labels.add(new JLabel("Height", SwingConstants.RIGHT));
        labels1.add(new JLabel("Minimum Width:900, Height: 800"));
        p.add(labels, BorderLayout.WEST);
        p.add(labels1, BorderLayout.SOUTH);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField widthField = new JTextField();
        widthField.setText("900");
        controls.add(widthField);
        JTextField heightField = new JTextField();
        heightField.addAncestorListener(new RequestFocusListener(false));
        heightField.setText("800");
        controls.add(heightField);
        p.add(controls, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(null, p, "Enter Canvas Width and Height",
                JOptionPane.QUESTION_MESSAGE);
        try {
            width = Integer.parseInt(widthField.getText());
            height = Integer.parseInt(heightField.getText());
            if (width < 640 || height < 480) {
                JOptionPane.showMessageDialog(null, p,
                        "W:640,H:480 Minimum required", JOptionPane.ERROR_MESSAGE);
            }
            draw.setWH(width, height);
            draw.openPaint();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, p,
                    "Please enter valid number!", JOptionPane.ERROR_MESSAGE);
        }
    }

}

class RequestFocusListener implements AncestorListener {
    private final boolean removeListener;

    public RequestFocusListener() {
        this(true);
    }


    public RequestFocusListener(boolean removeListener) {
        this.removeListener = removeListener;
    }

    @Override
    public void ancestorAdded(AncestorEvent e) {
        JComponent component = e.getComponent();
        component.requestFocusInWindow();

        if (removeListener)
            component.removeAncestorListener(this);
    }

    @Override
    public void ancestorMoved(AncestorEvent e) {
    }

    @Override
    public void ancestorRemoved(AncestorEvent e) {
    }
}
