import java.util.Scanner;

public class OrderedArrayTest {
    public static void main(String[] args) {
        Long start;
        Long end;
        int size = 100_000;
        OrderedArray orderedArr = new OrderedArray(size);
        Scanner in = new Scanner(System.in);

        System.out.println("Начало вставки в массив упорядоченных элементов...");
        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            orderedArr.insert(i);
        }
        end = System.nanoTime();
        System.out.println("Конец. Время: " + (end - start) + " мс");
        
        System.out.println("Начало двоичного поиска по массиву упорядоченных элементов...");
        int searchVal = in.nextInt();
        start = System.nanoTime();
        System.out.println(orderedArr.find(searchVal));
        end = System.nanoTime();
        System.out.println("Конец. Время: " + (end - start) + " мс");

        System.out.println("Начало удаления по массиву упорядоченных элементов...");
        int deleteVal = in.nextInt();
        start = System.nanoTime();
        System.out.println(orderedArr.delete(deleteVal));
        end = System.nanoTime();
        System.out.println("Конец. Время: " + (end - start) + " мс");


    }
}
