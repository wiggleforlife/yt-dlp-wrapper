import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class URLWindow {

    static JFrame frame;

    public URLWindow() {
        frame = new JFrame();

        SpringLayout lay = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(450, 150));
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(lay);

        System.out.println(panel.getMinimumSize());

        JLabel label = new JLabel("Video URL:");
        JTextField urlField = new JTextField("", 25);
        JButton confirm = new JButton("Confirm");

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DownloadVideo.download(urlField.getText());
            }
        });

        panel.add(label);
        panel.add(urlField);
        panel.add(confirm);

        lay.putConstraint(SpringLayout.WEST, label, 6, SpringLayout.WEST, panel);
        lay.putConstraint(SpringLayout.NORTH, label, 6, SpringLayout.NORTH, panel);
        lay.putConstraint(SpringLayout.WEST, urlField, 6, SpringLayout.EAST, label);
        lay.putConstraint(SpringLayout.NORTH, urlField, 6, SpringLayout.NORTH, panel);
        lay.putConstraint(SpringLayout.EAST, confirm, 6, SpringLayout.EAST, panel);
        lay.putConstraint(SpringLayout.SOUTH, confirm, 6, SpringLayout.SOUTH, panel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Download Video");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

}
