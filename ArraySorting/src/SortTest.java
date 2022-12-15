import java.util.Scanner;

public class SortTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        boolean condition = true;
        Long start;
        Long end;
        int size = 20000;
        SortArray arr = new SortArray(size);

        for (int i = 0; i < size; i++) {
            arr.insert((int) (Math.random() * 100));
        }

        System.out.print("Список действий:\n" +
                "0 - Распечатать исходный массив\n" +
                "1 - Сортировка вставками\n" +
                "2 - Сортировка выбором\n" +
                "3 - Сортировка слиянием\n" +
                "4 - Быстрая сортировка\n" +
                "5 - Выход");

        while (condition) {
            System.out.print("\nВыберите действие: ");
            Scanner in = new Scanner(System.in);
            int action = in.nextInt();
            System.out.println();

            switch (action) {
                case 0:
                    arr.print();
                    break;
                case 1: // Сортировка вставками
                    SortArray insSortedArr = arr.clone();
                    start = System.nanoTime();
                    insSortedArr.selectionSort();
                    end = System.nanoTime();

                    System.out.println("Сортировка вставками завершена. Время: " + (end - start) + " мс");
                    System.out.print("Распечатать результат? (y/n) -> ");
                    if (in.next().equals("y"))
                        insSortedArr.print();
                    break;
                case 2: // Сортировка выбором
                    SortArray selSortedArr = arr.clone();
                    start = System.nanoTime();
                    selSortedArr.selectionSort();
                    end = System.nanoTime();

                    System.out.println("Сортировка выбором завершена. Время: " + (end - start) + " мс");
                    System.out.print("Распечатать результат? (y/n) -> ");
                    if (in.next().equals("y"))
                        selSortedArr.print();
                    break;
                case 3: // Сортировка слиянием
                    SortArray mrgSortedArr = arr.clone();
                    start = System.nanoTime();
                    mrgSortedArr.mergeSort();
                    end = System.nanoTime();

                    System.out.println("Сортировка слиянием завершена. Время: " + (end - start) + " мс");
                    System.out.print("Распечатать результат? (y/n) -> ");
                    if (in.next().equals("y"))
                        mrgSortedArr.print();
                    break;
                case 4: // Быстрая сортировка
                    SortArray qckSortedArr = arr.clone();
                    start = System.nanoTime();
                    qckSortedArr.quickSort();
                    end = System.nanoTime();

                    System.out.println("Быстрая сортировка завершена. Время: " + (end - start) + " мс");
                    System.out.print("Распечатать результат? (y/n) -> ");
                    if (in.next().equals("y"))
                        qckSortedArr.print();
                    break;
                case 5:
                    in.close();
                    condition = false;
                    break;
            }
        }
    }
}
