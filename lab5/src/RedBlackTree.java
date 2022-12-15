import java.util.ArrayList;

public class RedBlackTree<T extends Comparable<T>> {
    enum NodeColor {
        RED,
        BLACK,
        NONE
    }

    public class Node {
        private T _value; // Значение узла дерева
        private NodeColor _color; // Цвет узла
        private Node _parent; // Родительский узел
        private Node _left; // Левый дочерний узел
        private Node _right; // Правый дочерниый узел

        public Node() {
            _value = null;
            _color = NodeColor.NONE;
            _parent = null;
            _left = null;
            _right = null;
        }

        public Node(T value, NodeColor color) {
            _value = value;
            _color = color;
            _parent = _nil;
            _left = _nil;
            _right = _nil;
        }

        public Node(Node node) { // Конструктор копий
            _value = node._value;
            _color = node._color;
            _parent = node._parent;
            _left = node._left;
            _right = node._right;
        }

        public boolean isFree() {
            return _value == null || _value == _nil;
        }

        public boolean isLeftFree() {
            return _left == null || _left == _nil;
        }

        public boolean isRightFree() {
            return _right == null || _right == _nil;
        }

        public boolean isParentFree() {
            return _parent == null || _parent == _nil;
        }

        public T getValue() {
            return _value;
        }

        public void setValue(T value) {
            _value = value;
        }

        public Node getParent() {
            return _parent;
        }

        public void setParent(Node node) {
            _parent = node;
        }

        public Node getLeft() {
            return _left;
        }

        public void setLeft(Node node) {
            _left = node;
            if (_left != null && _left != _nil)
                _left._parent = this;
        }

        public Node getRight() {
            return _right;
        }

        public void setRight(Node node) {
            _right = node;
            if (_right != null && _right != _nil)
                _right._parent = this;
        }

        public boolean isBlack() {
            return _color == NodeColor.BLACK;
        }

        public void makeBlack() {
            _color = NodeColor.BLACK;
        }

        public boolean isRed() {
            return _color == NodeColor.RED;
        }

        public void makeRed() {
            _color = NodeColor.RED;
        }

        public NodeColor getColor() {
            return _color;
        }

        public void setColor(NodeColor color) {
            _color = color;
        }

        public Node getGrandfather() {
            if (_parent != null && _parent != _nil)
                return _parent._parent;
            return null;
        }

        public Node getUncle() {
            Node grand = getGrandfather();
            if (grand != null) {
                if (grand._left == _parent)
                    return grand._right;
                else if (grand._right == _parent)
                    return grand._left;
            }
            return null;
        }

        public Node getSuccessor() { // Получить следующий по значению узел дерева
            Node temp = null;
            Node node = this;
            if (!node.isRightFree()) {
                temp = node.getRight();
                while (!temp.isLeftFree())
                    temp = temp.getLeft();
                return temp;
            }
            temp = node.getParent();
            while (temp != _nil && node == temp.getRight()) {
                node = temp;
                temp = temp.getParent();
            }
            return temp;
        }

        public String getColorName() {
            return ((isBlack()) ? "B" : "R");
        }

    }

    private Node _root; // Корень дерева
    private Node _nil; // Пустой узел

    public RedBlackTree() {
        _root = new Node();
        _nil = new Node();
        _nil._color = NodeColor.BLACK;
        _root._parent = _nil;
        _root._right = _nil;
        _root._left = _nil;
    }

    // Левый поворот дерева tree относительно узла node
    private static <T extends Comparable<T>> void leftRotate(RedBlackTree<T> tree, RedBlackTree<T>.Node node) {
        RedBlackTree<T>.Node nodeParent = node.getParent(); // Родитель node
        RedBlackTree<T>.Node nodeRight = node.getRight(); // Правый потомок node
        if (nodeParent != tree._nil) { // Если родитель - не пустой узел
            if (nodeParent.getLeft() == node) // Если node - левый потомок родителя
                nodeParent.setLeft(nodeRight); // Устанавливаем правый потомок node левым потомком родителя
            else
                nodeParent.setRight(nodeRight); // Устанавливаем правый потомок node правым потомком родителя
        } else {
            tree._root = nodeRight; // Делаем правый потомок node корнем
            tree._root.setParent(tree._nil); // Устанавливаем родителем корня nil
        }
        node.setRight(nodeRight.getLeft()); // Делаем правым потомком node левый потомок правого потомка node
        nodeRight.setLeft(node); // Делаем левым потомком правого потомка node сам node
    }

