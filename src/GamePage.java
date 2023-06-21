import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GamePage extends JFrame implements ActionListener{
    Font bigFont = new Font("Arial", Font.BOLD, 30);
    Font smallFont = new Font("Arial", Font.TRUETYPE_FONT, 20);
    JLabel title = new JLabel();
    JLabel blurb = new JLabel();
    JButton pressMe = new JButton("Next");
    final int WIDTH = 250;
    final int HEIGHT = 250;
    public boolean isActive = true;
    public void setBlurb(String b) {
        blurb.setText(b);
        isActive = true;
    }
    public GamePage(String output) {
        setSize(WIDTH, HEIGHT);
        setLayout(new FlowLayout());
        setFont(bigFont);
        add(title);
        setFont(smallFont);
        add(blurb);
        blurb.setText(output);
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
