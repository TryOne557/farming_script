import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

    public static Robot robot;
    public static int keyCode1;
    public static int keyCode2;
    public static int keyCode3;
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


        JLabel label1 = new JLabel("Key 1 Code:");

        JTextField keyCodeField1 = new JTextField();

        JLabel label2 = new JLabel("Key 2 Code:");

        JTextField keyCodeField2 = new JTextField();

        JLabel label3 = new JLabel("Duration (CPS) 1 (ms):");

        JTextField durationField1 = new JTextField();

        JLabel label4 = new JLabel("Key 2 Code:");

        JTextField keyCodeField3 = new JTextField();

        JLabel label5 = new JLabel("Duration 2 (ms):");

        JTextField durationField2 = new JTextField();

        panel.add(label1);
        panel.add(keyCodeField1);
        panel.add(label2);
        panel.add(keyCodeField2);
        panel.add(label3);
        panel.add(durationField1);
        panel.add(label4);
        panel.add(keyCodeField3);
        panel.add(label5);
        panel.add(durationField2);

        JButton startButton = new JButton("Start");

        //68
        //65

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                keyCode1 = Integer.parseInt(keyCodeField1.getText());
                keyCode2 = Integer.parseInt(keyCodeField2.getText());
                keyCode3 = Integer.parseInt(keyCodeField3.getText());
                duration1 = Integer.parseInt(durationField1.getText());
                duration2 = Integer.parseInt(durationField2.getText());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                timer = new Timer(duration1 + duration2, new TimerListener());
                timer.start();
            }
        });
        panel.add(startButton);

        frame.add(panel);
        frame.setVisible(true);
    }}

class TimerListener implements ActionListener {

    int durchgang = 1;
    long startTime = System.currentTimeMillis();
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


        if(startTime == 0 && durchgang == 1){
            while((System.currentTimeMillis() - startTime) < Main.duration1)
            {
                Main.robot.keyPress(Main.keyCode2);
                Main.robot.keyRelease(Main.keyCode2);

            }
            Main.robot.keyRelease(Main.keyCode2);

            durchgang = 2;
        }
        if (startTime == 0 && durchgang == 2){
            while((System.currentTimeMillis() - startTime) < Main.duration1)
            {
                Main.robot.keyPress(Main.keyCode3);
                Main.robot.keyRelease(Main.keyCode3);

            }
            Main.robot.keyRelease(Main.keyCode3);
            durchgang = 1;
        }
        startTime = 0;



    }
}