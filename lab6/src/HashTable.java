public class HashTable {
    private Node[] arr;
    private int arrSize;
    private Node deletedNode;

    public HashTable(int size) {
        arrSize = size;
        arr = new Node[arrSize];
        deletedNode = new Node(-1);
    }

    public int hashFunction(int key) {
        return key % arrSize;
    }

    public void insert(Node item) {
        int hash = hashFunction(item.getKey()); // Хэшируем ключ

        while (arr[hash] != null && arr[hash].getKey() != -1) // Пока не найдем пустой/удаленный элемент
        {
            ++hash;
            hash %= arrSize;
        }
        arr[hash] = item;
    }

    public Node delete(int key) {
        int hash = hashFunction(key); // Хэшируем ключ
        while (arr[hash] != null) // Пока не найдем пустой элемент
        {
            if (arr[hash].getKey() == key) { // Если нашли ключ
                Node temp = arr[hash];
                arr[hash] = deletedNode; // Удаляем элемент
                return temp; // Возвращаем удаленный элемент
            }
            ++hash;
            hash %= arrSize;
        }
        return null;
    }

    public Node find(int key) {
        int hash = hashFunction(key); // Хэшируем ключ
        while (arr[hash] != null) // Пока не найдем пустой элемент
        {
            if (arr[hash].getKey() == key) // Если нашли ключ
                return arr[hash]; // Возвращаем элемент
            ++hash;
            hash %= arrSize;
        }
        return null;
    }

    public void display() {
        System.out.print("HashTable: ");
        for (Node node : arr) {
            if (node != null)
                System.out.print(node.getKey() + " ");
            else
                System.out.print("[] ");
        }
        // for (int j = 0; j < arrSize; j++) {
        //     if (arr[j] != null)
        //         System.out.print(arr[j].getKey() + " ");
        //     else
        //         System.out.print("[]");
        // }
        System.out.println();
    }
}
