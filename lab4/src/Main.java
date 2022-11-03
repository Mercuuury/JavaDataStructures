import java.util.Scanner;

public class Main {
    private static Long start;
    private static Long end;
    private static ListIterator l1Iter;
    private static ListIterator deIter;
    private static ListIterator l2Iter;
    private static ListIterator srtIter;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите исходный размер массива/списка: ");
        int size = in.nextInt();
        boolean condition = true;

        LinkedList l1list = new LinkedList();
        DoubleEndedList delist = new DoubleEndedList();
        DoublyLinkedList l2list = new DoublyLinkedList();
        SortedList srtlist = new SortedList();
        HighArray arr = new HighArray(size);

        System.out.println("Введите через пробел исходные элементы массива/списков: ");
        String inputString = in.nextLine();
        inputString = in.nextLine();
        for (String str : inputString.split("\\s+")) {
            int num = Integer.parseInt(str);
            l1list.insertFirst(num);
            delist.insertFirst(num);
            l2list.insertFirst(num);
            srtlist.insert(num);
            arr.insert(num);
        }

        l1Iter = l1list.getIterator();
        deIter = delist.getIterator();
        l2Iter = l2list.getIterator();
        srtIter = srtlist.getIterator();

        System.out.print("Список действий:\n" +
                "1 - Перестановка двух конкретных элементов в массиве и списке (по значению)\n" +
                "2 - Проверка скорости перехода к элементу в списке\n" +
                "3 - Вставка элементов\n" +
                "4 - Поиск элементов\n" +
                "5 - Удаление элементов\n" +
                "6 - Выход");

        while (condition) {
            System.out.print("\nВыберите действие: ");
            int action = in.nextInt();
            System.out.println();

            switch (action) {
                case 1:
                    swapElements();
                    break;
                case 2:
                    listIter();
                    break;
                case 3:
                    insert();
                    break;
                case 4:
                    search();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    in.close();
                    condition = false;
                    break;
            }
        }
    }

    private static void swapElements() {

    }

    private static void listIter() {
        System.out.print("Выберите тип списка:\n" +
                "1 - Связанный список\n" +
                "2 - Двусторонний список\n" +
                "3 - Двусвязный список\n" +
                "4 - Вернуться назад");

        Scanner in = new Scanner(System.in);
        boolean condition = true;
        while (condition) {
            System.out.print("\nТип списка: ");
            int action = in.nextInt();
            System.out.println();

            switch (action) {
                case 1:
                    System.out.print("Выберите действие:\n" +
                            "1 - Перейти к следующему элементу\n" +
                            "2 - Вернуться к выбору типа списка");
                    while (true) {
                        System.out.print("\nДействие: ");
                        if (in.nextInt() == 1) {
                            start = System.nanoTime();
                            boolean res = l1Iter.nextLink();
                            end = System.nanoTime();
                            System.out.println("Выполнено за: " + (end - start) + " нс");
                            
                        } else
                            break;
                    }
                    break;
                case 2:
                    listIter();
                    break;
                case 3:
                    insert();
                    break;
                case 4:
                    in.close();
                    condition = false;
                    break;
            }
        }
    }

    private static void insert() {

    }

    private static void search() {

    }

    private static void delete() {

    }
}
