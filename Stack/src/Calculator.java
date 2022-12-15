
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Calculator extends JFrame implements ActionListener {
    static JFrame f;
    static JTextField tField;
    static Calc stackCalc;
    boolean needToClear = false;
    String input, result = "";

    public Calculator() {
        stackCalc = new Calc();
        int gridy = 0;

        f = new JFrame("Калькулятор");
        JPanel panel = new JPanel(new GridBagLayout());

        tField = new JTextField(10);
        tField.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5, 5, 5, 5)));
        panel.add(tField, createGBC(0, 16));

        String[] buttons = { "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "*",
                "0", "(", ")", "/",
                ".", "e", "pi", "^", "<", "C" };
        for (int i = 0; i < buttons.length; i++) {
            panel.add(createBtn(buttons[i]), createGBC((i % 4 == 0) ? ++gridy : gridy, 4));
        }
        panel.add(createBtn("="), createGBC(gridy, 8));

        f.add(panel);
        f.setSize(250, 325);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.charAt(0) == 'C') {
            input = "";
            tField.setText(input);
            needToClear = false;
        } else if (s.charAt(0) == '<') {
            input = needToClear ? "" : tField.getText().substring(0, tField.getText().length() - 1);
            needToClear = false;
            tField.setText(input);
        } else if (s.charAt(0) == '=') {
            input = tField.getText();
            try {
                double res = stackCalc.compile(input.toCharArray());
                if (res % 1 == 0)
                    tField.setText(input + "=" + Integer.toString((int) res));
                else
                    tField.setText(input + "=" + Double.toString(res));
            } catch (Exception ex) {
                tField.setText(ex.getMessage());
            }
            needToClear = true;
        } else {
            input = needToClear ? "" : tField.getText();
            tField.setText(input + s.charAt(0));
            needToClear = false;
        }
    }

    private GridBagConstraints createGBC(int gridy, int gridwidth) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        return c;
    }

    private JButton createBtn(String text) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.BLACK);
        btn.setBackground(Color.WHITE);
        btn.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5, 15, 5, 15)));
        btn.addActionListener(this);
        return btn;
    }

    public static void main(String args[]) {
        new Calculator();
    }
}
