import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

    public static Robot robot;
    public static int keyCodeClick;
    public static int keyCode1;
    public static int keyCode2;
    public static int duration1;
    public static int duration2;
    private static Timer timer;

    public static void main(String[] args) {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Press Key");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JLabel label1 = new JLabel("Click Code:");

        JTextField keyCodeClickField = new JTextField();

        JLabel label2 = new JLabel("Key 1 Code:");

        JTextField keyCodeField1 = new JTextField();

        JLabel label4 = new JLabel("Key 2 Code:");

        JTextField keyCodeField2 = new JTextField();

        JLabel label3 = new JLabel("Duration (CPS):");

        JTextField durationField1 = new JTextField();

        JLabel label5 = new JLabel("Change Keys Duration (ms):");

        JTextField durationField2 = new JTextField();

        panel.add(label1);
        panel.add(keyCodeClickField);
        panel.add(label2);
        panel.add(keyCodeField1);
        panel.add(label3);
        panel.add(durationField1);
        panel.add(label4);
        panel.add(keyCodeField2);
        panel.add(label5);
        panel.add(durationField2);

        JButton startButton = new JButton("Start");

        //68
        //65

        startButton.addActionListener(e -> {

            keyCodeClick = Integer.parseInt(keyCodeClickField.getText());
            keyCode1 = Integer.parseInt(keyCodeField1.getText());
            keyCode2 = Integer.parseInt(keyCodeField2.getText());
            duration1 = Integer.parseInt(durationField1.getText());
            duration2 = Integer.parseInt(durationField2.getText());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            timer = new Timer(duration1 + duration2, new TimerListener());
            timer.start();
        });
        panel.add(startButton);

        frame.add(panel);
        frame.setVisible(true);
    }}

class TimerListener implements ActionListener {

    int round = 1;
    long roundStartTime = System.currentTimeMillis();
    @Override
    public void actionPerformed(ActionEvent e) {
         //fetch starting time

//        Main.robot.keyPress(Main.keyCode1);
//        try {
//            Thread.sleep(Main.duration1);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//        Main.robot.keyRelease(Main.keyCode1);


        switch (round) {
            case 1: {
                while((System.currentTimeMillis() - roundStartTime) > Main.duration1)
                {
                    Main.robot.keyPress(Main.keyCode1);
                    Main.robot.keyRelease(Main.keyCode1);

                }
                Main.robot.keyRelease(Main.keyCode1);

                round = 2;
            }
            case 2: {
                while((System.currentTimeMillis() - roundStartTime) > Main.duration1)
                {
                    Main.robot.keyPress(Main.keyCode2);
                    Main.robot.keyRelease(Main.keyCode2);

                }
                Main.robot.keyRelease(Main.keyCode2);
                round = 1;
            }
        }

        roundStartTime = System.currentTimeMillis();
    }
}