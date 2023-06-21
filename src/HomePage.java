import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class HomePage extends JFrame implements ActionListener{
    Font bigFont = new Font("Arial", Font.BOLD, 30);
    JLabel title = new JLabel("Welcome to Uno! Click to Start");
    JButton pressMe = new JButton("Start");
    final int WIDTH = 250;
    final int HEIGHT = 250;
    public boolean isActive = true;
    public HomePage() {
        setSize(WIDTH, HEIGHT);
        setLayout(new FlowLayout());
        setFont(bigFont);
        add(title);
        add(pressMe);
        pressMe.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == pressMe) {
            isActive = false;
        }
    }
}
