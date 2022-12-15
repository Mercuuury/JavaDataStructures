import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Node node;
        Long start, end;
        int type, key, size, n;
        boolean condition = true;
        Scanner in = new Scanner(System.in);

        System.out.print("Выберите метод пробирования: 1 - линейное пробирование, 2 - двойное хеширование.\n >> ");
        type = in.nextInt();
        System.out.print("Введите размер хэш-таблицы: ");
        size = in.nextInt();
        if (type == 2 && !isPrime(size)) {
            size = getPrime(size);
            System.out.println("При использовании двойного хеширования размер таблицы должен быть простым числом. " +
                    "Размер был увеличен до " + size);
        }
        System.out.print("Введите первоначальное количество элементов: ");
        n = in.nextInt();

        HashTable theHashTable = new HashTable(size, type == 1 ? true : false);
        for (int j = 0; j < n; j++) {
            key = (int) (Math.random() * 10 * size);
            node = new Node(key);
            theHashTable.insert(node, 1);
        }

        System.out.print("Список действий:\n" +
                "0 - Распечатать хеш-таблицу\n" +
                "1 - Вставка\n" +
                "2 - Удаление\n" +
                "3 - Поиск\n" +
                "4 - Сравнение хеш-функций\n" +
                "5 - Выход");
        while (condition) {
            System.out.print("\nВыберите действие: ");
            int action = in.nextInt();
            System.out.println();
            in.nextLine();
            switch (action) {
                case 0: // Печать
                    theHashTable.display();
                    System.out.println();
                    break;
                case 1: // Вставка
                    System.out.print("Введите значение для вставки: ");
                    key = in.nextInt();
                    start = System.nanoTime();
                    theHashTable.insert(new Node(key), 1);
                    end = System.nanoTime();
                    System.out.println("Время: " + (end - start) + " нс");
                    break;
                case 2: // Удаление
                    System.out.print("Введите значение для удаления: ");
                    key = in.nextInt();
                    start = System.nanoTime();
                    node = theHashTable.delete(key);
                    end = System.nanoTime();
                    System.out.println("Результат: " + node + ". Время: " + (end - start) + " нс");
                    break;
                case 3: // Поиск
                    System.out.print("Введите значение для поиска: ");
                    key = in.nextInt();
                    start = System.nanoTime();
                    node = theHashTable.find(key);
                    end = System.nanoTime();
                    System.out.println("Результат: " + node + ". Время: " + (end - start) + " нс");
                    break;
                case 4: // Сравнение хеш-функций
                    HashTable ht1 = new HashTable(size, type == 1 ? true : false);
                    HashTable ht2 = new HashTable(size, type == 1 ? true : false);
                    Node[] nodes = new Node[n];
                    for (int j = 0; j < n; j++) {
                        key = (int) (Math.random() * 10 * size);
                        nodes[j] = new Node(key);
                    }

                    start = System.nanoTime();
                    for (Node no : nodes)
                        ht1.insert(no, 1);
                    end = System.nanoTime();
                    System.out.println("key%size. Время: " + (end - start) + " нс");

                    start = System.nanoTime();
                    for (Node no : nodes)
                        ht2.insert(no, 2);
                    end = System.nanoTime();
                    System.out.println("key*(key+3)%size. Время: " + (end - start) + " нс");
                    break;
                case 5:
                    in.close();
                    condition = false;
                    break;
            }
        }
    }

    private static int getPrime(int min) {
        for (int j = min + 1; true; j++)
            if (isPrime(j))
                return j;
    }

    private static boolean isPrime(int n) {
        for (int j = 2; (j * j <= n); j++)
            if (n % j == 0)
                return false;
        return true;
    }
}
