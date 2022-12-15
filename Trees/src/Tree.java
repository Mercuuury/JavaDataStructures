import java.util.Stack;

public class Tree {
    private TreeNode root;

    public Tree() {
        root = null;
    }

    public TreeNode find(int key) {
        if (root == null)
            return null;
        TreeNode current = root; // Начиная с корня
        while (current.getValue() != key) { // Пока не встретим совпадение
            if (key < current.getValue()) // Идем по левой стороне?
                current = current.getLeft();
            else // Идем по правой стороне?
                current = current.getRight();
            if (current == null) // Если нет потомков
                return null; // Элемент не найден
        }
        return current;
    }

    public void add(int value) {
        TreeNode newNode = new TreeNode(value);
        if (root == null) // Если у дерева нет корня
            root = newNode;
        else { // Если у дерева есть корень
            TreeNode current = root, parent; // Начиная с корня
            while (true) {
                parent = current;
                if (value < current.getValue()) { // Идем по левой стороне?
                    current = current.getLeft();
                    if (current == null) { // Если дошли до конца ветви
                        parent.setLeft(newNode); // Вставляем узел слева
                        return;
                    }
                } else { // Идем по правой стороне?
                    current = current.getRight();
                    if (current == null) { // Если дошли до конца ветви
                        parent.setRight(newNode); // Вставляем узел справа
                        return;
                    }
                }
            }
        }
    }

    public boolean remove(int key) {
        if (root == null)
            return false;
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;

        while (current.getValue() != key) { // Ищем узел
            parent = current;
            if (key < current.getValue()) { // Идем по левой стороне?
                isLeftChild = true;
                current = current.getLeft();
            } else { // Идем по правой стороне?
                isLeftChild = false;
                current = current.getRight();
            }
            if (current == null) // Если нет потомков
                return false; // Элемент не найден
        }

        // Если у узла нет потомков - удаляем его
        if (current.getLeft() == null && current.getRight() == null) {
            if (current == root) // Если удаляемый узел - корневой
                root = null; // Обнуляем корень
            else if (isLeftChild) // Иначе отсоединяем от родителя
                parent.setLeft(null);
            else
                parent.setRight(null);
        }
        // Если нет правого потомка - заменяем на левое поддерево
        else if (current.getRight() == null)
            if (current == root)
                root = current.getLeft();
            else if (isLeftChild)
                parent.setLeft(current.getLeft());
            else
                parent.setRight(current.getLeft());
        // Если нет левого потомка - заменяем на правое поддерево
        else if (current.getLeft() == null)
            if (current == root)
                root = current.getRight();
            else if (isLeftChild)
                parent.setLeft(current.getRight());
            else
                parent.setRight(current.getRight());
        // Если есть оба потомка - заменяем на преемника по порядку
        else {
            TreeNode successor = getSuccessor(current); // Получаем преемника удаляемого узла
            // Соединяем родителя удаляемого с преемником
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.setLeft(successor);
            else
                parent.setRight(successor);
            successor.setLeft(current.getLeft()); // Соединяем преемника с левым потомком удаляемого
        }
        return true;
    }

    private TreeNode getSuccessor(TreeNode delNode) { // Возвращает узел со следующим по величине значением
        TreeNode successorParent = delNode;
        TreeNode successor = delNode;
        TreeNode current = delNode.getRight(); // Переходим к правому потомку
        while (current != null) { // Пока не останется левых потомков
            successorParent = successor;
            successor = current;
            current = current.getLeft(); // Переходим к левому потомку
        }
        if (successor != delNode.getRight()) { // Если преемник не правый потомок - связываем
            successorParent.setLeft(successor.getRight());
            successor.setRight(delNode.getRight());
        }
        return successor;
    }

    public void displayTree() {
        Stack<TreeNode> globalStack = new Stack<TreeNode>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("..............................................................");
        while (isRowEmpty == false) {
            Stack<TreeNode> localStack = new Stack<TreeNode>();
            isRowEmpty = true;
            for (int j = 0; j < nBlanks; j++)
                System.out.print(" ");
            while (globalStack.isEmpty() == false) {
                TreeNode temp = (TreeNode) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getValue());
                    localStack.push(temp.getLeft());
                    localStack.push(temp.getRight());
                    if (temp.getLeft() != null || temp.getRight() != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(" ");
            }
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }
        System.out.println("..............................................................");
    }

    public TreeNode getRoot() {
        return root;
    }
}
