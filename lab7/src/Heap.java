public class Heap {
    private Node[] arr;
    private int maxSize;
    private int currSize; // Поле для определения последней занятой ячейки

    public Heap(int size) {
        arr = new Node[size];
        currSize = 0;
        maxSize = size;
    }

    public void shiftUp(int index) {
        int parent = (index - 1) / 2; // Находим родительский элемент для index
        Node bottom = arr[index]; // Сохраняем узел index
        // По ходу цикла index будет указывать на каждый узел по очереди по пути к корню
        // Пока не достигнут корень && ключ родителя < ключа нового узла
        while (index > 0 && arr[parent].getKey() < bottom.getKey()) {
            arr[index] = arr[parent]; // Копируем родительский узел в index, перемещая узел вниз
            index = parent; // Присваиваем индекс родителя index, перемещая index вверх
            parent = (parent - 1) / 2; // Присваиваем индекс родителя parent
        }
        // Вставляем bottom в ячейку index (Первое место, где узел не больше своего
        // родителя)
        arr[index] = bottom;
    }

    public void shiftDown(int index) {
        int largerChild;
        Node top = arr[index]; // Сохраняем узел index

        while (index < currSize / 2) { // Пока у узла есть как минимум 1 потомок
            int lChild = 2 * index + 1; // Левый потомок
            int rChild = lChild + 1; // Правый потомок

            // Находим большего потомка
            // Если правый потомок существует && его ключ больше ключа левого потомка
            if (rChild < currSize && arr[lChild].getKey() < arr[rChild].getKey())
                largerChild = rChild;
            else
                largerChild = lChild;

            if (top.getKey() >= arr[largerChild].getKey()) // Если исходный ключ >= ключу большего потомка
                break; // Процесс завершен

            arr[index] = arr[largerChild]; // Сдвигаем потомка вверх
            index = largerChild; // Идем вниз
        }
        // Вставляем top в ячейку index
        arr[index] = top;
    }

    public boolean insert(int key) {
        if (currSize == maxSize)
            return false;

        Node newNode = new Node(key);
        arr[currSize] = newNode; // Вставляем узел в первую свободную позицию в конце массива
        shiftUp(currSize++); // Используем смещение вверх
        return true;
    }

    public boolean change(int index, int newValue) {
        if (index < 0 || index >= currSize)
            return false;

        int oldValue = arr[index].getKey(); // Запоминаем старое значение
        arr[index].setKey(newValue); // Меняем на новое значение

        if (oldValue < newValue) // Если новое значение больше старого
            shiftUp(index); // перемещаем элемент вверх
        else
            shiftDown(index); // иначе вниз
        return true;
    }

    public Node remove() { // Удалить корневой элемент
        if (isEmpty())
            return null;

        Node root = arr[0]; // Сохраняем удаляемый ключ
        arr[0] = arr[--currSize]; // Перемещаем последний узел на место корневого
        shiftDown(0); // Смещаем его вниз на подходящую позицию
        return root;
    }

    public int find(int searchKey) {
        if (isEmpty())
            return -1;

        for (int i = 0; i < currSize; i++) // Перебираем все элементы пирамиды
            if (arr[i].getKey() == searchKey)
                return i;
        return -1;
    }

    public boolean isEmpty() {
        return currSize == 0;
    }

    public void displayHeap() {
        for (int m = 0; m < currSize; m++)
            if (arr[m] != null)
                System.out.print(arr[m].getKey() + " ");
            else
                System.out.print("-- ");
        System.out.println();

        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int curr = 0; // current item

        while (currSize > 0) { // Для каждого элемента пирамиды
            if (column == 0) // Корень?
                for (int k = 0; k < nBlanks; k++) // Вывод предшествующих пробелов
                    System.out.print(" ");

            System.out.print(arr[curr].getKey()); // Вывод элемента

            if (++curr == currSize) // Последний элемент?
                break; // Выход из цикла

            if (++column == itemsPerRow) { // Конец строки?
                nBlanks /= 2; // уменьшаем кол-во пробелов вдвое для следующей строки
                itemsPerRow *= 2; // удваиваем количество элементов следующей строки
                column = 0; // начинаем сначала
                System.out.println();
            } else { // Следующий элемент в строке
                for (int k = 0; k < nBlanks * 2 - 2; k++) // Вывод промежуточных пробелов
                    System.out.print(" ");
            }
        }
    }
}
