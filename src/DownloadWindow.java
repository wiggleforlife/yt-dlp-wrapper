import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class DownloadWindow {

    static JFrame frame;
    static JTextArea outputField;

    public DownloadWindow() {
        frame = new JFrame();

        GridLayout lay = new GridLayout();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(450, 150));
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(lay);

        outputField = new JTextArea(5,5);
        outputField.setEditable(false);

        panel.add(outputField);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Download Video");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void addToField(String out) {
        outputField.setText(out + outputField.getText());
    }

    public static void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

}
