import processing.core.PApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class UserInterface {

    private final static String[] OPTIONS = new String[] {"--present", "Canvas"};
    private JFrame frame;
    private JPanel panel;
    private JButton startButton;
    private JCheckBox bounceSetting, darkMode, AI;
    private int rows = 2, cols = 2;
    private int width = 400, height = 200;

    public UserInterface(){
        frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowClosing(WindowEvent e) {
                endProgram();
            }

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(rows, cols));

        bounceSetting = new JCheckBox("Bounce Setting");
        panel.add(bounceSetting);

        darkMode = new JCheckBox("Dark Mode Setting");
        panel.add(darkMode);

        AI = new JCheckBox("AI Setting");
        panel.add(AI);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        panel.add(startButton);

        frame.add(panel);
        frame.setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setVisible(true);
    }

    private void start(){
        Canvas.setAltBounce(bounceSetting.isSelected());
        Canvas.setDarkMode(darkMode.isSelected());
        Canvas.setAI(AI.isSelected());
        PApplet.main(OPTIONS);
    }

    private void endProgram(){
        frame.setVisible(false);
        frame.dispose();
        System.exit(0);
    }

}
