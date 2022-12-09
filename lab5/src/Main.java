import java.awt.event.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Main extends JFrame implements ActionListener {
    static JTextField tField;
    static JPanel actionPanel;
    static DrawPanel drawPanel;
    static Tree theTree;
    private static final int width = 600;
    private static final int height = 400;

    String input, result = "";

    public Main() {
        JFrame f = new JFrame("Tree");
        actionPanel = new JPanel(new GridBagLayout());
        tField = new JTextField(16);
        theTree = new Tree();
        drawPanel = new DrawPanel(theTree, width, height - 40);

        tField.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5, 5, 5, 5)));
        String[] buttons = { "Вставить", "Найти", "Удалить" };
        for (String btnName : buttons)
            actionPanel.add(createBtn(btnName), createGBC(0, 4));
        actionPanel.add(tField, createGBC(0, 4));

        f.add(actionPanel);
        f.add(drawPanel);
        f.setSize(width, height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
        actionPanel.setBounds(0, 0, width, 40);
        drawPanel.setBounds(0, 40, width, height - 40);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (!tField.getText().matches("[0-9]+")) {
            tField.setText("IllegalArgumentException");
            return;
        }
        switch (e.getActionCommand().charAt(0)) {
            case 'В':
                theTree.insert(Integer.parseInt(tField.getText()));
                break;
            case 'Н':
                drawPanel.setSearchNode(theTree.find(Integer.parseInt(tField.getText())));
                break;
            case 'У':
                theTree.delete(Integer.parseInt(tField.getText()));
                break;
        }
        tField.setText("");
        theTree.displayTree();
        drawPanel.update(drawPanel.getGraphics());
        actionPanel.update(actionPanel.getGraphics());
    }

    private GridBagConstraints createGBC(int gridy, int gridwidth) {
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1.0;
        c.gridwidth = gridwidth;
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

    public static void main(String[] args) {
        new Main();
    }
}
