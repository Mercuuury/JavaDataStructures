import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class DrawPanel extends JPanel {
    public Tree theTree;
    private TreeNode searchNode;
    public RedBlackTree<Integer> redBlackTree;
    private RedBlackTree<Integer>.Node searchRBNode;
    private double width;
    private int treeType; // 0 - Tree, 1 - RBTree

    public DrawPanel(Tree theTree, RedBlackTree<Integer> redBlackTree, int width, int height) {
        this.theTree = theTree;
        this.redBlackTree = redBlackTree;
        this.width = width;
        this.setSize(width, height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("default", Font.BOLD, 16));
        if (treeType == 0)
            drawTree(g);
        else
            drawRBTree(g);
    }

    private void drawTree(Graphics g) { // Обход и отрисовка методом поиска в ширину (BFS)
        if (theTree.getRoot() == null)
            return;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(theTree.getRoot());
        int currRow = 1;
        int yGap = 70;

        while (!queue.isEmpty()) {
            int nElems = (int) Math.pow(2, currRow - 1);
            int nullCount = 0;
            for (int curEl = 1; curEl <= nElems; curEl++) {
                TreeNode temp = queue.poll();
                if (temp == null) {
                    nullCount++;
                    queue.add(null);
                    queue.add(null);
                    continue;
                } else if (temp == searchNode)
                    g.setColor(Color.GREEN);

                double x = width / (2 * nElems) * curEl + width / (2 * nElems) * (curEl - 1);
                int y = currRow * (yGap - 10);
                g.drawString(Integer.toString(temp.getValue()), (int) x, y);
                g.setColor(Color.BLACK);
                g.drawOval((int) x - 7, y - 20, 30, 30);

                if (temp.getLeft() != null)
                    g.drawLine((int) x - 7, y, (int) (x - (width / (4 * nElems)) + 5 + currRow),
                            currRow * yGap - 13 * currRow + yGap - 33);
                if (temp.getRight() != null)
                    g.drawLine((int) x + 22, y, (int) (x + (width / (4 * nElems)) + 7),
                            currRow * yGap - 13 * currRow + yGap - 33);

                queue.add(temp.getLeft());
                queue.add(temp.getRight());
            }
            if (nullCount == nElems)
                break;
            currRow++;
            yGap -= 3;
        }
    }

    private void drawRBTree(Graphics g) { // Обход и отрисовка методом поиска в ширину (BFS)
        if (redBlackTree.getRoot() == null || redBlackTree.getRoot().getValue() == null)
            return;

        Queue<RedBlackTree<Integer>.Node> queue = new LinkedList<RedBlackTree<Integer>.Node>();
        queue.add(redBlackTree.getRoot());
        int currRow = 1;
        int yGap = 70;

        while (!queue.isEmpty()) {
            int nElems = (int) Math.pow(2, currRow - 1);
            int nullCount = 0;
            for (int curEl = 1; curEl <= nElems; curEl++) {
                RedBlackTree<Integer>.Node temp = queue.poll();
                if (temp == null || temp.getValue() == null) {
                    nullCount++;
                    queue.add(null);
                    queue.add(null);
                    continue;
                } else if (temp == searchRBNode)
                    g.setColor(Color.GREEN);

                double x = width / (2 * nElems) * curEl + width / (2 * nElems) * (curEl - 1);
                int y = currRow * (yGap - 10);
                g.drawString(Integer.toString(temp.getValue()), (int) x, y);

                if (temp.isRed())
                    g.setColor(Color.RED);
                else
                    g.setColor(Color.BLACK);
                g.drawOval((int) x - 7, y - 20, 30, 30);

                g.setColor(Color.BLACK);
                if (temp.getLeft() != null && temp.getLeft().getValue() != null)
                    g.drawLine((int) x - 7, y, (int) (x - (width / (4 * nElems)) + 5 + currRow),
                            currRow * yGap - 13 * currRow + yGap - 33);
                if (temp.getRight() != null && temp.getRight().getValue() != null)
                    g.drawLine((int) x + 22, y, (int) (x + (width / (4 * nElems)) + 7),
                            currRow * yGap - 13 * currRow + yGap - 33);

                queue.add(temp.getLeft());
                queue.add(temp.getRight());
            }
            if (nullCount == nElems)
                break;
            currRow++;
            yGap -= 3;
        }

    }

    public void setTreeType(int treeType) {
        this.treeType = treeType;
    }

    public void setSearchNode(TreeNode searchNode) {
        this.searchNode = searchNode;
    }

    public void setSearchRBNode(RedBlackTree<Integer>.Node searchRBNode) {
        this.searchRBNode = searchRBNode;
    }
}