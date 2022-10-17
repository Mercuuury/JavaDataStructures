import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    static JFrame f;
    static JTextField tField;
    static Calc stackCalc;
    boolean needToClear = false;
    String input, result;

    public Calculator() {
        stackCalc = new Calc();
        input = "";

        f = new JFrame("Калькулятор");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tField = new JTextField(16);
        tField.setEditable(true);

        JPanel p = new JPanel();
        p.add(tField);

        String[] buttons = { "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "*",
                "0", "(", ")", "/",
                "<", "C", "=", "^" };

        for (String btn : buttons) {
            JButton b = new JButton(btn);
            b.addActionListener(this);
            p.add(b);
        }

        f.add(p);
        f.setSize(205, 220);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.charAt(0) == 'C' || needToClear) {
            input = "";
            tField.setText(input);
            needToClear = false;
        } else if (s.charAt(0) == '<') {
            input = input.substring(0, input.length() - 1);
            tField.setText(input);
        } else if (s.charAt(0) == '=') {
            input = tField.getText();
            try {
                tField.setText(input + "=" + Integer.toString(stackCalc.compile(input.toCharArray())));
            } catch (Exception ex) {
                tField.setText(ex.getMessage());
            }
            needToClear = true;
        } else {
            tField.setText(tField.getText() + s);
        }
    }

    public static void main(String args[]) {
        new Calculator();
    }
}