    // Правый поворот дерева tree относительно узла node
    private static <T extends Comparable<T>> void rightRotate(RedBlackTree<T> tree, RedBlackTree<T>.Node node) {
        RedBlackTree<T>.Node nodeParent = node.getParent(); // Родитель node
        RedBlackTree<T>.Node nodeLeft = node.getLeft(); // Левый потомок node
        if (nodeParent != tree._nil) { // Если родитель - не пустой узел
            if (nodeParent.getLeft() == node) // Если node - левый потомок родителя
                nodeParent.setLeft(nodeLeft); // Устанавливаем левый потомок node левым потомком родителя
            else
                nodeParent.setRight(nodeLeft); // Устанавливаем левый потомок node правым потомком родителя
        } else {
            tree._root = nodeLeft; // Делаем левый потомок node корнем
            tree._root.setParent(tree._nil); // Устанавливаем родителем корня nil
        }
        node.setLeft(nodeLeft.getRight()); // Делаем левым потомком node правый потомок левого потомка node
        nodeLeft.setRight(node); // Делаем правым потомком левого потомка node сам node
    }

    public void add(T o) { // Добавлене красного узла дерева
        Node node = _root, temp = _nil; // node - корень, temp - nil
        Node newNode = new Node((T) o, NodeColor.RED); // Создаем новый красный узел
        while (node != null && node != _nil && !node.isFree()) { // Поиск позиции для вставки начиная с корня
            temp = node;
            if (newNode.getValue().compareTo(node.getValue()) < 0) // Определение ветви для спуска вниз
                node = node.getLeft();
            else
                node = node.getRight();
        }
        newNode.setParent(temp); // Назначаем родителя нового узла
        if (temp == _nil) // Если дерево пустое
            _root.setValue(newNode.getValue()); // Назначаем корню новое значение
        else {
            if (newNode.getValue().compareTo(temp.getValue()) < 0) // Определяем: левый или правый потомок
                temp.setLeft(newNode);
            else
                temp.setRight(newNode);
        }
        newNode.setLeft(_nil); // Добавляем null листья
        newNode.setRight(_nil); // Добавляем null листья
        fixInsert(newNode);
    }

