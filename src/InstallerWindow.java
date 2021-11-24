import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;

public class InstallerWindow {

    static JProgressBar prog;
    static JLabel status;
    static JFrame frame;

    public InstallerWindow() {
        frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(1,1));

        status = new JLabel("Finding Homebrew...");
        prog = new JProgressBar();
        prog.setValue(0);
        prog.setStringPainted(false);

        panel.add(status);
        panel.add(prog);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Download Video");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void updateProg(int val) {
        prog.setValue(val);
    }

    public static void updateStatus(String val) {
        status.setText(val);
    }

    public static void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }


}
