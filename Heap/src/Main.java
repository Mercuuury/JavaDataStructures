import java.util.Scanner;

public class Main {
    // Требующийся функционал программы:
    // 1) Создание пирамиды заданного в интерефейсе размера.
    // 2) Возможность вставки/поиска и удаления по пирамиде.
    // 3) Реализация пирамидальной сортировки массива.
    public static void main(String[] args) {
        boolean condition = true;
        Long start;
        Long end;
        Scanner in = new Scanner(System.in);
        System.out.print("Введите максимальный размер пирамиды: ");
        Heap theHeap = new Heap(in.nextInt());

        System.out.print("Список действий:\n" +
                "0 - Распечатать пирамиду\n" +
                "1 - Вставка\n" +
                "2 - Удаление\n" +
                "3 - Изменение\n" +
                "4 - Поиск\n" +
                "5 - Пирамидальная сортировка массива\n" +
                "6 - Выход");
        while (condition) {
            System.out.print("\nВыберите действие: ");
            int action = in.nextInt();
            System.out.println();
            in.nextLine();
            switch (action) {
                case 0: // Печать
                    theHeap.displayHeap();
                    System.out.println();
                    break;
                case 1: // Вставка
                    System.out.print("Введите значения для вставки через пробел: ");
                    String values = in.nextLine();
                    start = System.nanoTime();
                    for (String value : values.split(" "))
                        if (!theHeap.insert(Integer.parseInt(value)))
                            System.out.println(
                                    "Вставка значения " + Integer.parseInt(value) + " невозможна, пирамида полна.");
                    end = System.nanoTime();
                    System.out.println("Время: " + (end - start) + " нс");
                    break;
                case 2: // Удаление
                    if (!theHeap.isEmpty()) {
                        start = System.nanoTime();
                        System.out.println("Удалено значение " + theHeap.remove().getKey());
                        end = System.nanoTime();
                        System.out.println("Время: " + (end - start) + " нс");
                    } else
                        System.out.println("Удаление невозможно, пирамида пуста.");
                    break;
                case 3: // Изменение
                    System.out.print("Введите индекс изменяемого элемента: ");
                    int index = in.nextInt();
                    System.out.print("Введите новое значение элемента: ");
                    int value = in.nextInt();

                    start = System.nanoTime();
                    if (!theHeap.change(index, value))
                        System.out.println("Изменение невозможно, указан неверный индекс.");
                    else {
                        end = System.nanoTime();
                        System.out.println("Время: " + (end - start) + " нс");
                    }
                    break;
                case 4: // Поиск
                    if (!theHeap.isEmpty()) {
                        System.out.print("Введите искомое значение: ");
                        value = in.nextInt();
                        start = System.nanoTime();
                        index = theHeap.find(value);
                        end = System.nanoTime();
                        if (index != -1) {
                            System.out.println("Значение найдено под индексом " + index);
                            System.out.println("Время: " + (end - start) + " нс");
                        } else
                            System.out.println("Значение не найдено.");
                    } else
                        System.out.println("Поиск невозможен, пирамида пуста.");
                    break;
                case 5: // Сортировка
                    System.out.print("Введите значения неотсортированного массива через пробел: ");
                    String[] valuesArr = in.nextLine().split(" ");
                    Heap sortHeap = new Heap(valuesArr.length);
                    int[] sortArr = new int[valuesArr.length];
                    for (int i = 0; i < valuesArr.length; i++)
                        sortArr[i] = Integer.parseInt(valuesArr[i]);

                    start = System.nanoTime();
                    for (int i = 0; i < valuesArr.length; i++)
                        sortHeap.insert(sortArr[i]); // из несортированного массива
                    for (int i = 0; i < valuesArr.length; i++)
                        sortArr[i] = sortHeap.remove().getKey(); // в отсортированный массив
                    end = System.nanoTime();

                    System.out.print("Отсортированный массив: ");
                    for (int i : sortArr)
                        System.out.print(i + " ");
                    System.out.println("Время: " + (end - start) + " нс");
                    break;
                case 6:
                    in.close();
                    condition = false;
                    break;
            }
        }
    }
}
