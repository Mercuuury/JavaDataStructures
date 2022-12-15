public class HashTable {
    private Node[] arr;
    private Node deletedNode;
    private static final int hashingConst = 5;
    private boolean isLinear;
    private int arrSize;

    public HashTable(int size, boolean isLinear) {
        arrSize = size;
        arr = new Node[arrSize];
        deletedNode = new Node(-1);
        this.isLinear = isLinear ? true : false;
    }

    public int hashFunction(int key) {
        return key % arrSize;
    }

    public int hashFunction2(int key) {
        return key * (key + 3) % arrSize;
    }

    public int hashFunctionDouble(int key) {
        return hashingConst - key % hashingConst;
    }

    public void insert(Node item, int hashFunction) {
        // Хешируем ключ
        int hash = hashFunction == 1 ? hashFunction(item.getKey()) : hashFunction2(item.getKey());
        int stepSize = hashFunctionDouble(item.getKey()); // Определяем размер шага для двойного хеширования

        while (arr[hash] != null && arr[hash].getKey() != -1) // Пока не найдем пустой/удаленный элемент
        {
            hash += isLinear ? 1 : stepSize;
            hash %= arrSize;
        }
        arr[hash] = item;
    }

    public Node delete(int key) {
        int hash = hashFunction(key); // Хешируем ключ
        int stepSize = hashFunctionDouble(key); // Определяем размер шага для двойного хеширования

        while (arr[hash] != null) // Пока не найдем пустой элемент
        {
            if (arr[hash].getKey() == key) { // Если нашли ключ
                Node temp = arr[hash];
                arr[hash] = deletedNode; // Удаляем элемент
                return temp; // Возвращаем удаленный элемент
            }
            hash += isLinear ? 1 : stepSize;
            hash %= arrSize;
        }
        return null;
    }

    public Node find(int key) {
        int hash = hashFunction(key); // Хешируем ключ
        int stepSize = hashFunctionDouble(key); // Определяем размер шага для двойного хеширования

        while (arr[hash] != null) // Пока не найдем пустой элемент
        {
            if (arr[hash].getKey() == key) // Если нашли ключ
                return arr[hash]; // Возвращаем элемент

            hash += isLinear ? 1 : stepSize;
            hash %= arrSize;
        }
        return null;
    }

    public void display() {
        System.out.print("HashTable: ");
        for (Node node : arr) {
            if (node != null) {
                int key = node.getKey();
                if (key == -1)
                    System.out.print("[X] ");
                else
                    System.out.print(node.getKey() + " ");
            } else
                System.out.print("[] ");
        }
        System.out.println();
    }
}