    private void fixInsert(Node node) { // Исправление цветов узлов дерева после вставки
        Node temp;
        // Пока родитель != null и красный узел имеет красный дочерний узел (нарушение
        // свойства красно-черного дерева)
        while (!node.isParentFree() && node.getParent().isRed()) {
            if (node.getParent() == node.getGrandfather().getLeft()) { // Если родитель - левый потомок
                temp = node.getGrandfather().getRight(); // Получаем правого потомка родителя родителя
                if (temp.isRed()) { // Если он красный
                    temp.makeBlack(); // делаем его черным
                    node.getParent().makeBlack(); // делаем родителя исправляемого узла черным
                    node.getGrandfather().makeRed(); // Делаем родителя родителя красным
                    node = node.getGrandfather(); // Получаем родителя родителя узла
                } else { // Если он черный
                    if (node == node.getParent().getRight()) { // Если исправляемый узел - правый потомок
                        node = node.getParent(); // Получаем родителя исправляемогго узла
                        leftRotate(this, node); // Совершаем левый поворот относительно исправляемого узла
                    }
                    node.getParent().makeBlack(); // Делаем родителя черным
                    node.getGrandfather().makeRed(); // Делаем родителя родителя красным
                    rightRotate(this, node.getGrandfather()); // Совершаем правый поворот относительно родителя родителя
                }
            } else { // Если родитель - правый потомок своего родителя
                // Проводим аналогичную, противоположную процедуру
                temp = node.getGrandfather().getLeft();
                if (temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    node = node.getGrandfather();
                } else {
                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rightRotate(this, node);
                    }
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    leftRotate(this, node.getGrandfather());
                }
            }
        }
        _root.makeBlack(); // Делаем корень черным
    }

    public boolean remove(T o) { // Удаление узла дерева
        Node node = findNode(o);
        if (node == null || node == _nil) // Если узел не найден
            return false;

        Node temp = _nil, successor = _nil;

        if (node.isLeftFree() || node.isRightFree()) // Если один из потмков отсутствует
            successor = node; // Наследником становится node
        else
            successor = node.getSuccessor(); // Наследником становится узел со след. по величине значением

        if (!successor.isLeftFree()) // Если левый потомок наследника имеется
            temp = successor.getLeft(); // Сохраняем в temp левый потомок наследника
        else
            temp = successor.getRight(); // Сохраняем в temp правый потомок наследника
        temp.setParent(successor.getParent()); // Делаем родителем temp родителя наследника

        if (successor.isParentFree()) // Если родитель наследника пуст (корень)
            _root = temp; // Делаем корнем temp
        else if (successor == successor.getParent().getLeft()) // Если наследник - левый потомок родителя
            successor.getParent().setLeft(temp); // Делаем левым потомком родителя наследника temp
        else
            successor.getParent().setRight(temp); // Делаем правым потомком родителя наследника temp

        if (successor != node) { // Если наследник - не удаляемый узел
            node.setValue(successor.getValue()); // Устанавливаем удаляемому узлу значение наследника
        }
        if (successor.isBlack()) // Если наследник - черный
            fixRemove(temp); // Исправляем цвета для сохранения свойств красно-черного дерева
        return true;
    }

    private void fixRemove(Node node) { // Исправление цветов узлов дерева после удаления
        Node temp;
        while (node != _root && node.isBlack()) { // Пока node - не корень и node - черный узел
            if (node == node.getParent().getLeft()) { // Если node - левый потомок своего родителя
                temp = node.getParent().getRight(); // Сохраняем в temp правый потомок родителя node
                if (temp.isRed()) { // Если temp - красный
                    temp.makeBlack(); // Делаем его черным
                    node.getParent().makeRed(); // Делаем родителя node красным
                    leftRotate(this, node.getParent()); // Совершаем левый поворот относительно родителя node
                    temp = node.getParent().getRight(); // Сохраняем в temp правый потомок родителя node
                }
                if (temp.getLeft().isBlack() && temp.getRight().isBlack()) { // Если оба потомка temp - черные
                    temp.makeRed(); // Делаем temp красным
                    node = node.getParent(); // Сохраняем в node родителя node
                } else {
                    if (temp.getRight().isBlack()) { // Если правый потомок temp - черный
                        temp.getLeft().makeBlack(); // Делаем левого потомка temp черным
                        temp.makeRed(); // Делаем temp красным
                        rightRotate(this, temp); // Совершаем правый поворот относительно temp
                        temp = node.getParent().getRight(); // Сохраняем в temp правый потомок родителя node
                    }
                    temp.setColor(node.getParent().getColor()); // Делаем temp цвета родителя node
                    node.getParent().makeBlack(); // Далем родителя node черным
                    temp.getRight().makeBlack(); // Делаем правый потомок temp черным
                    leftRotate(this, node.getParent()); // Совершаем левый поворот относительно родителя node
                    node = _root; // Сохраняем в node корневой узел
                }
            } else { // Если node - правый потомок своего родителя
                // Проводим аналогичную, противоположную процедуру
                temp = node.getParent().getLeft();
                if (temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeRed();
                    rightRotate(this, node.getParent());
                    temp = node.getParent().getLeft();
                }
                if (temp.getLeft().isBlack() && temp.getRight().isBlack()) {
                    temp.makeRed();
                    node = node.getParent();
                } else {
                    if (temp.getLeft().isBlack()) {
                        temp.getRight().makeBlack();
                        temp.makeRed();
                        leftRotate(this, temp);
                        temp = node.getParent().getLeft();
                    }
                    temp.setColor(node.getParent().getColor());
                    node.getParent().makeBlack();
                    temp.getLeft().makeBlack();
                    rightRotate(this, node.getParent());
                    node = _root;
                }
            }
        }
        node.makeBlack();
    }

    // Поиск узла дерева со значением o
    public Node findNode(T o) {
        Node node = _root;
        // Перебор узлов от корня, определяя ветвь для спуска сравнением значений узлов
        while (node != null && node != _nil && node.getValue().compareTo(o) != 0) {
            if (node.getValue().compareTo(o) > 0)
                node = node.getLeft();
            else
                node = node.getRight();
        }
        return node;
    }

    public Node getRoot() {
        return _root;
    }

    public Node getNil() {
        return _nil;
    }

    public static <T extends Comparable<T>> void printTree(RedBlackTree<T> tree) {
        ArrayList<RedBlackTree<T>.Node> nodes = new ArrayList<RedBlackTree<T>.Node>();
        nodes.add(0, tree._root);
        printNodes(tree, nodes);
    }

    private static <T extends Comparable<T>> void printNodes(RedBlackTree<T> tree,
            ArrayList<RedBlackTree<T>.Node> nodes) {
        int childsCounter = 0;
        int nodesAmount = nodes.size();
        ArrayList<RedBlackTree<T>.Node> childs = new ArrayList<RedBlackTree<T>.Node>();

        for (int i = 0; i < nodesAmount; i++) {
            if (nodes.get(i) != null && nodes.get(i) != tree._nil) {
                System.out.print("(" + nodes.get(i).getValue().toString() + "," + nodes.get(i).getColorName() + ")");
                if (!nodes.get(i).isLeftFree()) {
                    childs.add(nodes.get(i).getLeft());
                    childsCounter++;
                } else
                    childs.add(null);
                if (!nodes.get(i).isRightFree()) {
                    childs.add(nodes.get(i).getRight());
                    childsCounter++;
                } else
                    childs.add(null);
            } else {
                System.out.print("--");
            }
        }
        System.out.print("\n");

        if (childsCounter != 0)
            printNodes(tree, childs);
    }
}