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
    private JTextField tField;
    private JPanel actionPanel;
    private DrawPanel drawPanel;
    private JToggleButton treeBtn;
    private JToggleButton rBTreeBtn;
    private Tree theTree;
    private RedBlackTree<Integer> theRBTree;
    private static final int width = 600;
    private static final int height = 600;
    private Long start;
    private Long end;

    public Main() {
        JFrame f = new JFrame("Trees");
        theTree = new Tree();
        theRBTree = new RedBlackTree<Integer>();
        actionPanel = new JPanel(new GridBagLayout());
        actionPanel.setBounds(0, 0, width, 40);
        drawPanel = new DrawPanel(theTree, theRBTree, width, height - 40);
        drawPanel.setTreeType(0);
        drawPanel.setBounds(0, 40, width, height - 40);
        tField = new JTextField(16);
        tField.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5, 5, 5, 5)));

        treeBtn = createJToggleBtn("Tree", true);
        rBTreeBtn = createJToggleBtn("RBTree", false);
        actionPanel.add(treeBtn, createGBC(0, 4));
        actionPanel.add(rBTreeBtn, createGBC(0, 4));
        String[] buttons = { "Вставить", "Найти", "Удалить" };
        for (String btnName : buttons)
            actionPanel.add(createBtn(btnName), createGBC(0, 4));
        actionPanel.add(tField, createGBC(0, 4));

        f.add(actionPanel);
        f.add(drawPanel);
        f.setSize(width + 15, height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().charAt(0)) {
            case 'T':
                drawPanel.setTreeType(0);
                treeBtn.setSelected(true);
                rBTreeBtn.setSelected(false);
                break;
            case 'R':
                drawPanel.setTreeType(1);
                treeBtn.setSelected(false);
                rBTreeBtn.setSelected(true);
                break;
            case 'В':
                if (!validateTField())
                    return;
                if (treeBtn.isSelected()) {
                    start = System.nanoTime();
                    theTree.add(Integer.parseInt(tField.getText()));
                    end = System.nanoTime();
                } else {
                    start = System.nanoTime();
                    theRBTree.add(Integer.parseInt(tField.getText()));
                    end = System.nanoTime();
                }
                System.out.println(end - start + " нс");
                break;
            case 'Н':
                if (!validateTField())
                    return;
                if (treeBtn.isSelected()) {
                    TreeNode searchNode;
                    start = System.nanoTime();
                    searchNode = theTree.find(Integer.parseInt(tField.getText()));
                    end = System.nanoTime();
                    drawPanel.setSearchNode(searchNode);
                } else {
                    RedBlackTree<Integer>.Node searchNode;
                    start = System.nanoTime();
                    searchNode = theRBTree.findNode(Integer.parseInt(tField.getText()));
                    end = System.nanoTime();
                    drawPanel.setSearchRBNode(searchNode);
                }
                System.out.println(end - start + " нс");
                break;
            case 'У':
                if (!validateTField())
                    return;
                if (treeBtn.isSelected()) {
                    start = System.nanoTime();
                    theTree.remove(Integer.parseInt(tField.getText()));
                    end = System.nanoTime();
                } else {
                    start = System.nanoTime();
                    theRBTree.remove(Integer.parseInt(tField.getText()));
                    end = System.nanoTime();
                }
                System.out.println(end - start + " нс");
                break;
        }
        tField.setText("");
        // theTree.displayTree();
        // RedBlackTree.printTree(theRBTree);
        drawPanel.update(drawPanel.getGraphics());
        actionPanel.update(actionPanel.getGraphics());
    }

    private boolean validateTField() {
        if (!tField.getText().matches("[0-9]+")) {
            tField.setText("IllegalArgumentException");
            return false;
        }
        return true;
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

    private JToggleButton createJToggleBtn(String text, boolean isSelected) {
        JToggleButton btn = new JToggleButton(text);
        btn.setForeground(Color.BLACK);
        btn.setBackground(Color.WHITE);
        btn.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5, 5, 5, 5)));
        btn.addActionListener(this);
        btn.setSelected(isSelected);
        return btn;
    }

    public static void main(String[] args) {
        /*
         * Требующийся функционал программы:
         * 1. Создать программу, реализующую поиск, вставку и удаление элементов в
         * дереве и в красно-чёрном дереве.
         * 2. Полноценный интерфейс, позволяющий вводить числовые и символьные
         * значения, обработка отрицательных значений, ошибка при вводе текста.
         * Интерфейс также должен обладать возможностью отображать ветви дерева. При
         * выполнении операций на дереве оно должно перерисовываться и при необходимости
         * перекрашиваться.
         */
        new Main();
    }
}
