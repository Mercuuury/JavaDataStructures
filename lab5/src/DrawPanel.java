import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class DrawPanel extends JPanel {
    public Tree theTree;
    private TreeNode searchNode;
    private double width;

    public DrawPanel(Tree theTree, int width, int height) {
        this.theTree = theTree;
        this.width = width;
        this.setSize(width, height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("default", Font.BOLD, 16));
        drawTree(g);
    }

    private void drawTree(Graphics g) { // Обход и отрисовка методом поиска в ширину (BFS)
        if (theTree.getRoot() != null) {
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
                    }

                    double x = width / (2 * nElems) * curEl + width / (2 * nElems) * (curEl - 1);
                    int y = currRow * (yGap - 10);
                    g.drawString(Integer.toString(temp.getValue()), (int) x, y);

                    if (temp.getLeft() != null)
                        g.drawLine((int) x - 7, y, (int) (x - (width / (4 * nElems)) + 5 + currRow),
                                currRow * yGap - 13 * currRow + yGap - 33);
                    if (temp.getRight() != null)
                        g.drawLine((int) x + 22, y, (int) (x + (width / (4 * nElems)) + 7),
                                currRow * yGap - 13 * currRow + yGap - 33);

                    if (temp == searchNode)
                        g.setColor(Color.GREEN);
                    g.drawOval((int) x - 7, y - 20, 30, 30);
                    g.setColor(Color.BLACK);

                    queue.add(temp.getLeft());
                    queue.add(temp.getRight());
                }
                if (nullCount == nElems)
                    break;
                currRow++;
                yGap -= 3;
            }
        }
    }

    public void setSearchNode(TreeNode searchNode) {
        this.searchNode = searchNode;
    }
}